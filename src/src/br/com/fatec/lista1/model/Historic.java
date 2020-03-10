package br.com.fatec.lista1.model;

import java.util.LinkedList;
import java.util.List;

public class Historic {
    private Purchase firstPurchase_;
    private Purchase lastPurchase_;
    private List<Purchase> allPurchases_;

    public Historic() {
        firstPurchase_ = lastPurchase_ = null;
        allPurchases_ = new LinkedList<Purchase>();
    }

    public Historic(Purchase primeiraCompra) {
        firstPurchase_ = lastPurchase_ = primeiraCompra;
        allPurchases_ = new LinkedList<Purchase>();
        allPurchases_.add(primeiraCompra);
    }

    public void AddFirstPurchase(Purchase primeraCompra) {
        firstPurchase_ = primeraCompra;
        allPurchases_.set(0, primeraCompra);
    }

    public void Add(Purchase novaEntrada) {
        lastPurchase_ = novaEntrada;
        allPurchases_.add(novaEntrada);
    }

    public Purchase GetFirst() {
        return this.firstPurchase_;
    }

    public Purchase GetLast() {
        return this.lastPurchase_;
    }

    public void Print() {
        int i = 0;
        Purchase tmp;
        do {
            tmp = allPurchases_.get(i);
            tmp.Print();
            i++;
        } while (i < allPurchases_.size());
    }
}
