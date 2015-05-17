package com.bank.miasi.controlers.providers;

import com.bank.miasi.model.konta.typy.AccountType;
import com.bank.miasi.model.OdsetkiKalkulator;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OdsetkiKalkulatorProvider implements Provider<OdsetkiKalkulator> {

    private Map<String, OdsetkiKalkulator> typy = new HashMap<>();

    @Inject
    public OdsetkiKalkulatorProvider(Provider<AccountType> accountTypeProvider) {
        for (Object mapEntry : accountTypeProvider.getMapSet()) {
            Map.Entry<String, AccountType> typKontaEntry = (Map.Entry<String, AccountType>) mapEntry;
            typy.put(typKontaEntry.getKey(), new OdsetkiKalkulator(typKontaEntry.getValue()));
        }
    }

    public OdsetkiKalkulator getInstance(String name) {
        return typy.get(name);
    }

    public Collection<OdsetkiKalkulator> getList() {
        return typy.values();
    }

    @Override
    public Set getMapSet() {
        return typy.entrySet();
    }
}
