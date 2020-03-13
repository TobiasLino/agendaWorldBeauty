//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.app;

import br.com.fatec.lista1.controller.Controller;
import br.com.fatec.lista1.model.Agenda;
import br.com.fatec.lista1.model.Historic;
import br.com.fatec.lista1.view.Menus;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/*      Inicia o sistema.       */
public class Main {
        public static void main(String[] args) throws IOException, ParseException {
                // operações especiais e com arquivo
                Controller ctrl = new Controller();
                // interface
                Menus menu = new Menus();
                // lista de clientes
                Agenda agenda_ = new Agenda();
                // histórico de compras da unidade
                Historic historicoUnidade = new Historic(agenda_);
                // verifica se o arquivo de clients existe
                ctrl.fileCheck("agenda.json", agenda_);
                // verifica se o histórico existe
                ctrl.fileCheck("historic.json", historicoUnidade);
                // opções
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
