package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.exceptions.BlednaKwota;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public class Lokata extends Konto{

    public Lokata(TypKonta typ, String numer, Klient wlasciciel, BigDecimal kwota){
        super(typ, numer, wlasciciel);
        stan = kwota;
    }
    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
            throw new NiewspieranaOperacja();
    }

}
