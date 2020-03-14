//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.view;

import br.com.fatec.lista1.controller.Controller;
import br.com.fatec.lista1.model.Agenda;
import br.com.fatec.lista1.model.Client;
import br.com.fatec.lista1.model.Historic;
import br.com.fatec.lista1.model.Purchase;

import java.io.IOException;

public class Menus {
        // Operações de inserção e listagem na agenda
        private Controller op = new Controller();
        // Menu principal, chamado na classe Main.
        public int mainMenu() {
                System.out.print("\nEscolha a opção desejada:\n"
                        + "\t1. Cadastrar Cliente.\n"
                        + "\t2. Listar Clientes.\n"
                        + "\t3. Excluir Cliente.\n"
                        + "\t4. Editar Cliente.\n"
                        + "\t5. Gerar Relatório.\n"
                        + "\t6. Histórico de Compras.\n"
                        + "\t7. Compras.\n"
                        + "\t8. Sair.\n");
                String result = op.getOption("Digite a sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Menu para listagem de clientes.
        public int ListClientMenu() {
                System.out.print("\nEscolha a opção desejada:\n"
                        + "\t1. Listar todos os clientes.\n"
                        + "\t2. Listar Clientes Masculinos.\n"
                        + "\t3. Listar Clientes Femininos.\n"
                        + "\t4. Listar clientes de gênero não binário\n"
                        + "\t5. Sair.\n");
                String result = op.getOption("Digite a sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
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
        private int insertClientOptions() {
                System.out.print("\nInsira a opção correspondente:\n"
                        + "\t1. Inserir Nome.\n"
                        + "\t2. Inserir Idade.\n"
                        + "\t3. Inserir Data de Nascimento.\n"
                        + "\t4. Inserir Gênero.\n"
                        + "\t5. Inserir Telefone.\n"
                        + "\t6.Cancelar.\n"
                        + "\t7.Confirmar.\n");
                String result = op.getOption("Digite a sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Cria o cliente a ser inserido.
        public void InsertClient(Agenda agenda) throws IOException {
                Client novoCliente = new Client();
                editaClienteInfos(agenda, novoCliente, true);
        }
        // Imprime as informações do cliente temporário.
        private void tempClientInfos(Client client) {
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
                                case 2 : op.insertAge(cliente); break;
                                case 3 : op.insertBirth(cliente); break;
                                case 4 : op.insertGender(cliente); break;
                                case 5 : op.insertPhone(cliente); break;
                                case 6 : return;
                                case 7 :
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
        public void removeCliente(Agenda agenda) throws IOException {
                String name = op.getOption("Digite o nome do cliente: ");
                Client toRemove = agenda.findIt(name);
                if (op.verificaCliente(toRemove, agenda)) {
                        Client lx = op.getClient(name, agenda);
                        if (confirmOption()) {
                                agenda.remove(lx);
                        }
                }
                agenda.sync();
        }
        // Opçoes para visualização a salvamento dos relatórios
        private int relOptions() {
                System.out.print("Insira a opção desejada:\n"
                        + "\t1. Imprimir relatório.\n"
                        + "\t2. Salvar arquivo.\n"
                        + "\t3. Voltar.\n");
                String result = op.getOption("Digite a sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Menu para seleção da opção
        public void geraRelatorio(Agenda agenda, Historic historic) throws IOException {
                int n = 0;
                do {
                        n = relOptions();
                        switch (n) {
                                case 1: op.impRelatorio(agenda, historic); break;
                                case 2: op.saveRelatorio(agenda, historic); break;
                                case 3: return;
                                default: System.out.println("Insira uma opção válida.");
                        }
                } while (true);
        }
        // Opções de visualização do histórico
        private int historicOptions() {
                System.out.println("\nSelecione sua opção.\n"
                        + "\t1. Visualizar histórico da unidade.\n"
                        + "\t2. Visualizar histórico por cliente.\n"
                        + "\t3. Voltar.");
                String result = op.getOption("Digite sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Seleção
        public void historicMenu(Agenda agenda, Historic historic) {
                int opt = 0;
                while (opt != 3) {
                        opt = historicOptions();
                        switch (opt) {
                                case 1: geralHist(historic); break;
                                case 2: clientHist(agenda); break;
                                default:
                                        System.out.println("Digite um valor válido.");
                        }
                }
        }
        // Historico geral
        private void geralHist(Historic historic) {
                if (historic.size() > 0) {
                        historic.Print();
                } else {
                        System.out.println("Nenhuma compra cadastrada.");
                }
        }
        // Histórico por cliente
        private void clientHist(Agenda agenda) {
                String nome = op.getOption("Digite o nome do cliente: ");
                if (!nome.equals("")) {
                        Client tmp = agenda.findIt(nome);
                        if (tmp != null && tmp.getHistoric_() != null) {
                                tmp.getHistoric_().Print();
                        } else {
                                System.out.println("Cliente não encontrado.");
                        }
                } else {
                        System.out.println("Digite um nome.");
                }
        }
        // Opções do menu de compras
        private int purchaseMenuOptions() {
                System.out.println("\nDigite a sua opção:\n"
                        + "\t1. Adicionar compra.\n"
                        + "\t2. Remover compra.\n"
                        + "\t3. Editar compra.\n"
                        + "\t4. Voltar.");
                String result = op.getOption("Digite sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Menu de compras
        public void purchaseMenu(Historic historic, Agenda agenda) {
                int opt = 0;
                while (opt != 4) {
                        opt = purchaseMenuOptions();
                        switch (opt) {
                                case 1: newPurchase(historic, agenda); break;
                                case 2: removePurchase(historic, agenda); break;
                                case 3: changePurchase(historic, agenda); break;
                                default:
                                        System.out.println("Digite um valor válido.");
                        }
                }
        }
        // Opções de cadastro de uma nova compra
        private int editPurchaseOptions() {
                System.out.println("\nSelecione uma das opções\n"
                        + "\t1. Adicionar cliente.\n"
                        + "\t2. Adicionar produtos.\n"
                        + "\t3. Adicionar serviços.\n"
                        + "\t4. Adicionar valor.\n"
                        + "\t5. Adicionar método de pagamento.\n"
                        + "\t6. Cancelar\n"
                        + "\t7. Salvar.");
                String result = op.getOption("Digite sua opção: ");
                int opt = 0;
                if (!result.equals("")) {
                        opt = Integer.parseInt(result);
                }
                return opt;
        }
        // Cadastro de uma nova compra
        private void newPurchase (Historic historic, Agenda agenda) {
                Purchase nova = new Purchase();
                editPurchase(historic, nova, agenda);
        }
        // Remoção de uma compra
        private void removePurchase(Historic historic, Agenda agenda) {
                // imprime as compras do cliente selecionado
                clientHist(agenda);
                String purchaseId = op.getOption("\nInsira o id da compra a ser removida [Enter para sair]: ");
                if (!purchaseId.equals("")) {
                        int id = Integer.parseInt(purchaseId);
                        historic.remove(id);
                }
        }
        // Edição de uma compra
        private void changePurchase(Historic historic, Agenda agenda) {
                // imprime as compras do cliente selecionado
                clientHist(agenda);
                String purchaseId = op.getOption("Insira o id da compra a ser editada [Enter para sair]: ");
                while (!purchaseId.equals("")) {
                        int id = Integer.parseInt(purchaseId);
                        Purchase tmp = historic.findIt(id);
                        if (tmp != null) {
                                editPurchase(historic, tmp, agenda);
                        } else {
                                System.out.println("Id incorreto");
                        }
                }
        }
        // Opções para edição da compra
        private void editPurchase(Historic historic, Purchase nova, Agenda agenda) {
                int opt = 0;
                while (opt != 6) {
                        nova.print();
                        opt = editPurchaseOptions();
                        switch (opt) {
                                case 1: addClientToPurchase(agenda, nova); break;
                                case 2: addProductsToPurchase(nova); break;
                                case 3: addServicesToPurchase(nova); break;
                                case 4: addValueToPurchase(nova); break;
                                case 5: addPaymentToPurchase(nova); break;
                                case 7:
                                        String name = nova.getClient();
                                        if (name.equals("")) {
                                                System.out.println("Insira o nome do cliente");
                                                break;
                                        } else {
                                                if (confirmOption()) {
                                                        historic.Add(nova);
                                                        return;
                                                }
                                        }
                        }
                }
        }
        // Verifica se o cliente existe e insere-o na compra
        private void addClientToPurchase(Agenda agenda, Purchase compra) {
                String name = op.getOption("Insira o nome do cliente: ");
                Client tmp = agenda.findIt(name);
                if (tmp != null) {
                        compra.setClient(tmp);
                } else {
                        System.out.println("Cliente não encontrado.");
                }
        }
        // Opções dos produtos disponíveis
        private String availableProducts(Purchase purchase) {
                String[] produtos = purchase.getAvailableProducts();
                System.out.printf("\nInsira o Valor correspondente ao produto.\n"
                        + "\t1. %23s\t2. %23s\n\t3. %23s\t4. %23s\n"
                        + "\t5. %23s\t6. %23s\n\t7. %23s\t8. %23s\n"
                        + "\t9. %23s\t10. %23s\n\t11. %23s\t12. %23s\n",
                        produtos[0], produtos[1], produtos[2], produtos[3],
                        produtos[4], produtos[5], produtos[6], produtos[7],
                        produtos[8], produtos[9], produtos[10], produtos[11]
                );
                return op.getOption("Digite sua opção [Enter para sair/confirmar] : ");
        }
        // Adiciona os produtos à compra
        private void addProductsToPurchase(Purchase compra) {
                String[] produtos = compra.getAvailableProducts();
                String opt = availableProducts(compra);
                while (!opt.equals("")) {
                        int index = Integer.parseInt(opt);
                        compra.addProductsByIndex(index);
                        opt = availableProducts(compra);
                }
        }
        // Adiciona os serviços à compra
        private void addServicesToPurchase(Purchase compra) {
                String service = op.getOption("Insira o serviço [Enter para finalizar] : ");
                compra.addServices(service);
        }
        // Adiciona o valor da compra
        private void addValueToPurchase(Purchase compra) {
                String valor = op.getOption("Insira o valor da compra: ");
                if (!valor.equals("")) {
                        double value = Double.parseDouble(valor);
                        if (value >= 0) {
                                compra.setValue_(value);
                        } else {
                                System.out.println("Valor tem que ser um número positivo.");
                        }
                } else {
                        System.out.println("Valor tem que ser um número.");
                }
        }
        // Adiciona método de pagamento
        private void addPaymentToPurchase(Purchase compra) {
                String method = "";
                String option = op.getOption("Insira o número correspondente.\n"
                        + "\t1. dinheiro.\n"
                        + "\t2. cartão de crédito.\n"
                        + "\t3. cartão de débito.\n"
                        + "Qual sua opção: ");
                if (!option.equals("")) {
                        switch (Integer.parseInt(option)) {
                                case 1: method = "dinheiro"; break;
                                case 2: method = "cred_card"; break;
                                case 3: method = "deb_card"; break;
                                default:
                                        System.out.println("Valor inválido");
                        }
                        compra.setPaymentMethod_(method);
                }
        }
}
