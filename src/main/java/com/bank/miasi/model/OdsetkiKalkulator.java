package com.bank.miasi.model;

import com.bank.miasi.model.konta.typy.AccountType;

import java.math.BigDecimal;

public class OdsetkiKalkulator {

    private AccountType accountType;

    public OdsetkiKalkulator(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal obliczOdsetki(BigDecimal stanKonta) {
        BigDecimal wynik;
        BigDecimal procent = accountType.getOprocentowanie();

        wynik = stanKonta;
        wynik = wynik.multiply(procent);
        wynik = wynik.multiply(new BigDecimal(accountType.getOkresRozliczeniowy()));
        wynik = wynik.divide(new BigDecimal(36500), 4);

        return wynik;
    }
}
