//
//  Tobias Lino 2020.
//
package br.com.fatec.lista1.model;

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
                age_ = 1;
                birth_ = "";
                gender_ = "";
                phone_ = null;
                historic_ = null;
        }

        public void Print() {
                if (phone_ != null) {
                        System.out.printf("%40s|%3d|%10s|%12s|%17s\n",name_, age_, birth_, gender_, phone_.getNumber_());
                } else {
                        System.out.printf("%40s|%3d|%10s|%12s|%17s\n",name_, age_, birth_, gender_, "");
                }
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

        public void addPurchase(Purchase novaCompra) {
                this.historic_.Add(novaCompra);
        }
}
