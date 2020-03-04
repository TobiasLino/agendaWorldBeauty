//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.filemanipulation;

import br.com.fatec.lista1.agenda.Agenda;
import br.com.fatec.lista1.registration.Client;

import java.io.*;
import java.util.List;

public class FileManip {
    private final String file_name = "agenda.dat";
    private File file = new File("arquivo.txt");

    public void sync(Agenda agenda) throws IOException {
        FileOutputStream fOut = new FileOutputStream(file_name);
        ObjectOutputStream readObj = new ObjectOutputStream(fOut);
        List<Client> ref;
        for (int i = 0; i < 27; ++i) {
            ref = agenda.getClients(i);
            for (Client client : ref) {
                readObj.writeObject(client);
            }
        }
        readObj.close();
    }
/*
    public void LerArquivo() {
        try {
            FileReader leitura = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(leitura);
            String linha = "";
            while((linha = br.readLine()) != null){
                System.out.println(linha);
            }
            leitura.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Erro na leitura");
        }
    }

 */


    public void Read(Agenda agenda) throws IOException, ClassNotFoundException {
        FileWriter fw = new FileWriter(file, true);
        FileInputStream fIn = new FileInputStream(file_name);
        ObjectInputStream readObj = new ObjectInputStream(fIn);
        while (readObj.available() != 0){
            fw.write(readObj.toString());
            Object ref = readObj.readObject();
            Client tmp = (Client) ref;
            agenda.add(tmp);
        }
        readObj.close();
        fw.close();
    }
}
