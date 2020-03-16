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
package br.com.fatec.lista1.controller;

import br.com.fatec.lista1.model.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Controller {
        // Controle de Erros.
        // O argumento fatality define a fatalidade do erro para que o programa
        // decida se uma parada é necessária.
        public void err(String msg, boolean fatality) {
                System.out.println("ERROR: " + msg);
                if (fatality) { exit(0); }
        }
        // retorna a opção digitada pelo usuário mediante uma mensagem.
        public String getOption(String msg) {
                System.out.print(msg);
                Scanner s = new Scanner(System.in);
                return s.nextLine();
        }
        // Adiciona cliente diretamente na agenda
        public void adicionaCliente(Agenda agenda, Client cliente) {
                agenda.add(cliente);
                System.out.println("Adiciona Cliente");
                agenda.sync();
        }
        // Certifica que o cliente foi encontrado.
        public boolean checkIfNotNull(Client client) {
                return client != null;
        }
        // Certifica que o histórico do cliente não está nulo
        public boolean checkIfNotNull(Historic historic) {
                return historic != null;
        }
        // Certifica que a compra não é nula
        public boolean checkIfNotNull(Purchase purchase) {
                return purchase != null;
        }
        /*
         * Opções de inserção dos dados digitados no cliente.
         */
        public void insertName(Client cliente) {
                String nam = getOption("Insira o nome: ");
                cliente.setName_(nam);
        }
        public void insertAge(Client client) {
                int age = Integer.parseInt(getOption("Insira a idade: "));
                client.setAge(age);
        }
        public void insertBirth(Client cliente) {
                Scanner n = new Scanner(System.in);
                System.out.print("Insira a Data de Nascimento: ");
                cliente.setBirth_(n.nextLine());
        }
        // Com o objetivo de padronizar as inserções de gênero, insere as string pré
        // selecionadas de acordo com a primeira letra da palavra digitada.
        public void insertGender(Client cliente) {
                String g = getOption("\n\t[M] - Masculino\n\t[F] - Feminino" +
                        "\n\t[N] - Não Binário\n\nInsira o gênero: ");
                switch (g.charAt(0)){
                        case 'f' :
                        case 'F' : cliente.setGender_("feminino"); break;
                        case 'm' :
                        case 'M' : cliente.setGender_("masculino"); break;
                        case 'n' :
                        case 'N' : cliente.setGender_("Não binário"); break;
                        default:
                                System.out.println("Digite um valor válido.");
                                insertGender(cliente);
                }
        }
        public void insertPhone(Client cliente) {
                String p = getOption("Insira o número de telefone [com o DDD] : ");
                Phone tel = new Phone(p);
                cliente.setPhone_(tel);
        }
        /*
         * Métodos de impressão.
         */
        public void listaClientes(Agenda agenda) {
                if (agenda.size() == 0) {
                        System.out.println("Nenhum cliente Cadastrado");
                } else {
                        System.out.println("\n\nListando todos os clientes Cadastrados.");
                        title();
                        agenda.print();
                }
                System.out.println();
        }
        public void listaClientesMale(Agenda agenda) {
                System.out.println("\n\nListando todos os Clientes Masculinos.");
                title();
                agenda.printMale();
                System.out.println();
        }
        public void listaClientesFemale(Agenda agenda) {
                System.out.println("\n\nListando todos os Clientes Femininos.");
                title();
                agenda.printFemale();
                System.out.println();
        }
        public void listaClientesNotBinaries(Agenda agenda) {
                System.out.println("\n\nListando todos os Clientes de gênero não binário.");
                title();
                agenda.printNotBinaries();
        }
        /*
         * Define o titulo da tabela de clientes
         */
        public void title() {
                System.out.printf("\n%40s|%3s|%10s|%12s|%17s\n","Nome", "Age", "Birth", "Gender", "Phone");
        }
        /*
         * Operações com os relatórios
         *
         * Imprime.         */
        public void impRelatorio(Agenda agenda, Historic historic) {
                Report rep = new Report(agenda, historic);
                rep.printReport();
        }
        // Salva o arquivo com o relatório
        public void saveRelatorio(Agenda agenda, Historic historic) throws IOException {
                Report rep = new Report(agenda, historic);
                rep.getReportFile();
        }
        /*
         * Verifica se os arquivos existem e, se sim, analisa os dados e os distribui.
         */
        public void fileCheck(Agenda agenda, Historic historic) throws IOException {
                String clientPathName = "usrFiles//clients";
                String historicPathName = "usrFiles//historic";
                File clientPath = new File(clientPathName);
                File historicPath = new File(historicPathName);
                File fC = new File(clientPathName + "//agenda.json");
                File fH = new File(historicPathName + "//historic.json");
                if (!clientPath.exists() && !historicPath.exists()) {
                        if (!clientPath.mkdirs() && !historicPath.mkdirs()) {
                                err("Criando pastas", true);
                        }
                        if (!fC.createNewFile() && !fH.createNewFile()) {
                                err("Criando arquivos", true);
                        }
                        // recover passa os dados do arquivo para a memória.
                        agenda.recover();
                } else {
                        if (fC.exists() && fH.exists()) {
                                agenda.recover();
                                historic.recover();
                        }
                        else if (!fC.createNewFile() && !fH.createNewFile()) {
                                err("Criação de arquivos", true);
                        }
                }
        }
}

