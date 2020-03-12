//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.model;

import br.com.fatec.lista1.controller.Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agenda {
    // Vetor onde cada elemento contém uma lista com clientes cujo
    // nome começa com a letra correspondente ao seu índice
    // (A = 0, B = 1, ..., Z = 25) com +1 de segurança.
    protected List<Client>[] agenda_;
    // Array para salvar no arquivo json.
    JSONObject jsonObject = null;
    JSONArray jsonArray = null;
    private final String file_name = "agenda.json";
    // Define um tamanho fixo do vetor e inicializa cada elemento.
    @SuppressWarnings("unchecked")
    public Agenda() throws FileNotFoundException {
        int ALPHABETLEN = 26;
        agenda_ = new LinkedList[ALPHABETLEN + 1];
        for (int i = 0; i < ALPHABETLEN + 1; ++i) {
            agenda_[i] = new LinkedList<Client>();
        }
    }
    // Retorna o cliente cujo nome seja igual ao parâmetro passado.
    public Client findIt(String name) {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getName_().equals(name))
                    return client;
            }
        }
        return null;
    }
    // Adiciona o cliente na lista correspondente. Por segurança,
    // a lista é reordenada evitando conflitos de nomes parecidos.
    public void add(Client cliente) {
        int i = getIndex(cliente.getName_());
        agenda_[i].add(cliente);
        agenda_[i].sort(Comparator.comparing(Client::getName_));
    }
    // Remove um cliente pelo nome.
    public void remove(String name) throws IOException {
        int index = getIndex(name);
        int valor;
        Client lixo = new Client();
        lixo.setName_(name);
        // Iterator i = agenda_[index].iterator();
        for (Client client : agenda_[index])
            if (client.getName_().equals(name)) {
                remove(client);
        }
    }
    // Remove um cliente passando o objeto.
    public void remove(Client cliente) throws IOException {
        int index = getIndex(cliente.getName_());
        Iterator<Client> i = agenda_[index].iterator();
        if (i.hasNext()) {
            if (i.next().equals(cliente)) {
                i.remove();
            }
        }
    }
    // Retorna a quantidade de elementos presentes na agenda.
    public int size() {
        int i, tamanho = 0;
        for (i = 0; i < agenda_.length; ++i) {
            tamanho += agenda_[i].size();
        }
        return tamanho;
    }
    // Imprime todos os clientes da lista no terminal.
    public void print() {
        Client tmp;
        for (List<Client> clients : agenda_) {
            for (Client client : clients) {
                tmp = client;
                tmp.Print();
            }
        }
    }
    // Retorna a lista de clientes correspondente ao indíce (inicial
    // do nome do cliente).
    public List<Client> getClients(int index) {
        return agenda_[index];
    }
    // Imprime apenas Clientes definidos como do gênero masculino.
    public void printMale() {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getGender_().charAt(0) == 'm')
                    client.Print();
            }
        }
    }
    // Imprime apenas clientes definidos como do gênero feminino.
    public void printFemale() {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getGender_().charAt(0) == 'f')
                    client.Print();
            }
        }
    }
    // Imprime apenas clientes definidos como de um gênero não binário.
    public void printNotBinaries() {
        for (List<Client> clients : agenda_) {
            for (Client client : clients) {
                if (client.getGender_().charAt(0) == 'n') {
                    client.Print();
                }
            }
        }
    }
    // Booleano que verifica se a agenda contém o cliente.
    public boolean contains(Client cliente) {
        Client tmp = findIt(cliente.getName_());
        return tmp != null;
    }
    // Verifica se a agenda contém o cliente, pelo nome.
    public boolean contains(String clientName) {
        Client tmp = findIt(clientName);
        return tmp != null;
    }
    // Retorna o índice correspondente a letra inicial da string passada
    // como parâmetro.
    public int getIndex(String str) {
        char var = str.toUpperCase().charAt(0);
        return var - 65;
    }
    // Salvar no arquivo.
    @SuppressWarnings("unchecked")
    public void sync() throws IOException {
        Controller ctrl = new Controller();
        File f = new File(file_name);
        if (!f.delete())  ctrl.err("Erro ao deletar", false);
        try {
            // File file = new File(file_name);
            //file.createNewFile();
            FileWriter arquivoJson = new FileWriter(file_name);
            jsonArray = new JSONArray();
            for (List<Client> clients : agenda_) {
                for (Client client : clients) {
                    JSONObject obj = new JSONObject();
                    putIntoJSON(obj, client);
                    jsonArray.add(obj);
                }
            }
            arquivoJson.write(jsonArray.toJSONString());
            arquivoJson.flush();
        } catch (IOException e) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // Insere os dados do cliente no jsonObj.
    @SuppressWarnings("unchecked")
    private void putIntoJSON(JSONObject jsonObject, Client client) {
        jsonObject.put("name", client.getName_());
        jsonObject.put("age", client.getAge());
        jsonObject.put("birth", client.getBirth_());
        jsonObject.put("gender", client.getGender_());
        if (client.getPhone_() != null) {
            jsonObject.put("phone", client.getPhone_().getNumber_());
        } else {
            jsonObject.put("phone", "");
        }
    }
    // Recupera os dados do arquivo.
    @SuppressWarnings("unchecked")
    public void recover() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader arquivoJson = new FileReader(file_name);
            Object obj = jsonParser.parse(arquivoJson);
            if (obj instanceof JSONArray) {
                jsonArray = (JSONArray) obj;
                jsonArray.forEach(Client -> getJson ((JSONObject) Client));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Passa o conteúdo do Json para a agenda.
    private void getJson(JSONObject jsonObject) {
        Client tmp = new Client();
        tmp.setName_(jsonObject.get("name").toString());
        tmp.setAge(Integer.parseInt(jsonObject.get("age").toString()));
        tmp.setGender_(jsonObject.get("gender").toString());
        tmp.setBirth_(jsonObject.get("birth").toString());
        Phone tel = new Phone(jsonObject.get("phone").toString());
        tmp.setPhone_(tel);

        add(tmp);
    }
}
