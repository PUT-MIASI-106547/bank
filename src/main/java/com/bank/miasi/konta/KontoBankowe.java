package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.exceptions.NieWystarczajacoSrodkow;
import com.bank.miasi.exceptions.BlednaKwota;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public class KontoBankowe extends Konto {

    private BigDecimal debet = BigDecimal.ZERO;

    public KontoBankowe(TypKonta typ, String numer, Klient wlasciciel) {
        super(typ, numer, wlasciciel);
    }

    @Override
    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja {
        BigDecimal ile = operacjaBankowa.getKwota();
        if (ile.signum() == 0) {
            throw new BlednaKwota();
        }
        if (debet.add(stan.add(ile)).signum() < 0) {
            throw new NieWystarczajacoSrodkow();
        }
        stan = stan.add(ile);
        historia.add(operacjaBankowa);
    }

}
