package com.bank.miasi.controlers.providers;

import com.bank.miasi.Constants;
import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.controlers.kir.KIR;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Krzysztof
 */
public class BankProvider implements Provider<Bank> {

    private static Map<String, Bank> banki = new HashMap<>();

    @Inject
    public BankProvider(KIR kir) {
        banki.put(Constants.BANK_ALIOR_BANK, new Bank(4, "Alior Bank", kir));
        banki.put(Constants.BANK_WBK, new Bank(5, "BANK_WBK", kir));
        banki.put(Constants.BANK_KB, new Bank(6, "BANK_KB", kir));
    }

    public Bank getInstance(String name) {
        return banki.get(name);

    }

    public Collection<Bank> getList() {
        return banki.values();
    }
    @Override
    public Set getMapSet() {
        return banki.entrySet();
    }

}
