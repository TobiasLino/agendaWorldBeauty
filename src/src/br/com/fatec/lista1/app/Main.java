//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.app;

import br.com.fatec.lista1.controller.Controller;
import br.com.fatec.lista1.model.Agenda;
import br.com.fatec.lista1.view.Menus;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

/*
Inicia o sistema.
Cria um objeto para manipulação dos menus disponíveis.
Cria uma agenda para modificação dos clientes.
Cria um controlador para operações com os arquivos
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Controller ctrl = new Controller();
        Menus menu = new Menus();
        Agenda agenda_ = new Agenda();
        // Faz a verificação do arquivo com os dados dos clientes
        File f = new File("agenda.json");
        // recover passa os dados do arquivo para a agenda na memória.
        if (f.exists()) agenda_.recover();
        else if (!f.createNewFile()) ctrl.err("Criação de arquivo", true);

        while (true) {
            switch (menu.mainMenu()) {
                case 1 : menu.InsertClient(agenda_); break;
                case 2 : menu.ListClients(agenda_); break;
                case 3 : menu.removeCliente(agenda_); break;
                case 4 : menu.editaCliente(agenda_); break;
                case 5 : menu.geraRelatorio(agenda_); break;
                case 6 : return;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        }
    }
}
