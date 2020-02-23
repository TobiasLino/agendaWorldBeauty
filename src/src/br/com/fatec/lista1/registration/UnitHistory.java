package br.com.fatec.lista1.registration;

import java.util.LinkedList;
import java.util.List;

/*
 * Histórico geral da unidade.
 * Só poderá ser gerado uma única vez.
 */
public class UnitHistory extends Historic {
    private List<Purchase> allPuchases_;

    public UnitHistory(Purchase primeiraVenda) {
        firstPurchase_ = primeiraVenda;
        lastPurchase_ = primeiraVenda;
        allPuchases_ = new LinkedList<Purchase>();
        allPuchases_.add(firstPurchase_);
    }

    @Override
    public void Add(Purchase novaEntrada) {
        lastPurchase_ = novaEntrada;
        allPuchases_.add(novaEntrada);
    }

    @Override
    public Purchase GetFirst() {
        return firstPurchase_;
    }

    @Override
    public Purchase GetLast() {
        return lastPurchase_;
    }

    @Override
    public void Save() {

    }

    @Override
    public void Print() {

    }
}
