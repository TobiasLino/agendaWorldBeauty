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

import java.util.Date;
/*
 * As purchases são a compra do cliente na data referente.
 * Devem ser adicionadas ao histórico da unidade e do cliente.
 */
public class Purchase {
        // Conta a quantidade de compras cadastradas
        static int ID;
        // identifica o id da compra
        private int id;
        private Client client_;
        private String date_;
        private double value_;
        private String products_;
        // Produtos e serviços que os clientes podem consumir na unidade
        String[] availableProducts = {"manicure",
                "pedicure",
                "design de sobrancelhas",
                "corte",
                "pintura de cabelos",
                "remoção de rugas",
                "remoção de manchas na pele",
                "aplicação de botox",
                "tratamento para emagrecimento e redução de medidas",
                "barbearia",
                "modelagem e corte de barba",
                "tratamento para queda",
                "produtos especializados"};
        private String payment;

        public Purchase() {
                ID++;
                id = ID;
                client_ = null;
                date_ = new Date().toString(); // A data será sempre o dia atual.
                value_ = 0.0;
                products_ = "";
                payment = "dinheiro";
        }
        // Imprime as informações da compra
        public void print() {
                String p = "\nid: " + id;
                if (client_ != null) {
                        p += "\nNome: " + client_.getName_();
                } else {
                        p += "\nNome: " + "";
                }
                p += "\nData: " + date_
                        + "\nProdutos: " + products_
                        + "\nMétodo de pagamento: " + payment
                        + "\nValor: R$" + value_;
                System.out.println(p);
        }
        // Adiciona os produtos à compra
        public void addProducts(String produto) {
                products_ = produto;
        }
        public void addProductsByIndex(int pIndex) {
                products_ += availableProducts[pIndex] + ", ";
        }
        /*
         * getters e setters
         */
        public void setClient(Client client) {
                client_ = client;
        }
        public String getClient() {
                if (client_ != null) {
                        return client_.getName_();
                }
                return "";
        }
        public Client getClientRef() {
                if (client_ != null) {
                        return client_;
                }
                return null;
        }
        public String getDate_() {
                return date_;
        }
        public void setDate_(String date) {
                this.date_ = date;
        }
        public double getValue_() {
                return value_;
        }
        public void setValue_(double value_) {
                this.value_ = value_;
        }
        public String getProducts() {
                return products_;
        }
        public String getPaymentMethod_() {
                return payment;
        }
        public void setPaymentMethod_(String method) {
                this.payment = method;
        }
        public int getId() {
                return id;
        }
        public String[] getAvailableProducts() {
                return availableProducts;
        }
}
