package com.bank.miasi.test;

import com.bank.miasi.kir.*;
import com.bank.miasi.service.Beans;
import java.math.BigDecimal;
import java.util.Random;

public class SymulacjaPaczki {

    public static Paczka zasymulujStanPaczki() {

        Random generator = new Random();
        int idBankuOdbiorcy = generator.nextInt(10);
        int idBankuNadawcy = generator.nextInt(10);
        BigDecimal kwota = new BigDecimal(generator.nextInt(10000));
        return new Paczka(Beans.getAliorBank(), Beans.getWBK(), kwota,
                "test dla id banku odbiorcy: " + idBankuOdbiorcy,
                "test dla id banku odbiorcy: " + idBankuOdbiorcy
                + ",id banku nadawcy: " + idBankuNadawcy + ",kwoty: " + kwota,
                "1232131313123", "31414142141414");
    }
}
