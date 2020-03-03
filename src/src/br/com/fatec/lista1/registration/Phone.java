package br.com.fatec.lista1.registration;

import java.io.Serializable;

public class Phone implements Serializable {
    private String number_;

    public Phone() {
        this.number_ = "";
    }

    public Phone(String numero) {
        this.number_ = numero;
    }

    public String getNumber_() {
        return number_;
    }

    public void setNumber_(String number_) {
        this.number_ = number_;
    }
}