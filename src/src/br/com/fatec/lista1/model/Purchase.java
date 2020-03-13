package br.com.fatec.lista1.model;

import java.util.Date;

public class Purchase {
        private static int ID;
        private int id;
        private Client client_;
        private String date_;
        private double value_;
        private String products_;
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
                        System.out.printf("\n%4s|%30s|%30s|", id, client_.getName_(), date_);
                        System.out.printf("%40s|%40s|%10s|%5.2f", products_, services_, payment, value_);
                } else {
                        System.out.printf("\n%4s|%30s|%30s|", id, "", date_);
                        System.out.printf("%40s|%40s|%10s|%5.2f", products_, services_, payment, value_);
                }
        }
        public void setClient(Client client) {
                client_ = client;
        }

        public String getClient() {
                if (client_ != null) {
                        return client_.getName_();
                } else {
                        return "";
                }
        }

        public String getDate_() {
                return date_.toString();
        }

        public void setDate_(String date) {
                this.date_ = date_;
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
}
