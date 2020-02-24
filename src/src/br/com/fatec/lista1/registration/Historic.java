package br.com.fatec.lista1.registration;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Historic {
    private Purchase firstPurchase_;
    private Purchase lastPurchase_;
    private List<Purchase> allPurchases_;

    public Historic() {
        firstPurchase_ = null;
        lastPurchase_ = null;
        allPurchases_ = new LinkedList<Purchase>();
        allPurchases_.add(firstPurchase_);
    }

    public Historic(Purchase primeiraCompra) {
        firstPurchase_ = primeiraCompra;
        lastPurchase_ = primeiraCompra;
        allPurchases_ = new LinkedList<Purchase>();
        allPurchases_.add(firstPurchase_);
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
