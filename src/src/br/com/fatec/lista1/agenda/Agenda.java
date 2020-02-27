package br.com.fatec.lista1.agenda;

import br.com.fatec.lista1.registration.Client;

import java.util.*;
/*
 *  Classe Principal que mantém todos os clientes numa lista alfabética.
 *  Exemplo:
 *      Agenda a = new Agenda();
 *      Date dat = new Date();
 *      Client cl1 = new Client("Tobias", dat);
 *      Client cl2 = new Client("Tania", dat);
 *
 *      a.addTo(cl1.getName_(), cl1);
 *      a.addTo(cl2.getName_(), cl2);
 *
 *      a.Print();      // Imprime todos os clientes
 *                      // em ordem alfabética
*/
public class Agenda {
    private List<Client>[] agenda_;

    public Agenda() {
        agenda_ = new LinkedList[27];
        int i = 0;
        for (;i < 27; ++i) {
            agenda_[i] = new LinkedList<Client>();
        }
    }

    public Client findIt(String name) {
        int index = getIndex(name);
        int value;
        Iterator i = agenda_[index].iterator();
        for (;i.hasNext();) {
            value = agenda_[index].indexOf(i.next());
            if (name.equals(agenda_[index].get(value).getName_())) {
                return agenda_[index].get(value);
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
        Iterator i = agenda_[index].iterator();
        for (;i.hasNext();) {
            valor = agenda_[index].indexOf(i.next());
            if (lixo.getName_().equals(agenda_[index].get(valor).getName_())) {
                i.remove();
            }
        }
    }

    public int size() {
        int i, tamanho = 0;
        for (i = 0; i < agenda_.length; ++i) {
            tamanho += agenda_[i].size();
        }
        return tamanho;
    }

    public void print() {
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
                System.out.println();
            }
        }
    }

    public boolean contains(Client cliente) {
        Client tmp = findIt(cliente.getName_());
        return tmp == null ? false : true;
    }

    public boolean contains(String clientName) {
        Client tmp = findIt(clientName);
        return tmp == null ? false : true;
    }

    public int getIndex(String str) {
        char var = str.charAt(0);
        return var - 65;
    }
}
