//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.registration;

import java.io.Serializable;

public class Client implements Serializable {
    private String name_;
    private int age_;
    private String birth_;
    private String gender_;		// masculino, feminino e não binário.
    private Phone phone_;
    private Historic historic_;

    public Client() {
        name_ = "";
        age_ = 0;
        birth_ = "";
        gender_ = "";
        phone_ = null;
        historic_ = null;
    }

    public Client(String nome, String nascimento) {
        this.name_ = nome;
        this.birth_ = nascimento;
        historic_ = new Historic();
    }

    public Client(String nome, String nascimento, String genero) {
        this.name_ = nome;
        this.birth_ = nascimento;
        this.gender_ = genero;
    }

    public void Print() {
        String var = "Nome: " + name_ + "\n";
        if (!birth_.equals("null")) {
            var += "Nascimento: " + birth_ + "\n";
        }
        if (!gender_.equals("null ")) {
            var += "Gênero: " + gender_ + "\n";
        }
        if (phone_ != null) {
            var += "Telefone: " + phone_.getNumber_();
        }
        System.out.println(var);
    }

    public String infos() {
        String var = "Nome: " + name_ + "\n";
        if (!birth_.equals("null")) {
            var += "Nascimento: " + birth_ + "\n";
        }
        if (!gender_.equals("null ")) {
            var += "Gênero: " + gender_ + "\n";
        }
        if (phone_ != null) {
            var += "Telefone: " + phone_.getNumber_();
        }
        var += "\n";
        return var;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public int getAge() {
        return age_;
    }

    public void setAge(int idade) {
        age_ = idade;
    }

    public String getBirth_() {
        return birth_;
    }

    public void setBirth_(String birth_) {
        this.birth_ = birth_;
    }

    public String getGender_() {
        return gender_;
    }

    public void setGender_(String gender_) {
        this.gender_ = gender_.toLowerCase();
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
