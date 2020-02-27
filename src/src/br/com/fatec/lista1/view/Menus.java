package br.com.fatec.lista1.view;

import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.Scanner;

public class Menus {
    public int mainMenu() {
        int opcao;
        Scanner scan = new Scanner(System.in);
        String n = "\nEscolha a opção desejada:\n"
                + "\t1. Cadastrar Cliente.\n"
                + "\t2. Realizar Compra.\n"
                + "\t3. Listar Clientes.\n"
                + "\t4. Excluir Cliente.\n"
                + "\t5. Editar Cliente.\n"
                + "\t6. Gerar Relatório.\n"
                + "\t7. Histórico.\n"
                + "\t8. Sair.";
        System.out.println(n);
        System.out.print("Qual sua opção? ");
        opcao = Integer.parseInt(scan.nextLine());
        return opcao;
    }

    public int insertClientOptions() {
        int opcao = 0;
        Scanner scan = new Scanner(System.in);
        String n = "\nInsira a opção correspondente:\n"
                + "\t1. Inserir Nome.\n"
                + "\t2. Inserir Data de Nascimento.\n"
                + "\t3. Inserir Gênero.\n"
                + "\t4. Inserir Telefone.\n"
                + "\t5. Inserir Compra.\n"
                + "\t6.Cancelar.\n"
                + "\t7.Confirmar.";
        System.out.println(n);
        System.out.print("Qual sua opção? ");
        opcao = Integer.parseInt(scan.nextLine());
        return opcao;
    }

    public void adicionaCliente(Agenda agenda, Client cliente) {
        agenda.add(cliente);
        System.out.println("Adiciona CLiente");
    }

    public void InsertClient(Agenda agenda) {
        int option = 0;
        Client novoCliente = new Client();
        while (option != 1) {
            option = insertClientOptions();
            switch (option) {
                case 1 : insertName(novoCliente); option = 0; break;
                // case 2 : insertBirth(novoCliente); option = 0; break;
                case 3 : insertGender(novoCliente); option = 0; break;
                case 4 : insertPhone(novoCliente); option = 0; break;
                case 6 : return;
                case 7 :
                    if (confirmaNome(novoCliente)) {
                        if (confirmOption()) {
                            adicionaCliente(agenda, novoCliente);
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
    public boolean confirmaNome(Client client) {
        if (client.getName_() != "null") {
            return true;
        }
        return false;
    }

    public void insertName(Client cliente) {
        String nam = getOption("Insira o nome: ");
        cliente.setName_(nam);
    }

    public void insertBirth(Client cliente) {
        Scanner n = new Scanner(System.in);
        Date d = new Date();
        System.out.print("Insira a Data de Nascimento");
        cliente.setBirth_(d);
    }

    public void insertGender(Client cliente) {
        String g = getOption("\n\t[M] - Masculino\n\t[F] - Feminino" +
                "\n\t[N] - Não Binário\n\nInsira o gênero: ");
        cliente.setGender_(g);
    }

    public void insertPhone(Client cliente) {
        String p = getOption("Insira o número de telefone [com o DDD] : ");
        Phone tel = new Phone(p);
        cliente.setPhone_(tel);
    }

    public boolean confirmOption() {
        char opcao = getOption("Confirmar ? (S/n): ").charAt(0);
        if (opcao == 's'
                || opcao == 'S') {
            return true;
        } else {
            return false;
        }
    }

    public void listaClientes(Agenda agenda) {
        System.out.println("\n\nListando todos os clientes Cadastrados.");
        agenda.print();
    }

    public boolean verificaCliente(Agenda agenda) {
        String nome = getOption("Insira o nome do Cliente [0 para sair]: ");
        if (nome == "0") return false;
        boolean verified = true;
        if (agenda.contains(nome)) {
            return verified;
        } else {
            System.out.println("Cliente não encontrado.");
            verified = false;
            verificaCliente(agenda);
        }
        return verified;
    }
    public void editaCliente(Agenda agenda) {
        if (verificaCliente(agenda)) {
            System.out.println("Encontrado");
            return;
        } else {
            return;
        }
    }

    public String getOption(String msg) {
        System.out.print(msg);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
