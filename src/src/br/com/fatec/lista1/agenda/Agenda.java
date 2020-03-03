package br.com.fatec.lista1.agenda;

import br.com.fatec.lista1.registration.Client;

import java.util.*;


public class Agenda {
    protected List<Client>[] agenda_;

    public Agenda() {
        int ALPHABETLEN = 26;
        agenda_ = new List[ALPHABETLEN + 1];
        for (List<Client> clients : agenda_) {
            clients = new LinkedList<>();
        }
    }

    public Client findIt(String name) {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getName_().equals(name))
                    return client;
            }
        }
        return null;
    }

    public void add(Client cliente) {
        int i = getIndex(cliente.getName_());
        agenda_[i].add(cliente);
    }

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

    public void remove(Client cliente) {
        remove(cliente.getName_());
    }

    public int size() {
        int i, tamanho = 0;
        for (i = 0; i < agenda_.length; ++i) {
            tamanho += agenda_[i].size();
        }
        return tamanho;
    }

    public void sort() {
        Client tmp;
        for (List<Client> clients : agenda_) {
            clients.sort(Comparator.comparing(Client::getName_));
        }
    }

    public void print() {
        Client tmp;
        for (List<Client> clients : agenda_) {
            clients.sort(Comparator.comparing(Client::getName_));
            for (Client client : clients) {
                tmp = client;
                tmp.Print();
            }
        }
    }

    public List<Client> getClients(int index) {
        return agenda_[index];
    }

    public void printMale() {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getGender_().charAt(0) == 'M'
                        || client.getGender_().charAt(0) == 'm')
                    client.Print();
                System.out.println();
            }
        }
    }

    public void printFemale() {
        for (List<Client> clientes : agenda_) {
            for (Client client : clientes) {
                if (client.getGender_().charAt(0) == 'F'
                        || client.getGender_().charAt(0) == 'f')
                    client.Print();
                System.out.println();
            }
        }
    }

    public boolean contains(Client cliente) {
        Client tmp = findIt(cliente.getName_());
        return tmp != null;
    }

    public boolean contains(String clientName) {
        Client tmp = findIt(clientName);
        return tmp != null;
    }

    public int getIndex(String str) {
        char var = str.charAt(0);
        return var - 65;
    }
}
