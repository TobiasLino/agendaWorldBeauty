package br.com.fatec.lista1.view;

import br.com.fatec.lista1.agenda.Agenda;

public class Main {
    public static void main(String[] args) {
        Menus menu = new Menus();
        Agenda agenda_ = new Agenda();

        while (true) {
            switch (menu.mainMenu()) {
                case 1 : menu.InsertClient(agenda_); break;
                case 2 : menu.listaClientes(agenda_); break;
                case 3 : menu.listaClientesM(agenda_); break;
                case 4 : menu.listaClientesF(agenda_); break;
                case 6 : menu.editaCliente(agenda_); break;
                case 8 : return;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        }
    }
}
