import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.util.Date;

public class testes {
    public static void main(String[] args) {
        Agenda a = new Agenda();
        Date dat = new Date();
        Client cl1 = new Client("Tobias", dat);
        Client cl2 = new Client("Tania", dat);
        Client cl3 = new Client("José", dat, "Masculino");
        Client cl4 = new Client("Jana", dat, "Faminino");
        Client cl5 = new Client("Amanda", dat, "Feminino");

        a.addTo(cl1.getName_(), cl1);
        a.addTo(cl2.getName_(), cl2);
        a.addTo(cl3.getName_(), cl3);
        a.addTo(cl4.getName_(), cl4);
        a.addTo(cl5.getName_(), cl5);
/*
        Client test = a.findIt(cl1);
        test.Print();

        test = a.findIt(cl2);
        test.Print();

        test = a.findIt(cl3);
        test.Print();

        test = a.findIt(cl4);
        test.Print();

        test = a.findIt(cl5);
        test.Print();

 */

        a.Print();
    }
}
