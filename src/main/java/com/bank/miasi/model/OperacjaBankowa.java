package com.bank.miasi.model;

import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.OperationType;

import java.math.BigDecimal;
import java.util.Date;

public class OperacjaBankowa {

    private OperationType operationType;
    private BigDecimal kwota;
    private String tytul;
    private Kontable odKogo;
    private Kontable doKogo;
    private Date data;

    public OperacjaBankowa(OperationType operationType, BigDecimal kwota, String tytul, Kontable odKogo, Kontable doKogo, Date data) {
        this.operationType = operationType;
        this.kwota = kwota;
        this.tytul = tytul;
        this.odKogo = odKogo;
        this.doKogo = doKogo;
        this.data = data;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getKwota() {
        return operationType.getKwota(kwota);
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
