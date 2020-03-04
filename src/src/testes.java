//
//  Tobias Lino 2020.
//
import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;
import br.com.fatec.lista1.registration.Phone;

import java.io.IOException;

public class testes {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Agenda a1 = new Agenda();

        Client cl1 = new Client();
        cl1.setName_("Tobias Lino");
        cl1.setAge(18);
        cl1.setGender_("Masculino");
        cl1.setBirth_("24/10/2001");
        Phone tel = new Phone("12 996819353");
        cl1.setPhone_(tel);

        Client cl2 = new Client();
        cl2.setName_("Malvina Bezerra");
        cl2.setGender_("Feminino");
        cl2.setAge(82);
        cl2.setBirth_("12/02/1938");

        Client cl3 = new Client();
        cl3.setName_("Ana Paula da Silva Lino");
        cl3.setGender_("feminino");
        cl3.setAge(37);
        cl3.setBirth_("20/01/1982");

        a1.add(cl2);
        a1.add(cl3);
        a1.add(cl1);
        /*
        FileManip fl = new FileManip();

        fl.sync(a1);

        fl.Read(a2);
         */

        a1.print();
    }
}
