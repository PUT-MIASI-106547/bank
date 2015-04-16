package com.bank.miasi.service;

import com.bank.miasi.service.visitator.AllRaport;
import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.kir.ManagerKIR;
import com.bank.miasi.konta.Kontable;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.KontoBankoweZDebetem;
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
        Kontable konto1 = new KontoBankowe(new KontoWygodne(), "1111", krzychu);
        Kontable konto2 = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        Kontable konto3 = new KontoBankowe(new KontoWygodne(), "1111", jakub);
        Kontable konto4 = new KontoBankoweZDebetem(new KontoBankowe(new KontoWygodne(), "1111", jakub), new BigDecimal(5000));
        try {
            ManagerKIR managerKIR = new ManagerKIR(new SymulatorZewnetrznegoKIR(), 4, "test");
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(12000.10), new Wplata(), "tt", konto1, null);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(500.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(1200.00), new PrzelewWychodzacy(), "tt", konto3, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(7000.00), new PrzelewWychodzacy(), "tt", konto4, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(120.00), new PrzelewWychodzacy(), "tt", konto4, konto2);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(7000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
            OperacjaBankowa.wykonajOperacje(managerKIR, BigDecimal.valueOf(10.00), new PrzelewWychodzacy(), "tt", konto1, konto2);
            konto1.printRaport(new AllRaport());
            konto1.printRaport(new ShortRaport());
            konto1.printRaport(new TypeRaport(new PrzelewPrzychodzacy()));
            System.out.println("Stany końcowe:");
            System.out.println("konto1: " + konto1.getStan());
            System.out.println("konto2: " + konto2.getStan());
            System.out.println("konto3: " + konto3.getStan());
            System.out.println("konto4: " + konto4.getStan());
            System.out.println("Razem: " + konto1.getStan().add(konto2.getStan()).add(konto3.getStan()).add(konto4.getStan()));
        } catch (NiewspieranaOperacja ex) {
            ex.printStackTrace();
        }
    }
}
