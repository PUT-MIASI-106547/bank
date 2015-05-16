package com.bank.miasi.controlers;

import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.controlers.kir.SymulatorKIR;
import com.bank.miasi.services.Autoryzator;
import com.bank.miasi.services.SimpleAutoryzator;
import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the services to implementation class
        bind(KIR.class).to(SymulatorKIR.class);
        bind(Autoryzator.class).to(SimpleAutoryzator.class);
    }

}