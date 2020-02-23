package br.com.fatec.lista1.registration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
    private Client client_;
    private Date date_;
    private double value_;
    private String products_;
    private String services_;
    private String paymentMethod_;

    public Purchase() {
        date_ = new Date();
        value_ = 0.0;
        products_ = "";
        services_ = "";
        paymentMethod_ = "dinheiro";
    }

    public void Print() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String printed = "Compra realizada por: " + client_.getName_()
                + "\nData: " +  df.format(date_);
        if (products_ != "") {
            printed += "\nProdutos: " + products_;
        }
        if (services_ != "") {
            printed += "\nServiços: " + services_;
        }
        printed += "\nMétodo de pagamento: " + paymentMethod_;
        System.out.println(printed);
    }

    public Date getDate_() {
        return date_;
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

    public String getProducts_() {
        return products_;
    }

    public void setProducts_(String products_) {
        this.products_ = products_;
    }

    public String getServices_() {
        return services_;
    }

    public void setServices_(String services_) {
        this.services_ = services_;
    }

    public String getPaymentMethod_() {
        return paymentMethod_;
    }

    public void setPaymentMethod_(String paymentMethod_) {
        this.paymentMethod_ = paymentMethod_;
    }
}
