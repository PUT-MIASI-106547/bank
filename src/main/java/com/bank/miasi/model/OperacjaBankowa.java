package com.bank.miasi.model;

import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.TypOperacji;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Krzysztof
 */
public class OperacjaBankowa {

    private TypOperacji typOperacji;
    private BigDecimal kwota;
    private String tytul;
    private Kontable odKogo;
    private Kontable doKogo;
    private Date data;

    public OperacjaBankowa(TypOperacji typOperacji, BigDecimal kwota, String tytul, Kontable odKogo, Kontable doKogo, Date data) {
        this.typOperacji = typOperacji;
        this.kwota = kwota;
        this.tytul = tytul;
        this.odKogo = odKogo;
        this.doKogo = doKogo;
        this.data = data;
    }


    public TypOperacji getTypOperacji() {
        return typOperacji;
    }

    public BigDecimal getKwota() {
        return typOperacji.getKwota(kwota);
    }

    public BigDecimal getKwotaValue() {
        return kwota;
    }

    public String getTytul() {
        return tytul;
    }

    public Kontable getOdKogo() {
        return odKogo;
    }

    public Kontable getDoKogo() {
        return doKogo;
    }

    public Date getData() {
        return data;
    }


}
