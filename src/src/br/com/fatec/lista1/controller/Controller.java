//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.controller;

import br.com.fatec.lista1.model.*;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Controller {
        // Controle simples de erros
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
        public void adicionaCliente(Agenda agenda, Client cliente) throws IOException {
                agenda.add(cliente);
                System.out.println("Adiciona Cliente");
                agenda.sync();
        }

        public boolean verificaCliente(Client client, Agenda agenda) {
                if (client != null) {
                        return true;
                } else {
                        System.out.println("Cliente não encontrado.");
                        return false;
                }
        }

        public Client getClient(String name, Agenda agenda) {
                if (agenda.contains(name)) {
                        return agenda.findIt(name);
                } else {
                        return null;
                }

        }

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
        // Define o titulo da tabela de clientes
        public void title() {
                System.out.printf("\n%40s|%3s|%10s|%12s|%17s\n","Nome", "Age", "Birth", "Gender", "Phone");
        }
        // Define o titulo da tabela de compras
        public void histTitle() {
                System.out.printf("\n%4s|%30s|%30s|%40s|%40s|%10s|%7s\n",
                        "ID", "Cliente", "Data", "Produtos", "Serviços", "Pagamento", "Valor");
        }
        // Imprime relatório.
        public void impRelatorio(Agenda agenda) {
                Report rep = new Report(agenda);
                rep.printReport();
        }
        // Salva o arquivo com o relatório
        public void saveRelatorio(Agenda agenda) throws IOException {
                Report rep = new Report(agenda);
                rep.getReportFile();
        }
        // Verifica se o arquivo existe
        public void fileCheck(String fileName, Agenda agenda) throws IOException, ParseException {
                String pathname = "usrFiles//clients";
                File path = new File(pathname);
                // Faz a verificação do arquivo com os dados dos clientes
                File f = new File(pathname + "//" + fileName);
                if (!path.exists()) {
                        if (!path.mkdirs()) err("Criando pasta", true);
                        if (!f.createNewFile()) err("Criando arquivo", true);
                        // recover passa os dados do arquivo para a agenda na memória.
                        agenda.recover();
                } else {
                        if (f.exists()) agenda.recover();
                        else if (!f.createNewFile()) err("Criação de arquivo", true);
                }
        }

        public void fileCheck(String filename, Historic historico) throws IOException, ParseException {
                String pathname = "usrFiles//historic";
                File path = new File(pathname);
                File f = new File(pathname + "//" + filename);
                if (!path.exists()) {
                        if (!path.mkdirs()) err("Criando pasta", true);
                        if (!f.createNewFile()) err("Criando arquivo", true);
                        historico.recover();
                } else {
                        if (f.exists()) historico.recover();
                        else if (!f.createNewFile()) err("Criação de arquivo", true);
                }
        }
}

