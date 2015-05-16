package com.bank.miasi.model.kir;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;

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
