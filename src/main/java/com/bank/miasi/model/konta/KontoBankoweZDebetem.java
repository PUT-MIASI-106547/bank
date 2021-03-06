package com.bank.miasi.model.konta;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.typy.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class KontoBankoweZDebetem implements Kontable {

    private BigDecimal debet = BigDecimal.ZERO;
    private Kontable konto;

    public KontoBankoweZDebetem(Kontable konto, BigDecimal debet) {
        this.konto = konto;
        konto.setInitialAmount(debet);
        this.debet = debet;
    }

    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
        konto.wplata(operacjaBankowa);
    }

    @Override
    public BigDecimal getStan() {
        return konto.getStan().subtract(debet);
    }

    @Override
    public AccountType getTyp() {
        return konto.getTyp();
    }

    @Override
    public List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy) {
        return konto.getHistoria(odKiedy, doKiedy);
    }

    @Override
    public String getNumer() {
        return konto.getNumer();
    }

    @Override
    public Klient getWlasciciel() {
        return konto.getWlasciciel();
    }

    @Override
    public void addHistoriaOperacji(OperacjaBankowa operacjaBankowa) {
        konto.addHistoriaOperacji(operacjaBankowa);
    }

    @Override
    public Bank getBank() {
        return konto.getBank();
    }

    @Override
    public void setInitialAmount(BigDecimal initialAmount) {
        konto.setInitialAmount(initialAmount);
    }

}
