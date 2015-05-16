package com.bank.miasi.model.konta;

import com.bank.miasi.model.Klient;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.BlednaKwota;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.typy.TypKonta;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.controlers.kir.Bank;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class KontoBankowe extends Konto {

    public KontoBankowe(Bank bank, TypKonta typ, String numer, Klient wlasciciel) {
        super(bank, typ, numer, wlasciciel);
    }

    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
        BigDecimal ile = operacjaBankowa.getKwota();
        if (ile.signum() == 0) {
            throw new BlednaKwota();
        }
        if (validAmount(ile)) {
            throw new NieWystarczajacoSrodkow();
        }
        stan = stan.add(ile);
        historia.add(operacjaBankowa);
    }

}