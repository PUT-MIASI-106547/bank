package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public abstract class Konto {

    protected BigDecimal stan = BigDecimal.ZERO;
    protected TypKonta typ;
    protected List<OperacjaBankowa> historia = new ArrayList<>();
    private String numer;
    private Klient wlasciciel;

    private Konto() {
    }

    public Konto(TypKonta typ, String numer, Klient wlasciciel) {
        this.typ = typ;
        this.numer = numer;
        this.wlasciciel = wlasciciel;
    }

    public BigDecimal getStan() {
        return stan;
    }

    public TypKonta getTyp() {
        return typ;
    }

    public List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy) {
        return Collections.unmodifiableList(historia);
    }

    public String getNumer() {
        return numer;
    }

    public Klient getWlasciciel() {
        return wlasciciel;
    }

    public abstract void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja;

    public void addHistoriaOperacji(OperacjaBankowa operacjaBankowa) {
        historia.add(operacjaBankowa);
    }
    
    public int getBankId(){
        return Integer.parseInt(numer.substring(0,1));
    }

}
