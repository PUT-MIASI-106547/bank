package com.bank.miasi.controlers.providers;

import com.bank.miasi.model.konta.typy.TypKonta;
import com.bank.miasi.model.OdsetkiKalkulator;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Krzysztof
 */
public class OdsetkiServiceProvider implements Provider<OdsetkiKalkulator> {

    private Map<String, OdsetkiKalkulator> typy = new HashMap<>();

    @Inject
    public OdsetkiServiceProvider(AccountTypeProvider accountTypeProvider) {
        for (Map.Entry<String, TypKonta> typKonta : accountTypeProvider.getTypyList()) {
            typy.put(typKonta.getKey(), new OdsetkiKalkulator(typKonta.getValue()));
        }
    }

    public OdsetkiKalkulator getInstance(String name) {
        return typy.get(name);
    }

    public Collection<OdsetkiKalkulator> getList() {
        return typy.values();
    }
}
