package br.com.fatec.lista1.model;

import br.com.fatec.lista1.controller.Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Historic {
        private Agenda agenda;
        private Purchase firstPurchase_;
        private Purchase lastPurchase_;
        private List<Purchase> allPurchases_;

        private JSONArray jsonArray = null;

        public Historic(Agenda agenda) {
                this.agenda = agenda;
                firstPurchase_ = lastPurchase_ = null;
                allPurchases_ = new LinkedList<>();
        }

        public void Add(Purchase novaEntrada) {
                if (firstPurchase_ != null) {
                        lastPurchase_ = novaEntrada;
                        allPurchases_.add(novaEntrada);
                } else {
                        firstPurchase_ = lastPurchase_ = novaEntrada;
                        allPurchases_.add(novaEntrada);
                }
                sync();
        }

        public void remove() {}

        public Purchase GetFirst() {
                return this.firstPurchase_;
        }

        public Purchase GetLast() {
                return this.lastPurchase_;
        }

        public void Print() {
                Controller op = new Controller();
                op.histTitle();
                for (Purchase compra : allPurchases_) {
                        compra.print();
                }
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
                        JSONObject jo2 = new JSONObject();
                        JSONArray jsonA = new JSONArray();
                        int key = 0;
                        for (String i : p.getProducts()) {
                                jo2.put(key++, i);
                        }
                        jsonA.add(jo2);
                        jo.put("produtos", jsonA);
                } else {
                        jo.put("produtos", "");
                }
                if (p.getServices() != null) {
                        // Passa os serviços como um array
                        JSONObject jo2 = new JSONObject();
                        JSONArray jsonA = new JSONArray();
                        int key = 0;
                        for (String i : p.getServices()) {
                                jo2.put(key++, i);
                        }
                        jsonA.add(jo2);
                        jo.put("servicos", jsonA);
                } else {
                        jo.put("servicos", "");
                }
                jo.put("metodo", p.getPaymentMethod_());
        }
        // Recupera os dados do arquivo.
        @SuppressWarnings("unchecked")
        public void recover() throws IOException, ParseException {
                String file_name = "usrFiles//historic//historic.json";
                JSONParser jsonParser = new JSONParser();
                try {
                        File file = new File(file_name);
                        if (file.length() > 0) {
                                FileReader arquivoJson = new FileReader(file);
                                Object obj = jsonParser.parse(arquivoJson);
                                if (obj instanceof JSONArray) {
                                        jsonArray = (JSONArray) obj;
                                        jsonArray.forEach(Purchase -> {
                                                try {
                                                        getJson((JSONObject) Purchase);
                                                } catch (FileNotFoundException e) {
                                                        e.printStackTrace();
                                                }
                                        });
                                }
                        } else {
                                return;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        // Obtem os arquivos
        private void getJson(JSONObject obj) throws FileNotFoundException {
                Purchase novo = new Purchase();

                Client tmp = agenda.findIt(obj.get("cliente").toString());

                if (tmp != null) {
                        novo.setClient(tmp);
                        Date date = (Date) obj.get("data");
                        novo.setDate_(date);
                        novo.setValue_(Double.parseDouble(obj.get("valor").toString()));
                        JSONArray js = (JSONArray) obj.get("produtos");
                        for (Object i : js.toArray()) {
                                novo.addProducts(i.toString());
                        }
                        js = (JSONArray) obj.get("servicos");
                        for (Object i : js.toArray()) {
                                novo.addServices(i.toString());
                        }
                        novo.setPaymentMethod_(obj.get("metodo").toString());
                }

                allPurchases_.add(novo);
        }
}
