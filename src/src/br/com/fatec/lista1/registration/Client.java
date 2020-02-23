package br.com.fatec.lista1.registration;

import java.util.Date;

public class Client {
    private String name_;		// obrigatório
    private Date birth_;
    private String gender_;		// masculino, feminino e não binário.
    private Phone phone_;
    private CustomerHistory historic_;

    public Client(String nome, Date nascimento) {
        this.name_ = nome;
        this.birth_ = nascimento;
    }
    public Client(String nome, Date nascimento, String genero) {
        this.name_ = nome;
        this.birth_ = nascimento;
        this.gender_ = genero;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public Date getBirth_() {
        return birth_;
    }

    public void setBirth_(Date birth_) {
        this.birth_ = birth_;
    }

    public String getGender_() {
        return gender_;
    }

    public void setGender_(String gender_) {
        this.gender_ = gender_;
    }

    public Phone getPhone_() {
        return phone_;
    }

    public void setPhone_(Phone phone_) {
        this.phone_ = phone_;
    }

    public CustomerHistory getHistoric_() {
        return historic_;
    }

    public void setHistoric_(CustomerHistory historic_) {
        this.historic_ = historic_;
    }

    protected boolean isEqual(Client cl) {
        return this.name_.equals(cl.name_);
    }
}
