package br.com.fatec.lista1.registration;

import java.util.*;

/*
 * O histórico é gerado automaticamente assim que o
 * cliente realizar a primeira compra e a sua
 * próxima compra será adicionada automaticamente após
 * cada compra realizada.
 */
public class CustomerHistory extends Historic {
    private List<Purchase> clientPurchases_;

    public CustomerHistory(Purchase primeiraCompra) {
        firstPurchase_ = primeiraCompra;
        lastPurchase_ = primeiraCompra;
        clientPurchases_ = new LinkedList<Purchase>();
        clientPurchases_.add(firstPurchase_);
    }


    @Override
    public void Add(Purchase novaEntrada) {
        lastPurchase_ = novaEntrada;
        clientPurchases_.add(novaEntrada);
    }

    @Override
    public Purchase GetFirst() {
        return this.firstPurchase_;
    }

    @Override
    public Purchase GetLast() {
        return this.lastPurchase_;
    }

    @Override
    public void Print() {
        int i = 0;
        Purchase tmp;
        do {
            tmp = clientPurchases_.get(i);
            tmp.Print();
            i++;
        } while (i < clientPurchases_.size());
    }
}
