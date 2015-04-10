package com.bank.miasi.test;

import com.bank.miasi.kir.*;
import java.math.BigDecimal;
import java.util.Random;

public class SymulacjaPaczki extends Paczka {

    public SymulacjaPaczki(int idBankuNadawcy, int idBankuOdbiorcy, BigDecimal kwota, String nazwa, String tytul, String numerKontaOdbiorcy, String numerKontaNadawcy) {
        super(idBankuNadawcy, idBankuOdbiorcy, kwota, nazwa, tytul, numerKontaOdbiorcy, numerKontaNadawcy);
    }

    public static SymulacjaPaczki zasymulujStanPaczki() {

        Random generator = new Random();
        int idBankuOdbiorcy = generator.nextInt(10);
        int idBankuNadawcy = generator.nextInt(10);
        BigDecimal kwota = new BigDecimal(generator.nextInt(10000));
        return new SymulacjaPaczki(idBankuNadawcy, idBankuOdbiorcy, kwota,
                "test dla id banku odbiorcy: " + idBankuOdbiorcy,
                "test dla id banku odbiorcy: " + idBankuOdbiorcy
                + ",id banku nadawcy: " + idBankuNadawcy + ",kwoty: " + kwota,
                "1232131313123", "31414142141414");
    }
}
