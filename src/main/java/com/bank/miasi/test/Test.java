package com.bank.miasi.test;

import com.bank.miasi.Klient;
import com.bank.miasi.Odsetki;
import com.bank.miasi.OdsetkiStateLokataOptymalna;
import com.bank.miasi.OdsetkiStateLokataRoczna;
import com.bank.miasi.kir.KIR;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.kir.Paczka;
import com.bank.miasi.konta.Lokata;
import com.bank.miasi.konta.typy.LokataOptymalna;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Klient klient = new Klient("Jakub", "Sobiesi", "ul Lala 2", "92081211697", "778239345", "606447714", new BigDecimal(2000.0));
        Lokata lokata = new Lokata(new LokataOptymalna(), "82339234", klient, new BigDecimal(10000.0));

        Odsetki od = new Odsetki(new OdsetkiStateLokataOptymalna());
        BigDecimal oprocentowanie = od.obliczOdsetki(lokata.getStan(), lokata.getTyp());
        System.out.println(oprocentowanie);
        od = new Odsetki(new OdsetkiStateLokataRoczna());
        BigDecimal oprocentowanie2 = od.obliczOdsetki(lokata.getStan(), lokata.getTyp());
        System.out.println(oprocentowanie2);
    }
}
