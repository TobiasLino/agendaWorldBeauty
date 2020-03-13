package br.com.fatec.lista1.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Purchase {
        private Client client_;
        private Date date_;
        private double value_;
        private List<String> products_;
        private List<String> services_;
        private String payment;

        public Purchase() {
                client_ = null;
                date_ = new Date(); // A data será sempre o dia atual.
                value_ = 0.0;
                products_ = new LinkedList<>();
                services_ = new LinkedList<>();
                payment = "dinheiro";
        }

        public void print() {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                System.out.printf("%40s|%10s|%50s|%50s|%10s\n", client_.getName_(), df.format(date_),
                        products_, services_, payment);
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

        public void setDate_(Date date_) {
                this.date_ = date_;
        }

        public double getValue_() {
                return value_;
        }

        public void setValue_(double value_) {
                this.value_ = value_;
        }

        public List<String> getProducts() {
                return products_;
        }

        public void addProducts(String produto) {
                products_.add(produto);
        }

        public List<String> getServices() {
                return services_;
        }

        public void addServices(String servico) {
                services_.add(servico);
        }

        public String getPaymentMethod_() {
                return payment;
        }

        public void setPaymentMethod_(String method) {
                this.payment = method;
        }
}
