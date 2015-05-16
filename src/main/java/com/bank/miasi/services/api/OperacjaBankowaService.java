package com.bank.miasi.services.api;

import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.TypOperacji;
import com.bank.miasi.reports.Raport;

import java.math.BigDecimal;

public interface OperacjaBankowaService {
    void wykonajOperacje(BigDecimal kwota,
                         TypOperacji typOperacji, String tytul, Kontable doKogo, Kontable odKogo) throws NiewspieranaOperacja;

    OperacjaBankowa reverseOperation(OperacjaBankowa operacjaBankowa);

    void addRowToRaport(OperacjaBankowa operacjaBankowa, Raport visitator);
}
