//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.view;

import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.util.Scanner;

public class Operations {

    public String getOption(String msg) {
        System.out.print(msg);
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    // Adiciona cliente diretamente na agenda
    public void adicionaCliente(Agenda agenda, Client cliente) {
        agenda.add(cliente);
        System.out.println("Adiciona Cliente");
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
            agenda.print();
        }
        System.out.println();
    }

    public void listaClientesMale(Agenda agenda) {
        System.out.println("\n\nListando todos os Clientes Masculinos.");
        agenda.printMale();
        System.out.println();
    }

    public void listaClientesFemale(Agenda agenda) {
        System.out.println("\n\nListando todos os Clientes Femininos.");
        agenda.printFemale();
        System.out.println();
    }

    public void listaClientesNotBinaries(Agenda agenda) {
        System.out.println("\n\nListando todos os Clientes de gênero não binário.");
        agenda.printNotBinaries();
    }
}

