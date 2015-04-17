package com.bank.miasi;

import com.bank.miasi.operacje.TypOperacji;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.Bank;
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

    public static void wykonajOperacje(BigDecimal kwota,
            TypOperacji typOperacji, String tytul, Kontable doKogo, Kontable odKogo) throws NiewspieranaOperacja {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(typOperacji, kwota, tytul, odKogo, doKogo, new Date());
        if (doKogo != null && odKogo != null) {
            if (odKogo.getBank().getBankId() != doKogo.getBank().getBankId()) {
                if (!typOperacji.isZewnetrzny()) {
                    throw new NiewspieranaOperacja();
                }
                odKogo.wplata(operacjaBankowa);
                odKogo.getBank().dodajPaczkeDoWyslania(doKogo.getBank(), Przesylka.getPrzesylkaFromOperacje(operacjaBankowa.reverse()));
            } else {
                doKogo.wplata(operacjaBankowa.reverse());
                odKogo.wplata(operacjaBankowa);

            }
        } else {
            if (doKogo != null) {
                doKogo.wplata(operacjaBankowa.reverse());
            }
            if (odKogo != null) {
                odKogo.wplata(operacjaBankowa);
            }
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
