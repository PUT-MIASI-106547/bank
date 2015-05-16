package com.bank.miasi.model.konta;

import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.typy.TypKonta;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.controlers.kir.Bank;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class Lokata extends Konto {

    public Lokata(Bank bank, TypKonta typ, String numer, Klient wlasciciel, BigDecimal kwota) {
        super(bank, typ, numer, wlasciciel);
        stan = kwota;
    }

    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
        throw new NiewspieranaOperacja();
    }

}
