package com.bank.miasi.service;

import com.bank.miasi.kir.Bank;
import com.bank.miasi.kir.KIR;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Krzysztof
 */
public class Banki {

    private static Map<String, Bank> banki = new HashMap<>();
    private static final String ALIOR_BANK = "aliorBank";
    private static final String WBK = "wbk";
    private static final String KB = "kb";
    private static KIR kirInstance = new SymulatorZewnetrznegoKIR();

    public static Bank getAliorBank() {
        if (!banki.containsKey(ALIOR_BANK)) {
            banki.put(ALIOR_BANK, new Bank(4, "Alior Bank"));
        }
        return banki.get(ALIOR_BANK);
    }

    public static Bank getWBK() {
        if (!banki.containsKey(WBK)) {
            banki.put(WBK, new Bank(5, "WBK"));
        }
        return banki.get(WBK);
    }

    public static Bank getKB() {
        if (!banki.containsKey(KB)) {
            banki.put(KB, new Bank(6, "KB"));
        }
        return banki.get(KB);
    }

    public static KIR getKirInstance() {
        return kirInstance;
    }

    public static void sendElixir() {
        for (Bank bank : banki.values()) {
            bank.wyslijPaczki();
        }
        for (Bank bank : banki.values()) {
            bank.pobierzPaczki();
        }
    }
}
