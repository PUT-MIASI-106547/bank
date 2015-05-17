package com.bank.miasi.reports;

import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.konta.Kontable;

public interface Raport {

    void addRow(OperacjaBankowa aThis);

    void addHeader(Kontable konto);

    void print();
}
