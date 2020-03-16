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

public class Client {
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
                historic_ = new Historic();
        }
        // Impressão dos dados em forma de tabela
        public void Print() {
                if (phone_ != null) {
                        System.out.printf("%40s|%3d|%10s|%12s|%17s\n",name_, age_, birth_, gender_, phone_.getNumber_());
                } else {
                        System.out.printf("%40s|%3d|%10s|%12s|%17s\n",name_, age_, birth_, gender_, "");
                }
        }
        // Adiciona a compra realizada direto no histórico do cliente.
        public void addPurchase(Purchase novaCompra) {
                this.historic_.Add(novaCompra);
        }
        /*
         * Métodos getters e setters
         */
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
}
