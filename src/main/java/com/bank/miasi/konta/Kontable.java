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

    public BigDecimal getStan();

    public TypKonta getTyp();

    public List<OperacjaBankowa> getHistoria(Date odKiedy, Date doKiedy);

    public String getNumer();

    public Klient getWlasciciel();

    public void wplata(OperacjaBankowa operacjaBankowa) throws NiewspieranaOperacja;

    public void addHistoriaOperacji(OperacjaBankowa operacjaBankowa);

    public Bank getBank();

    public void printRaport(Raport raport);

    public void setInitialAmount(BigDecimal initialAmount);

}
