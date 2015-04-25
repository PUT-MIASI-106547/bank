package com.bank.miasi.service;
import com.bank.miasi.kir.Bank;
import com.bank.miasi.kir.KIR;
import com.bank.miasi.konta.typy.*;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.name.Names;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the service to implementation class
        bind(KIR.class).to(SymulatorZewnetrznegoKIR.class);
    }

}