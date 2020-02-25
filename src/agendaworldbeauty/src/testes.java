import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.util.Date;

public class testes {
    public static void main(String[] args) {
        Agenda a = new Agenda();
        Date dat = new Date();

        Client cl1 = new Client("Amanda", dat, "Feminino");
        Phone tAmanda = new Phone("12 9966558822");
        cl1.setPhone_(tAmanda);
        Client cl2 = new Client("José", dat, "Masculino");
        Phone tJose = new Phone("11 966552233");
        cl2.setPhone_(tJose);
        Client cl3 = new Client("Jana", dat, "Feminino");
        Phone tJana = new Phone("14 978551244");
        cl3.setPhone_(tJana);

        a.add(cl1);
        a.add(cl2);
        a.add(cl3);

    }
}
