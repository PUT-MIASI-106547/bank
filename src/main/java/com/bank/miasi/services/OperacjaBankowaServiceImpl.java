package com.bank.miasi.services;

import com.bank.miasi.chain.ValidatorFactory;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.model.OperacjaBankowa;
import com.bank.miasi.model.kir.Przesylka;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.operacje.OperationType;
import com.bank.miasi.reports.Raport;
import com.bank.miasi.services.api.OperacjaBankowaService;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.Date;

public class OperacjaBankowaServiceImpl implements OperacjaBankowaService {
    @Inject
    private ValidatorFactory validatorFactory;

    @Override
    public void wykonajOperacje(BigDecimal kwota,
                                OperationType operationType, String tytul, Kontable doKogo, Kontable odKogo) throws NiewspieranaOperacja {
        OperacjaBankowa operacjaBankowa = new OperacjaBankowa(operationType, kwota, tytul, odKogo, doKogo, new Date());
        validatorFactory.getFirstValidator().checkValidity(operacjaBankowa);
        if (doKogo != null && odKogo != null) {
            if (odKogo.getBank().getBankId() != doKogo.getBank().getBankId()) {
                if (!operationType.isZewnetrzny()) {
                    throw new NiewspieranaOperacja();
                }
                odKogo.wplata(operacjaBankowa);
                odKogo.getBank().dodajPaczkeDoWyslania(doKogo.getBank(), Przesylka.getPrzesylkaFromOperacje(reverseOperation(operacjaBankowa)));
            } else {
                doKogo.wplata(reverseOperation(operacjaBankowa));
                odKogo.wplata(operacjaBankowa);

            }
        } else {
            if (doKogo != null) {
                doKogo.wplata(reverseOperation(operacjaBankowa));
            }
            if (odKogo != null) {
                odKogo.wplata(operacjaBankowa);
            }
        }
    }

    @Override
    public OperacjaBankowa reverseOperation(OperacjaBankowa operacjaBankowa) {
        return new OperacjaBankowa(operacjaBankowa.getOperationType().getReverse(), operacjaBankowa.getKwotaValue(),
                operacjaBankowa.getTytul(), operacjaBankowa.getOdKogo(), operacjaBankowa.getDoKogo(),
                operacjaBankowa.getData());
    }

    @Override
    public void addRowToRaport(OperacjaBankowa operacjaBankowa, Raport visitator) {
        visitator.addRow(operacjaBankowa);
    }
}
