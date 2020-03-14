package br.com.fatec.lista1.model;

import java.util.Date;

public class Purchase {
        private static int ID;
        private int id;
        private Client client_;
        private String date_;
        private double value_;
        private String products_;
        private String[] availableProducts = {"Barba Rubra", "Pó para Barba", "Shampoo Lisos", "Shampoo anticaspa",
                "Shampoo Ondulados", "Shampoo Crespos", "Condicionador Lisos", "Condicionador Ondulados",
                "Condicionador Crespos", "Esmalte", "Gel", "Creme para pentear"};
        private String services_;
        private String payment;

        public Purchase() {
                ID++;
                id = ID;
                client_ = null;
                date_ = new Date().toString(); // A data será sempre o dia atual.
                value_ = 0.0;
                products_ = "";
                services_ = "";
                payment = "dinheiro";
        }

        public void print() {
                if (client_ != null) {
                        System.out.println("\nid: " + id + "\nNome: " + client_.getName_()
                                + "\nData: " + date_ + "\nProdutos: " + products_
                                + "\nServiços: " + services_ + "\nMétodo de pagamento: " + payment
                                + "\nValor: R$" + value_);
                } else {

                        System.out.println("\nid: " + id + "\nNome: " + ""
                                + "\nData: " + date_ + "\nProdutos: " + products_
                                + "\nServiços: " + services_ + "\nMétodo de pagamento: " + payment
                                + "\nValor: R$" + value_);
                }
        }
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

        public void addProducts(String produto) {
                products_ = produto;
        }
        public void addProductsByIndex(int pIndex) {
                products_ += availableProducts[pIndex] + ", ";
        }

        public String getServices() {
                return services_;
        }

        public void addServices(String servico) {
                services_ = servico;
        }

        public String getPaymentMethod_() {
                return payment;
        }

        public void setPaymentMethod_(String method) {
                this.payment = method;
        }

        public int getId() { return id; }

        public String[] getAvailableProducts() {
                return availableProducts;
        }
}
