package com.bank.miasi.service;

import com.bank.miasi.service.visitator.AllRaport;
import com.bank.miasi.Klient;
import com.bank.miasi.OperacjaBankowa;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.konta.Kontable;
import com.bank.miasi.konta.KontoBankowe;
import com.bank.miasi.konta.KontoBankoweZDebetem;
import com.bank.miasi.konta.typy.KontoWygodne;
import com.bank.miasi.operacje.PrzelewPrzychodzacy;
import com.bank.miasi.operacje.PrzelewWychodzacy;
import com.bank.miasi.operacje.Wplata;
import com.bank.miasi.service.visitator.ShortRaport;
import com.bank.miasi.service.visitator.TypeRaport;
import java.math.BigDecimal;

/**
 *
 * @author Krzysztof
 */
public class BankController {

    public static void main(String[] args) {
        Klient krzychu = new Klient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        Klient jakub = new Klient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00));
        Kontable konto1 = new KontoBankowe(Banki.getAliorBank(), new KontoWygodne(), "11112", krzychu);
        Kontable konto2 = new KontoBankowe(Banki.getAliorBank(), new KontoWygodne(), "11113", jakub);
        Kontable konto3 = new KontoBankowe(Banki.getAliorBank(), new KontoWygodne(), "11114", jakub);
        Kontable konto5 = new KontoBankowe(Banki.getWBK(), new KontoWygodne(), "11115", jakub);
        Kontable konto4 = new KontoBankoweZDebetem(new KontoBankowe(Banki.getAliorBank(), new KontoWygodne(), "11111", jakub), new BigDecimal(5000));
        try {
            OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(12000.10), new Wplata(), "tt", konto1, null);
            przelewyWewnetrzne(konto1, konto2, konto3, konto4);
            raporty(konto1, konto2, konto3, konto4);
            przelewyZewnetrzne(konto1, konto2, konto3, konto4, konto5);

        } catch (NiewspieranaOperacja ex) {
            ex.printStackTrace();
        }
    }

    private static void raporty(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4) {
        konto1.printRaport(new AllRaport());
        konto1.printRaport(new ShortRaport());
        konto1.printRaport(new TypeRaport(new PrzelewPrzychodzacy()));
        System.out.println("Stany końcowe:");
        System.out.println("konto1: " + konto1.getStan());
        System.out.println("konto2: " + konto2.getStan());
        System.out.println("konto3: " + konto3.getStan());
        System.out.println("konto4: " + konto4.getStan());
        System.out.println("Razem: " + konto1.getStan().add(konto2.getStan()).add(konto3.getStan()).add(konto4.getStan()));
    }

    private static void przelewyWewnetrzne(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4) throws NiewspieranaOperacja {
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(500.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(1200.00), new PrzelewWychodzacy(), "tt", konto3, konto1);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(7000.00), new PrzelewWychodzacy(), "tt", konto4, konto1);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(120.00), new PrzelewWychodzacy(), "tt", konto4, konto2);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(7000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto2, konto1);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(2000.00), new PrzelewWychodzacy(), "tt", konto3, konto4);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(10.00), new PrzelewWychodzacy(), "tt", konto1, konto2);
    }

    private static void przelewyZewnetrzne(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4, Kontable konto5) throws NiewspieranaOperacja {
        System.out.println("Elixir");
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(500.00), new PrzelewWychodzacy(), "tt", konto5, konto1);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(300.00), new PrzelewWychodzacy(), "tt", konto5, konto1);
        Banki.sendElixir();
        System.out.println("konto5: " + konto5.getStan());
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(700.00), new PrzelewWychodzacy(), "tt", konto4, konto5);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(120.00), new PrzelewWychodzacy(), "tt", konto5, konto2);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(700.00), new PrzelewWychodzacy(), "tt", konto5, konto4);
        Banki.sendElixir();
        System.out.println("konto5: " + konto5.getStan());
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(200.00), new PrzelewWychodzacy(), "tt", konto2, konto5);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(200.00), new PrzelewWychodzacy(), "tt", konto3, konto5);
        OperacjaBankowa.wykonajOperacje(BigDecimal.valueOf(10.00), new PrzelewWychodzacy(), "tt", konto5, konto2);
        System.out.println("konto5: " + konto5.getStan());
        Banki.sendElixir();
        System.out.println("konto5: " + konto5.getStan());

    }
}
