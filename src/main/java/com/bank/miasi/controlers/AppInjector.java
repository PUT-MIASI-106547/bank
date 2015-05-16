package com.bank.miasi.controlers;

import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.controlers.kir.SymulatorKIR;
import com.bank.miasi.services.*;
import com.bank.miasi.services.api.*;
import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the services to implementation class
        bind(KIR.class).to(SymulatorKIR.class);
        bind(Autoryzator.class).to(SimpleAutoryzator.class);
        bind(KlientService.class).to(KlientServiceImpl.class);
        bind(KontoService.class).to(KontoServiceImpl.class);
        bind(OperacjaBankowaService.class).to(OperacjaBankowaServiceImpl.class);
    }

}