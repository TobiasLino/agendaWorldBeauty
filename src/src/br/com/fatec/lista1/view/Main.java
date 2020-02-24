package br.com.fatec.lista1.view;

public class Main {
    public static void main(String[] args) {
        Menus menu = new Menus();
        while (true) {
            switch (menu.mainMenu()) {
                case 1:
                    menu.InsertClient();
                default:
                    System.out.println("Digite uma opção válida.");
            }
        }
    }
}
