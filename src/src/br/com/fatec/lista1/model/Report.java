//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Report {
    private Agenda agenda_;
    // Inicializa o relatório baseando-se na classe.
    public Report(Agenda agenda) {
        this.agenda_ = agenda;
    }
    // Salva o arquivo
    public void getReportFile() throws IOException {
        String fileName, filePath = "usrFiles//rel";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyy");
        fileName = "rel_" + dateFormat.format(new Date()) + ".txt";
        File file = new File(filePath + "//" + fileName);
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(getReport());
            System.out.println("Arquivo <" + fileName + "> Salvo com sucesso.");
            writer.close();
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(getReport());
            System.out.println("Arquivo <" + fileName + "> Salvo com sucesso na pasta: "
                    + filePath);
            writer.close();
        }
    }
    // Visualiza o relatório na tela
    public void printReport() {
        System.out.println(getReport());
    }
    // Retorna as informações do relatório.
    private String getReport() {
        Date day = new Date();
        return "Gerando relatório para o dia: " + day.toString()  + '\n'
                + "Total de Clientes cadastrados: " + agenda_.size() + '\n'
                + "Idade média de todo o público da unidade: " + getMediumAge()  + '\n'
                + "Idade média do público masculino: " + getMediumAgeMale() + '\n'
                + "Idade média do público feminino : " + getMediumAgeFemale() + '\n'
                + "Idade média do público de gênero não binário: " + getMediumAgeNotBinaries() + '\n';
    }
    // Retorna a idade média do público.
    private int getMediumAge() {
        int ageCounter = 0, sizeCounter = 0;
        for (int i = 0; i < 27; ++i) {
            List<Client> ref = agenda_.getClients(i);
            for (Client client : ref) {
                if (client.getAge() > 0) {
                    ageCounter += client.getAge();
                    sizeCounter++;
                }
            }
        }
        if (sizeCounter != 0) return ageCounter/sizeCounter;
        else return 0;
    }
    // Retorna a idade média para clientes do gênero feminino.
    private int getMediumAgeFemale() {
        int ageCounter = 0, sizeCounter = 0;
        for (int i = 0; i < 27; ++i) {
            List<Client> ref = agenda_.getClients(i);
            for (Client client : ref) {
                if (client.getAge() > 0 && client.getGender_().equals("feminino")) {
                    ageCounter += client.getAge();
                    sizeCounter++;
                }
            }
        }
        if (sizeCounter != 0) return ageCounter/sizeCounter;
        else return 0;
    }
    // Retorna a idade média para clientes do gênero masculino.
    private int getMediumAgeMale() {
        int ageCounter = 0, sizeCounter = 0;
        for (int i = 0; i < 27; ++i) {
            List<Client> ref = agenda_.getClients(i);
            for (Client client : ref) {
                if (client.getAge() > 0 && client.getGender_().equals("masculino")) {
                    ageCounter += client.getAge();
                    sizeCounter++;
                }
            }
        }
        if (sizeCounter != 0) return ageCounter/sizeCounter;
        else return 0;
    }
    // Retorna a idade média para clientes de gênero não binário.
    private int getMediumAgeNotBinaries() {
        int ageCounter = 0, sizeCounter = 0;
        for (int i = 0; i < 27; ++i) {
            List<Client> ref = agenda_.getClients(i);
            for (Client client : ref) {
                if (client.getAge() > 0 && client.getGender_().equals("nao binario")) {
                    ageCounter += client.getAge();
                    sizeCounter++;
                }
            }
        }
        if (sizeCounter != 0) return ageCounter/sizeCounter;
        else return 0;
    }
}
