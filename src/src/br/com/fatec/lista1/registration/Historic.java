package br.com.fatec.lista1.registration;

import java.util.Date;

public abstract class Historic {
    protected Purchase firstPurchase_;
    protected Purchase lastPurchase_;

    public abstract void Add(Purchase novaEntrada);

    public abstract Purchase GetFirst();
    public abstract Purchase GetLast();
    // public abstract void Save();
    public abstract void Print();
}
