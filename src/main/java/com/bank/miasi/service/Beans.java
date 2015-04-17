package com.bank.miasi.service;

import com.bank.miasi.kir.Bank;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;

/**
 *
 * @author Krzysztof
 */
public class Beans {

    private static Bank aliorBank;
    private static Bank wbk;
    private static Bank kb;

    public static Bank getAliorBank() {
        if (aliorBank == null) {
            aliorBank = new Bank(new SymulatorZewnetrznegoKIR(), 4, "Alior Bank");
        }
        return aliorBank;
    }

    public static Bank getWBK() {
        if (wbk == null) {
            wbk = new Bank(new SymulatorZewnetrznegoKIR(), 5, "WBK");
        }
        return wbk;
    }

    public static Bank getKB() {
        if (kb == null) {
            kb = new Bank(new SymulatorZewnetrznegoKIR(), 6, "KB");
        }
        return kb;
    }
}
