package com.bank.miasi.service.visitator;

import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.konta.Konto;

/**
 *
 * @author inf106547
 */
public interface Raport {

    public void addRow(OperacjaBankowa aThis);

    public void addHeader(Konto konto);

    public void print();
}
