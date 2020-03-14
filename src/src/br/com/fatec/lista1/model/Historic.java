package br.com.fatec.lista1.model;

import br.com.fatec.lista1.controller.Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Historic {
        private Agenda agenda;
        private Purchase firstPurchase_;
        private List<Purchase> allPurchases_;

        private JSONArray jsonArray = null;
        public Historic() {
                this.agenda = null;
                firstPurchase_ = null;
                allPurchases_ = new LinkedList<>();
        }
        public Historic(Agenda agenda) {
                this.agenda = agenda;
                firstPurchase_ = null;
                allPurchases_ = new LinkedList<>();
        }

        public void Add(Purchase novaEntrada) {
                if (firstPurchase_ == null) {
                        firstPurchase_ = novaEntrada;
                }
                allPurchases_.add(novaEntrada);
                sync();
        }
        // Retorna uma compra referênciada pelo id
        public Purchase findIt(int id) {
                Iterator<Purchase> i = allPurchases_.iterator();
                while (i.hasNext()) {
                        if (i.next().getId() == id) {
                                return i.next();
                        }
                }
                return null;
        }

        public int size() { return allPurchases_.size(); }
        // Remove uma compra
        public void remove(int id) {
                allPurchases_.removeIf(compra -> compra.getId() == id);
        }

        public void Print() {
                for (Purchase compra : allPurchases_) {
                        compra.print();
                }
        }
        // Retorna uma referência para a lista allPurchases_
        public List<Purchase> getList() {
                return allPurchases_;
        }
        // Sincroniza os dados com o arquivo
        @SuppressWarnings("unchecked")
        public void sync() {
                String file_name = "usrFiles//historic//historic.json";
                Controller ctrl = new Controller();
                File f = new File(file_name);
                if (!f.delete())  ctrl.err("Erro ao deletar", false);
                try {
                        FileWriter arquivoJson = new FileWriter(file_name);
                        jsonArray = new JSONArray();
                        for (Purchase compra : allPurchases_) {
                                JSONObject obj = new JSONObject();
                                putIntoJSON(obj, compra);
                                jsonArray.add(obj);
                        }
                        arquivoJson.write(jsonArray.toJSONString());
                        arquivoJson.flush();
                } catch (IOException e) {
                        Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, e);
                }
        }
        // Coloca os dados corretamente dentro do objeto
        @SuppressWarnings("unchecked")
        private void putIntoJSON(JSONObject jo, Purchase p) {
                jo.put("cliente", p.getClient());
                jo.put("data", p.getDate_());
                jo.put("valor", p.getValue_());
                // Passa os produtos como um array
                if (p.getProducts() != null) {
                        jo.put("produtos", p.getProducts());
                } else {
                        jo.put("produtos", "");
                }
                if (p.getServices() != null) {
                        jo.put("servicos", p.getServices());
                } else {
                        jo.put("servicos", "");
                }
                jo.put("metodo", p.getPaymentMethod_());
        }
        // Recupera os dados do arquivo.
        @SuppressWarnings("unchecked")
        public void recover() {
                String file_name = "usrFiles//historic//historic.json";
                JSONParser jsonParser = new JSONParser();
                try {
                        File file = new File(file_name);
                        if (file.length() > 0) {
                                FileReader arquivoJson = new FileReader(file);
                                Object obj = jsonParser.parse(arquivoJson);
                                if (obj instanceof JSONArray) {
                                        jsonArray = (JSONArray) obj;
                                        jsonArray.forEach(Purchase -> getJson((JSONObject) Purchase));
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        // Obtem os arquivos
        private void getJson(JSONObject obj){
                Purchase novo = new Purchase();
                // busco o cliente na minha agenda
                Client tmp = agenda.findIt(obj.get("cliente").toString());

                if (tmp != null) {
                        // minha compra passa a ter meu cliente
                        novo.setClient(tmp);
                        String date =  obj.get("data").toString();
                        novo.setDate_(date);
                        novo.setValue_(Double.parseDouble(obj.get("valor").toString()));
                        novo.addProducts(obj.get("produtos").toString());
                        novo.addServices(obj.get("servicos").toString());
                        novo.setPaymentMethod_(obj.get("metodo").toString());

                        tmp.addPurchase(novo);
                }

                allPurchases_.add(novo);
        }
}
