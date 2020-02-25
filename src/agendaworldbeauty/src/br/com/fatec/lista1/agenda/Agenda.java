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
 *      Client cl3 = new Client("José", dat, "Masculino");
 *      Client cl4 = new Client("Jana", dat, "Faminino");
 *      Client cl5 = new Client("Amanda", dat, "Feminino");
 *
 *      a.addTo(cl1.getName_(), cl1);
 *      a.addTo(cl2.getName_(), cl2);
 *      a.addTo(cl3.getName_(), cl3);
 *      a.addTo(cl4.getName_(), cl4);
 *      a.addTo(cl5.getName_(), cl5);
 *
 *      a.Print();      // Imprime todos os clientes
 *                      // em ordem alfabética
*/
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

    public void add(Client cliente) {
        Client novoCliente = cliente;
        char index = cliente.getName_().charAt(0);
        int i = index - CHARVALUE;
        agenda_[i].add(novoCliente);
    }

    public int size() {
        return agenda_.length;
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
            }
        }
    }
}
