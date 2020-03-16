/*
        This file is part of AgendaGrupoWorldBeauty.

        AgendaGrupoWorldBeauty is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        AgendaGrupoWorldBeauty is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <https://www.gnu.org/licenses/>.

 */
package br.com.fatec.lista1.app;

import br.com.fatec.lista1.controller.Controller;
import br.com.fatec.lista1.model.Agenda;
import br.com.fatec.lista1.model.Historic;
import br.com.fatec.lista1.view.Menus;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {
                // Nota de copyright da GPL
                System.out.println("AgendaWorldBeauty  Copyright (C) 2020  Tobias da Silva Lino\n" +
                        "    This program comes with ABSOLUTELY NO WARRANTY.\n" +
                        "    This is free software, and you are welcome to redistribute it\n" +
                        "    under certain conditions.\n");
                // Operações especiais e com arquivo
                Controller ctrl = new Controller();
                // interface principal
                Menus menu = new Menus();
                // Lista de clientes
                Agenda agenda_ = new Agenda();
                // Histórico de compras da unidade
                Historic historicoUnidade = new Historic(agenda_);
                // O sistema salva os dados em arquivos .json, armazenados no diretório
                // usrFiles, assim é necessário verificar se o mesmo existe e, se sim,
                // analisar os dados contidos nos mesmos.
                ctrl.fileCheck(agenda_, historicoUnidade);
                // A partir daqui as opções ficam disponíveis para o usuário.
                while (true) {
                        switch (menu.mainMenu()) {
                                case 1 : menu.InsertClient(agenda_); break;
                                case 2 : menu.ListClients(agenda_); break;
                                case 3 : menu.removeCliente(agenda_); break;
                                case 4 : menu.editaCliente(agenda_); break;
                                case 5 : menu.geraRelatorio(agenda_, historicoUnidade); break;
                                case 6 : menu.historicMenu(agenda_, historicoUnidade); break;
                                case 7 : menu.purchaseMenu(historicoUnidade, agenda_); break;
                                case 8 : return;
                                default:
                                        System.out.println("Digite uma opção válida.");
                        }
                }
        }
}
