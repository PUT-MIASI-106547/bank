package com.bank.miasi.kir;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Kontable;
import java.math.BigDecimal;

public class Przesylka {

    public static Przesylka getPrzesylkaFromOperacje(OperacjaBankowa operacjaBankowa) {
        return new Przesylka(operacjaBankowa, operacjaBankowa.getOdKogo(), operacjaBankowa.getDoKogo());
    }

    private OperacjaBankowa operacjaBankowa;
    private final Kontable nadawca;
    private final Kontable odbiorca;

    public Przesylka(OperacjaBankowa operacjaBankowa, Kontable nadawca, Kontable odbiorca) {
        this.operacjaBankowa = operacjaBankowa;
        this.odbiorca = odbiorca;
        this.nadawca = nadawca;
    }

    public OperacjaBankowa rozpakuj() {
        final OperacjaBankowa toReturn = operacjaBankowa;
        operacjaBankowa = null;
        return toReturn;
    }

    public Kontable getNadawca() {
        return nadawca;
    }

    public Kontable getOdbiorca() {
        return odbiorca;
    }

}
