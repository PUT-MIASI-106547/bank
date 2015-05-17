package com.bank.miasi;

import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.controlers.providers.AccountTypeProvider;
import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.bank.miasi.model.Klient;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.konta.KontoBankowe;
import com.bank.miasi.model.konta.KontoBankoweZDebetem;
import com.bank.miasi.model.konta.Lokata;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.Date;

public class TestUtil {
    @Inject
    private OperationTypeProvider operationTypeProvider;
    @Inject
    private AccountTypeProvider accountTypeProvider;

    public OperacjaBankowa createOperacjaBankowa(Kontable odKogo, Kontable doKogo) {
        return createOperacjaBankowa(odKogo, doKogo, new BigDecimal(1000));
    }

    public OperacjaBankowa createOperacjaBankowa(Kontable odKogo, Kontable doKogo, BigDecimal kwota) {
        return new OperacjaBankowa(operationTypeProvider.getInstance(Constants.PRZELEW_PRZYCHODZACY), kwota, "tytul", odKogo, doKogo, new Date());
    }

    public KontoBankowe createKontoBankowe(Bank bank, Klient klient) {
        return new KontoBankowe(bank, accountTypeProvider.getInstance(Constants.KONTO_OPTYMALSNE), "112" + klient.getPesel(), klient);
    }

    public KontoBankoweZDebetem createKontoBankoweZDebetem(Kontable konto, BigDecimal debet) {
        return new KontoBankoweZDebetem(konto, debet);
    }

    public Klient createKlient(int i) {
        return new Klient("klient" + i, "nazwisko" + i, "ccc", "pesel" + i, "nip", "783874334", BigDecimal.valueOf(4000.00));
    }

    public Bank createBank(int id, KIR kir) {
        return new Bank(id, "BANK_" + id, kir);
    }

    public OperacjaBankowa createOperacjaBankowaWplata(Kontable odKogo, Kontable doKogo, BigDecimal kwota) {
        return new OperacjaBankowa(operationTypeProvider.getInstance(Constants.WPLATA), kwota, "tytul", odKogo, doKogo, new Date());

    }

    public OperacjaBankowa createOperacjaBankowaWyplata(Kontable odKogo, Kontable doKogo, BigDecimal kwota) {
        return new OperacjaBankowa(operationTypeProvider.getInstance(Constants.WYPLATA), kwota, "tytul", odKogo, doKogo, new Date());

    }

    public Lokata createLokata(Bank bank, Klient klient) {
        return new Lokata(bank, accountTypeProvider.getInstance(Constants.LOKATA_OPTYMALSNA), "112" + klient.getPesel(), klient, new BigDecimal(1000));
    }

}
