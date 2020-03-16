/*
        This file is part of AgendaGrupoWorldBeauty.

        AgendaGrupoWorldBeauty is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        AgendaGrupoWorldBeauty is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <https://www.gnu.org/licenses/>.

 */
package br.com.fatec.lista1.model;

import br.com.fatec.lista1.controller.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Report {
        private Agenda agenda_;
        private Historic historic;
        // Inicializa o relatório baseando-se na classe.
        public Report(Agenda agenda, Historic historico) {
                this.agenda_ = agenda;
                this.historic = historico;
        }
        // Salva o arquivo
        public void getReportFile() throws IOException {
                Controller ctrl = new Controller();
                String fileName, filePath = "usrFiles//rel";
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyy");
                // O nome do arquivo criado será rel_ + a data atual no formato dia_mes_ano
                // com extensão .txt
                // Exemplo: rel_10_03_2020.txt
                fileName = "rel_" + dateFormat.format(new Date()) + ".txt";
                File file = new File(filePath + "//" + fileName);
                File dir = new File(filePath);
                if (!dir.exists()) {
                        if (!dir.mkdirs()) ctrl.err("Criação das pastas", true);
                        if (!file.createNewFile()) ctrl.err("Criação do arquivo", true);
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
                return "\nGerando relatório para o dia: " + day.toString()  + '\n'
                        + "Total de Clientes cadastrados: " + agenda_.size() + '\n'
                        + "Idade média de todo o público da unidade: " + getMediumAge(false, "")  + '\n'
                        + "Idade média do público masculino: " + getMediumAgeMale() + '\n'
                        + "Idade média do público feminino : " + getMediumAgeFemale() + '\n'
                        + "Idade média do público de gênero não binário: " + getMediumAgeNotBinaries() + '\n'
                        + "Produto mais consumido: " + mostConsumedProduct(false, "") + '\n'
                        + "Produto mais consumido pelo público feminino: "
                        + mostConsumedProduct(true, "feminino") + '\n'
                        + "Produto mais consumido pelo público masculino: "
                        + mostConsumedProduct(true, "masculino") + '\n'
                        + "Produto mais consumido pelo público de gênero não binário: "
                        + mostConsumedProduct(true, "não binário") + '\n';
        }
        // Retorna a idade média do público.
        private int getMediumAge(boolean byGender, String gender) {
                int ageCounter = 0, sizeCounter = 0;
                for (int i = 0; i < 27; ++i) {
                        List<Client> ref = agenda_.getClients(i);
                        for (Client client : ref) {
                                if (client.getAge() > 0) {
                                        if (byGender) {
                                                if (client.getGender_().equals(gender)) {
                                                        ageCounter += client.getAge();
                                                        sizeCounter++;
                                                }
                                        } else {
                                                ageCounter += client.getAge();
                                                sizeCounter++;
                                        }
                                }
                        }
                }
                if (sizeCounter != 0) return ageCounter/sizeCounter;
                else return 0;
        }
        // Retorna a idade média para clientes do gênero feminino.
        private int getMediumAgeFemale() {
                return getMediumAge(true, "feminino");
        }
        // Retorna a idade média para clientes do gênero masculino.
        private int getMediumAgeMale() {
                return getMediumAge(true, "masculino");
        }
        // Retorna a idade média para clientes de gênero não binário.
        private int getMediumAgeNotBinaries() {
                return getMediumAge(true, "não binário");
        }
        // Define qual o produto mais consumido
        private String mostConsumedProduct(boolean byGender, String gender) {
                // cria uma compra para usar de referência para obter os produtos
                Purchase p = new Purchase();
                // produtos obtidos
                String[] products = p.getAvailableProducts();
                // usa a estrutura do map para armazenar o produtos com maiores igualdades
                Map<String, Integer> mapCounter = new HashMap<>();
                List<Purchase> ref = historic.getList();
                for (Purchase compra : ref) {
                        if (byGender) {
                                if (compra.getClientRef().getGender_().equals(gender)) {
                                        for (String product : products) {
                                                if (compra.getProducts().contains(product)) {
                                                        mapCounter.merge(product, 1, Integer::sum);
                                                }
                                        }
                                }
                        } else {
                                for (String product : products) {
                                        if (compra.getProducts().contains(product)) {
                                                mapCounter.merge(product, 1, Integer::sum);
                                        }
                                }
                        }
                }
                // ordena o map pelo valor, para saber quais produtos foram mais consumidos
                Map<String,Integer> sorted = mapCounter.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                //
                // Buscando o último valor
                //
                List<String> vl = new ArrayList<>();
                // Varre o map ordenado colocando os elementos na lista, assim o último
                // elemento da lista vai ser o de maior valor
                for (Map.Entry<String, Integer> i : sorted.entrySet()) {
                        vl.add(i.getKey());
                }
                if (vl.size() > 0) {
                        return vl.get(vl.size()-1);
                }
                return "";
        }
}
