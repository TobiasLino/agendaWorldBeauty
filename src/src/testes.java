import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.util.Date;

public class testes {
    public static void main(String[] args) {
        Agenda a = new Agenda();
        Date dat = new Date();

        Client cl1 = new Client("Amanda", "24/10/2001", "Feminino");
        Phone tAmanda = new Phone("12 9966558822");
        cl1.setPhone_(tAmanda);
        Client cl2 = new Client("José de Macedo", "24/10/2001", "M");
        Phone tJose = new Phone("11 966552233");
        cl2.setPhone_(tJose);
        Client cl3 = new Client("Jana", "24/10/2001", "Feminino");
        Phone tJana = new Phone("14 978551244");
        cl3.setPhone_(tJana);
        Client cl4 = new Client("Amanda", "24/10/2001", "F");
        Phone tAmanda2 = new Phone("12 9966558822");
        cl1.setPhone_(tAmanda2);
        Client cl5 = new Client("José Otávio", "24/10/2001", "M");
        Phone tJose2 = new Phone("11 966552233");
        cl2.setPhone_(tJose2);
        Client cl6 = new Client("Jana", "24/10/2001", "F");
        Phone tJana2 = new Phone("14 978551244");
        cl3.setPhone_(tJana2);

        a.add(cl1);
        a.add(cl2);
        a.add(cl3);
        a.add(cl4);
        a.add(cl5);

        System.out.println("Tamanho : " + a.size() + "\n\nANTES:");
        a.print();
        a.remove("José Otávio");
        System.out.println("Tamanho: " + a.size() + "\n\nDEPOIS");
        a.print();

        System.out.println("Male::");
        a.printMale();
        System.out.println("Female::");
        a.printFemale();
    }
}
