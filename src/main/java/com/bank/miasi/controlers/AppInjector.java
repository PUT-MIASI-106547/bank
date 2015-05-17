package com.bank.miasi.controlers;

import com.bank.miasi.chain.ValidatorFactory;
import com.bank.miasi.chain.ValidatorFactoryImpl;
import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.controlers.kir.KIR;
import com.bank.miasi.controlers.kir.KIRImpl;
import com.bank.miasi.controlers.providers.*;
import com.bank.miasi.model.OdsetkiKalkulator;
import com.bank.miasi.model.konta.typy.AccountType;
import com.bank.miasi.model.operacje.OperationType;
import com.bank.miasi.services.KlientServiceImpl;
import com.bank.miasi.services.KontoServiceImpl;
import com.bank.miasi.services.OperacjaBankowaServiceImpl;
import com.bank.miasi.services.SimpleAutoryzator;
import com.bank.miasi.services.api.Autoryzator;
import com.bank.miasi.services.api.KlientService;
import com.bank.miasi.services.api.KontoService;
import com.bank.miasi.services.api.OperacjaBankowaService;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the services to implementation class
        bind(KIR.class)
                .to(KIRImpl.class);
        bind(Provider.class)
                .to(OperationTypeProvider.class);
        bind(new TypeLiteral<Provider<OperationType>>() {
        }).to(OperationTypeProvider.class);
        bind(new TypeLiteral<Provider<OdsetkiKalkulator>>() {
        }).to(OdsetkiKalkulatorProvider.class);
        bind(new TypeLiteral<Provider<AccountType>>() {
        }).to(AccountTypeProvider.class);
        bind(new TypeLiteral<Provider<Bank>>() {
        }).to(BankProvider.class);
        bind(Autoryzator.class)
                .to(SimpleAutoryzator.class);
        bind(KlientService.class)
                .to(KlientServiceImpl.class);
        bind(KontoService.class)
                .to(KontoServiceImpl.class);
        bind(OperacjaBankowaService.class)
                .to(OperacjaBankowaServiceImpl.class);
        bind(ValidatorFactory.class)
                .to(ValidatorFactoryImpl.class);
    }

}