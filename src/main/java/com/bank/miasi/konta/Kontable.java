package com.bank.miasi.konta;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.Bank;
import com.bank.miasi.konta.typy.TypKonta;
import com.bank.miasi.service.visitator.Raport;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Krzysztof
 */
public interface Kontable {

    BigDecimal getStan();

    TypKonta getTyp();

    List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy);

    String getNumer();

    Klient getWlasciciel();

    void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja;

    void addHistoriaOperacji(OperacjaBankowa operacjaBankowa);

    Bank getBank();

    void printRaport(Raport raport);

    void setInitialAmount(BigDecimal initialAmount);

}
