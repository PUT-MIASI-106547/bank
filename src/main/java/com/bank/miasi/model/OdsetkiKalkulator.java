package com.bank.miasi.model;

import com.bank.miasi.model.konta.typy.TypKonta;

import java.math.BigDecimal;

public class OdsetkiKalkulator {

    TypKonta typKonta;

    public OdsetkiKalkulator(TypKonta typKonta) {
        this.typKonta = typKonta;
    }

    public BigDecimal obliczOdsetki(BigDecimal stanKonta) {
        BigDecimal wynik;
        BigDecimal procent = typKonta.getOprocentowanie();

        wynik = stanKonta;
        wynik = wynik.multiply(procent);
        wynik = wynik.multiply(new BigDecimal(typKonta.getOkresRozliczeniowy()));
        wynik = wynik.divide(new BigDecimal(36500), 4);

        return wynik;
    }
}
