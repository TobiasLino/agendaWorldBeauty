package br.com.fatec.lista1.view;

import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.util.Scanner;

public class Menus {
    public int mainMenu() {
        int opcao;
        Scanner scan = new Scanner(System.in);
        String n = "\nEscolha a opção desejada:\n"
                + "\t1. Cadastrar Cliente.\n"
                + "\t2. Listar Clientes.\n"
                + "\t3. Listar Clientes Masculinos.\n"
                + "\t4. Listar Clientes Femininos.\n"
                + "\t5. Excluir Cliente.\n"
                + "\t6. Editar Cliente.\n"
                + "\t7. Gerar Relatório.\n"
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
                + "\t5.Cancelar.\n"
                + "\t6.Confirmar.";
        System.out.println(n);
        System.out.print("Qual sua opção? ");
        opcao = Integer.parseInt(scan.nextLine());
        return opcao;
    }

    public void adicionaCliente(Agenda agenda, Client cliente) {
        agenda.add(cliente);
        System.out.println("Adiciona Cliente");
    }

    public void InsertClient(Agenda agenda) {
        Client novoCliente = new Client();
        editaClienteInfos(agenda, novoCliente);
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
        System.out.print("Insira a Data de Nascimento");
        cliente.setBirth_(n.nextLine());
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

    public void listaClientesM(Agenda agenda) {
        System.out.println("\n\nListando todos os Clientes Masculinos.");
        agenda.printMale();
    }

    public void listaClientesF(Agenda agenda) {
        System.out.println("\n\nListando todos os Clientes Femininos.");
        agenda.printFemale();
    }

    public boolean verificaCliente(String name, Agenda agenda) {
        boolean verified = true;
        if (agenda.contains(name)) {
            return verified;
        } else {
            System.out.println("Cliente não encontrado.");
            verified = false;
            verificaCliente(name, agenda);
        }
        return verified;
    }

    public Client getClient(String name, Agenda agenda) {
        if (agenda.contains(name)) {
            Client l = agenda.findIt(name);
            return l;
        } else {
            return null;
        }

    }

    public void editaClienteInfos(Agenda agenda, Client cliente) {
        int option = 0;
        while (option != 1) {
            option = insertClientOptions();
            switch (option) {
                case 1 : insertName(cliente); option = 0; break;
                case 2 : insertBirth(cliente); option = 0; break;
                case 3 : insertGender(cliente); option = 0; break;
                case 4 : insertPhone(cliente); option = 0; break;
                case 5 : return;
                case 6 :
                    if (confirmaNome(cliente)) {
                        if (confirmOption()) {
                            adicionaCliente(agenda, cliente);
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

    public void editaCliente(Agenda agenda) {
        String name = getOption("Digite o nome do cliente: ");
        Client toEdit = agenda.findIt(name);
        editaClienteInfos(agenda, toEdit);
    }

    public void removeCliente(Agenda agenda) {
        String name = getOption("Digite o nome do cliente: ");
        if (verificaCliente(name, agenda)) {
            Client lx = getClient(name, agenda);
            agenda.remove(lx);
        }
    }

    public String getOption(String msg) {
        System.out.print(msg);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
