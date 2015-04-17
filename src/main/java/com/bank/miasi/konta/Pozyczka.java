package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.exceptions.BlednaKwota;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.Bank;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class Pozyczka extends Konto {

    public Pozyczka(Bank bank, TypKonta typ, String numer, Klient wlasciciel, BigDecimal kwota) {
        super(bank, typ, numer, wlasciciel);
        stan = kwota;
    }

    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
        BigDecimal ile = operacjaBankowa.getKwota();
        if (ile.signum() >= 0) {
            throw new BlednaKwota();
        }
        if (stan.add(ile).signum() < 0) {
            throw new NieWystarczajacoSrodkow();
        }
        stan = stan.add(ile);
        historia.add(operacjaBankowa);
    }
}
