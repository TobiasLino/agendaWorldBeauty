package br.com.fatec.lista1.registration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private String name_;		// obrigatório
    private Date birth_;
    private String gender_;		// masculino, feminino e não binário.
    private Phone phone_;
    private Historic historic_;

    public Client() {
        name_ = "";
        birth_ = null;
        gender_ = "";
        phone_ = null;
        historic_ = null;
    }
    public Client(String nome, Date nascimento) {
        this.name_ = nome;
        this.birth_ = nascimento;
        historic_ = new Historic();
    }
    public Client(String nome, Date nascimento, String genero) {
        this.name_ = nome;
        this.birth_ = nascimento;
        this.gender_ = genero;
    }

    public void AddPurchase(Purchase novaCompra) {
        historic_.Add(novaCompra);
    }

    public void Print() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String var = "Nome: " + name_ + "\n"
                + "Nascimento: " + df.format(birth_) + "\n"
                + "Gênero: " + gender_ + "\n";
        if (phone_ != null) {
            var += "Telefone: " + phone_.getNumber_();
        }
        System.out.println(var);
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

    public Historic getHistoric_() {
        return historic_;
    }

    public void setHistoric_(Historic historic_) {
        this.historic_ = historic_;
    }

    public boolean isEqual(Client cl) {
        return this.name_.equals(cl.name_);
    }
}
