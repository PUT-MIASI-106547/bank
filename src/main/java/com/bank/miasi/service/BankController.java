package com.bank.miasi.service;

import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.test.SymulatorZewnetrznegoKIR;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class BankController {

    
    public static void main(String[] args) {
        Klient krzychu = new Klient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        Klient jakub = new Klient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        KontoBankowe nadawca = new KontoBankowe(new KontoWygodne(), "1111", krzychu);
        KontoBankowe odbiorca = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        try {
            ManagerKIR managerKIR = new ManagerKIR(new SymulatorZewnetrznegoKIR(), 4, "test");
            OperacjaBankowa.wykonajOperacje(managerKIR,BigDecimal.valueOf(120.10), new Wplata(), "tt", odbiorca, null);
//            OperacjaBankowa.wykonajOperacje(managerKIR,BigDecimal.valueOf(120.10), new PrzelewWychodzacy(), "tt", nadawca, odbiorca);
        } catch (NiewspieranaOperacja ex) {
            ex.printStackTrace();
        }
    }
}
