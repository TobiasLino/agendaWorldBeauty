//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.agenda;

import br.com.fatec.lista1.registration.Client;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.*;

public class Agenda {
    // Vetor onde cada elemento contém uma lista com clientes cujo
    // nome começa com a letra correspondente ao seu índice
    // (A = 0, B = 1, ..., Z = 25) com +1 de segurança.
    protected List<Client>[] agenda_;
    // Define um tamanho fixo do vetor e inicializa cada elemento.
    public Agenda() {
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
    public void remove(String name) {
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
    public void remove(Client cliente) {
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
        char var = str.charAt(0);
        return var - 65;
    }
    // Salvar no arquivo
    public void sync() throws JSONException {
        JSONArray array = new JSONArray();
        for (List<Client> clients : agenda_) {
            for (Client client : clients) {
                JSONObject jso = new JSONObject();
                putIntoJSON(jso, client);
                array.put(jso);
            }
        }
    }
    // Insere os dados do cliente no jsonObj.
    public void putIntoJSON(JSONObject jsonObject, Client client) throws JSONException {
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
}
