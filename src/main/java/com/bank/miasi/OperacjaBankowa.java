package com.bank.miasi;

import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.kir.Przesylka;
import com.bank.miasi.konta.Kontable;
import com.bank.miasi.service.visitator.Raport;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Krzysztof
 */
public class OperacjaBankowa {

    private TypOperacji typOperacji;
    private BigDecimal kwota;
    private String tytul;
    private Kontable odKogo;
    private Kontable doKogo;
    private Date data;

    private OperacjaBankowa() {
    }

    public static void wykonajOperacje(ManagerKIR managerKIR, BigDecimal kwota,
            TypOperacji typOperacji, String tytul, Kontable doKogo, Kontable odKogo) throws NiewspieranaOperacja {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(typOperacji, kwota, tytul, odKogo, doKogo, new Date());
        doKogo.wplata(operacjaBankowa.reverse());
        if (odKogo != null) {
            odKogo.wplata(operacjaBankowa);
        }
        if (doKogo.getBankId() != managerKIR.getBankId()) {
            managerKIR.dodajPaczkeDoWyslania(doKogo.getBankId(), Przesylka.getPrzesylkaFromOperacje(operacjaBankowa));
        }
    }

    public TypOperacji getTypOperacji() {
        return typOperacji;
    }

    public BigDecimal getKwota() {
        return typOperacji.getKwota(kwota);
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

    private OperacjaBankowa(TypOperacji typOperacji, BigDecimal kwota, String tytul, Kontable odKogo, Kontable doKogo, Date data) {
        this.typOperacji = typOperacji;
        this.kwota = kwota;
        this.tytul = tytul;
        this.odKogo = odKogo;
        this.doKogo = doKogo;
        this.data = data;
    }

    private OperacjaBankowa reverse() {
        return new OperacjaBankowa(typOperacji.getReverse(), kwota, tytul, odKogo, doKogo, data);
    }

    public void addRowToRaport(Raport visitator) {
        visitator.addRow(this);
    }
}
