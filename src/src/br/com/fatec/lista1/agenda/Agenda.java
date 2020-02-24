package br.com.fatec.lista1.agenda;

import br.com.fatec.lista1.registration.Client;

import java.util.*;

public class Agenda {
    public final int CHARVALUE = 65;
    private List<Client>[] agenda_ = new LinkedList[27];

    public Agenda() {
        int i = 0;
        for (;i < 27; ++i) {
            agenda_[i] = new LinkedList<Client>();
        }
    }

    public Client findIt(Client client) {
        char v = client.getName_().charAt(0);
        int i = v - CHARVALUE;
        int value = agenda_[i].indexOf(client);
        Client achado = agenda_[i].get(value);
        if (achado != null) {
            return achado;
        } else {
            return null;
        }
    }

    public void addTo(String name, Client cliente) {
        Client novoCliente = cliente;
        char index = name.charAt(0);
        int i = index - CHARVALUE;
        agenda_[i].add(novoCliente);
    }

    public int Size() {
        return agenda_.length;
    }

    public void Print() {
        int i, n;
        Iterator ref;
        Client tmp;
        for (i = 0; i < agenda_.length; ++i) {
            Collections.sort(agenda_[i], Comparator.comparing(Client::getName_));
            ref = agenda_[i].iterator();
            for (; ref.hasNext();) {
                n = agenda_[i].indexOf(ref.next());
                tmp = agenda_[i].get(n);
                tmp.Print();
            }
        }
    }
}
