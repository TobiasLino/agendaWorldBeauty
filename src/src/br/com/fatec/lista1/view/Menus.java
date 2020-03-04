//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.view;

import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;

import java.io.IOException;
import java.util.Scanner;

public class Menus {
    // Operações de inserção e listagem na agenda
    private Operations op = new Operations();
    // Menu principal, chamado na classe Main.
    public int mainMenu() {
        int opcao;
        Scanner scan = new Scanner(System.in);
        String n = "\nEscolha a opção desejada:\n"
                + "\t1. Cadastrar Cliente.\n"
                + "\t2. Listar Clientes.\n"
                + "\t3. Excluir Cliente.\n"
                + "\t4. Editar Cliente.\n"
                + "\t5. Gerar Relatório.\n"
                + "\t6. Sair.\n";
        System.out.print(n + "Qual sua opção? : ");
        opcao = Integer.parseInt(scan.nextLine());
        if (opcao > 0 && opcao <= 6) return opcao;
        else return 0;
    }
    // Menu para listagem de clientes.
    public int ListClientMenu() {
        int opcao;
        Scanner scan = new Scanner(System.in);
        String n = "\nEscolha a opção desejada:\n"
                + "\t1. Listar todos os clientes.\n"
                + "\t2. Listar Clientes Masculinos.\n"
                + "\t3. Listar Clientes Femininos.\n"
                + "\t4. Listar clientes de gênero não binário\n"
                + "\t5. Sair.\n";
        System.out.print(n + "Qual sua opção? :");
        opcao = Integer.parseInt(scan.nextLine());
        if (opcao > 0 && opcao <= 5) return opcao;
        else return 0;
    }
    // Chamada da impressão das listas.
    public void ListClients(Agenda agenda) {
        int option = 0;
        while (option != 1) {
            switch (ListClientMenu()) {
                case 1 : op.listaClientes(agenda); break;
                case 2 : op.listaClientesMale(agenda); break;
                case 3 : op.listaClientesFemale(agenda); break;
                case 4 : op.listaClientesNotBinaries(agenda); break;
                case 5 : option = 1;
                default: System.out.println("Insira uma opção válida.");
            }
        }
    }
    // Menu de inserção/edição de clientes.
    public int insertClientOptions() {
        int opcao = 0;
        Scanner scan = new Scanner(System.in);
        String n = "\nInsira a opção correspondente:\n"
                + "\t1. Inserir Nome.\n"
                + "\t2. Inserir Data de Nascimento.\n"
                + "\t3. Inserir Gênero.\n"
                + "\t4. Inserir Telefone.\n"
                + "\t5.Cancelar.\n"
                + "\t6.Confirmar.\n";
        System.out.print(n + "Qual sua opção? ");
        opcao = Integer.parseInt(scan.nextLine());
        if (opcao > 0 && opcao <= 6) return opcao;
        else return 0;
    }
    // Cria o cliente a ser inserido.
    public void InsertClient(Agenda agenda) throws IOException {
        Client novoCliente = new Client();
        editaClienteInfos(agenda, novoCliente, true);
    }
    // Imprime as informações do cliente temporário.
    public void tempClientInfos(Client client) {
        System.out.print("Dados do cliente até agora: ");
        op.title();
        client.Print();
    }
    // Confirma se o nome do cliente foi digitado
    public boolean confirmaNome(Client client) {
        return !client.getName_().equals("");
    }
    // Menu de confirmação da opção.
    public boolean confirmOption() {
        String opcao = op.getOption("Confirmar ? (S/n): ");
        return opcao.equals("s") || opcao.equals("S")
                || opcao.equals("");
    }
    // Edita os dados do cliente selecionado, true se for adiciona-lo à agenda
    // e false se apenas for editar.
    public void editaClienteInfos(Agenda agenda, Client cliente, boolean novoCliente) throws IOException {
        int option = 0;
        while (option != 1) {
            tempClientInfos(cliente);
            int opcao = insertClientOptions();
            switch (opcao) {
                case 1 : op.insertName(cliente); break;
                case 2 : op.insertBirth(cliente); break;
                case 3 : op.insertGender(cliente); break;
                case 4 : op.insertPhone(cliente); break;
                case 5 : return;
                case 6 :
                    if (confirmaNome(cliente)) {        // Confirma se o nome do cliente foi inserido.
                        if (confirmOption()) {
                            if (novoCliente) op.adicionaCliente(agenda, cliente);  // Se for adicionar ou apenas editar.
                            // se não, sincroniza com o arquivo
                            agenda.sync();
                            option = 1;
                            break;
                        } else {
                            option = 0; break;
                        }
                    } else {
                        System.out.println("O cliente não possui nome");
                        option = 0; break;
                    }
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
    // Edita as informações do cliente
    public void editaCliente(Agenda agenda) throws IOException {
        String name = op.getOption("Digite o nome do cliente: ");
        Client toEdit = agenda.findIt(name);
        if (op.verificaCliente(toEdit, agenda)) editaClienteInfos(agenda, toEdit, false);
    }
    // Remove o cliente selecionado.
    public void removeCliente(Agenda agenda) {
        String name = op.getOption("Digite o nome do cliente: ");
        Client toRemove = agenda.findIt(name);
        if (op.verificaCliente(toRemove, agenda)) {
            Client lx = op.getClient(name, agenda);
            if (confirmOption()) {
                agenda.remove(lx);
            }
        }
    }
}
