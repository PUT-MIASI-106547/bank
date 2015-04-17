package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.kir.Bank;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.service.visitator.Raport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public abstract class Konto implements Kontable {

    protected BigDecimal stan = BigDecimal.ZERO;
    protected TypKonta typ;
    protected Bank bank;
    protected List<OperacjaBankowa> historia = new ArrayList<>();
    private String numer;
    private Klient wlasciciel;
    private BigDecimal initialAmount = BigDecimal.ZERO;

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    private Konto() {
    }

    public Konto(Bank bank, TypKonta typ, String numer, Klient wlasciciel) {
        this.bank = bank;
        this.typ = typ;
        this.numer = numer;
        this.wlasciciel = wlasciciel;
    }

    @Override
    public BigDecimal getStan() {
        return stan.add(initialAmount);
    }

    @Override
    public TypKonta getTyp() {
        return typ;
    }

    @Override
    public List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy) {
        return Collections.unmodifiableList(historia);
    }

    @Override
    public String getNumer() {
        return numer;
    }

    @Override
    public Klient getWlasciciel() {
        return wlasciciel;
    }

    @Override
    public void addHistoriaOperacji(OperacjaBankowa operacjaBankowa) {
        historia.add(operacjaBankowa);
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public void printRaport(Raport raport) {
        for (OperacjaBankowa operacjaBankowa : historia) {
            operacjaBankowa.addRowToRaport(raport);
        }
        raport.addHeader(this);
        raport.print();
    }

    @Override
    public String toString() {
        return wlasciciel.toString();
    }

    protected boolean validAmount(BigDecimal ile) {
        return stan.add(initialAmount).add(ile).signum() < 0;
    }

}
