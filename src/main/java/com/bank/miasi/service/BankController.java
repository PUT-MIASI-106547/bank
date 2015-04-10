package com.bank.miasi.service;

import com.bank.miasi.service.visitator.AllRaport;
import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.PrzelewPrzychodzacy;
import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.service.visitator.ShortRaport;
import com.bank.miasi.service.visitator.TypeRaport;
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
        KontoBankowe konto1 = new KontoBankowe(new KontoWygodne(), "1111", krzychu);
        KontoBankowe konto2 = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        KontoBankowe konto3 = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        KontoBankowe konto4 = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        try {
            ManagerKIR managerKIR = new ManagerKIR(new SymulatorZewnetrznegoKIR(), 4, "test");
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(12000.10), new Wplata(), "tt", konto1, null);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(500.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(1200.00), new PrzelewWychodzacy(), "tt", konto3, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(7000.00), new PrzelewWychodzacy(), "tt", konto4, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(120.00), new PrzelewWychodzacy(), "tt", konto4, konto2);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(3000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(10.00), new PrzelewWychodzacy(), "tt", konto1, konto2);
            konto1.printRaport(new AllRaport());
            konto1.printRaport(new ShortRaport());
            konto1.printRaport(new TypeRaport(new PrzelewPrzychodzacy()));
        } catch (NiewspieranaOperacja ex) {
            ex.printStackTrace();
        }
    }
}
