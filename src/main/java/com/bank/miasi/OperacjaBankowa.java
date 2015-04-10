package com.bank.miasi;

import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.konta.Konto;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.kir.Przesylka;
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
    private Konto odKogo;
    private Konto doKogo;
    private Date data;

    private OperacjaBankowa() {

    }

    public static void wykonajOperacje(ManagerKIR managerKIR, BigDecimal kwota,
            TypOperacji typOperacji, String tytul, Konto doKogo, Konto odKogo) throws NiewspieranaOperacja {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(typOperacji, kwota, tytul, odKogo, doKogo, new Date());
        doKogo.wplata(operacjaBankowa);
        if (odKogo != null) {
            odKogo.wplata(operacjaBankowa.reverse());
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

    public Konto getOdKogo() {
        return odKogo;
    }

    public Konto getDoKogo() {
        return doKogo;
    }

    public Date getData() {
        return data;
    }

    private OperacjaBankowa(TypOperacji typOperacji, BigDecimal kwota, String tytul, Konto odKogo, Konto doKogo, Date data) {
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

}
