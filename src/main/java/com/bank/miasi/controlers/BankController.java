package com.bank.miasi.controlers;

import com.bank.miasi.Constants;
import com.bank.miasi.exceptions.NiewspieranaOperacja;
import com.bank.miasi.controlers.kir.Bank;
import com.bank.miasi.model.konta.Kontable;
import com.bank.miasi.model.konta.KontoBankowe;
import com.bank.miasi.model.konta.KontoBankoweZDebetem;
import com.bank.miasi.model.Klient;
import com.bank.miasi.controlers.providers.AccountTypeProvider;
import com.bank.miasi.controlers.providers.BankProvider;
import com.bank.miasi.controlers.providers.OperationTypeProvider;
import com.bank.miasi.reports.AllRaport;
import com.bank.miasi.reports.ShortRaport;
import com.bank.miasi.reports.TypeRaport;
import com.bank.miasi.services.api.Autoryzator;
import com.bank.miasi.services.api.KlientService;
import com.bank.miasi.services.api.KontoService;
import com.bank.miasi.services.api.OperacjaBankowaService;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Krzysztof
 */
public class BankController {

    @Inject
    private BankProvider bankProvider;
    @Inject
    private AccountTypeProvider accountTypeProvider;
    @Inject
    private OperationTypeProvider operationTypeProvider;
    @Inject
    private KlientService klientService;
    @Inject
    private OperacjaBankowaService operacjaBankowaService;
    @Inject
    private KontoService kontoService;

    private Date programStart = new Date();


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());
        injector.getInstance(Autoryzator.class);
        BankController controller = injector.getInstance(BankController.class);
        controller.run();
    }

    private void run() {

        Klient krzychu = klientService.createKlient("krzychu", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00), "qqq");
        Klient jakub = klientService.createKlient("Jakub", "Pawlak", "ccc", "92012812173", "nip", "783874334", BigDecimal.valueOf(4000.00), "qqq");
        Kontable konto1 = kontoService.createKontoBankowe(bankProvider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "11112", krzychu);
        Kontable konto2 = kontoService.createKontoBankowe(bankProvider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "11113", jakub);
        Kontable konto3 = kontoService.createKontoBankowe(bankProvider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_OPTYMALSNE), "11114", jakub);
        Kontable konto5 = kontoService.createKontoBankowe(bankProvider.getInstance(Constants.BANK_WBK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "11115", jakub);
        Kontable konto4 = kontoService.createKontoBankoweZBebetem(kontoService.createKontoBankowe(bankProvider.getInstance(Constants.BANK_ALIOR_BANK), accountTypeProvider.getInstance(Constants.KONTO_WYGODNE), "11111", jakub), new BigDecimal(5000));
        try {
            operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(12000.10), operationTypeProvider.getInstance(Constants.WPLATA), "tt", konto1, null);
            przelewyWewnetrzne(konto1, konto2, konto3, konto4);
            raporty(konto1, konto2, konto3, konto4);
            przelewyZewnetrzne(konto1, konto2, konto3, konto4, konto5);

        } catch (NiewspieranaOperacja ex) {
            ex.printStackTrace();
        }
    }

    private void raporty(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4) {
        kontoService.printRaport(konto1, new AllRaport(), programStart, new Date());
        kontoService.printRaport(konto1, new ShortRaport(), programStart, new Date());
        kontoService.printRaport(konto1, new TypeRaport(operationTypeProvider.getInstance(Constants.PRZELEW_PRZYCHODZACY)), programStart, new Date());
        System.out.println("Stany końcowe:");
        System.out.println("konto1: " + konto1.getStan());
        System.out.println("konto2: " + konto2.getStan());
        System.out.println("konto3: " + konto3.getStan());
        System.out.println("konto4: " + konto4.getStan());
        System.out.println("Razem: " + konto1.getStan().add(konto2.getStan()).add(konto3.getStan()).add(konto4.getStan()));
    }

    private void przelewyWewnetrzne(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4) throws NiewspieranaOperacja {
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(500.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto2, konto1);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(1200.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto3, konto1);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(7000.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto4, konto1);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(120.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto4, konto2);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(7000.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto3, konto4);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(2000.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto2, konto1);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(2000.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto3, konto4);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(10.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto1, konto2);
    }

    private void przelewyZewnetrzne(Kontable konto1, Kontable konto2, Kontable konto3, Kontable konto4, Kontable konto5) throws NiewspieranaOperacja {
        System.out.println("Elixir");
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(500.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto5, konto1);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(300.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto5, konto1);
        System.out.println("konto5: " + konto5.getStan());
        sendElixir();
        System.out.println("konto5: " + konto5.getStan());
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(700.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto4, konto5);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(120.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto5, konto2);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(700.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto5, konto4);
        System.out.println("konto5: " + konto5.getStan());
        sendElixir();
        System.out.println("konto5: " + konto5.getStan());
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(200.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto2, konto5);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(200.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto3, konto5);
        operacjaBankowaService.wykonajOperacje(BigDecimal.valueOf(10.00), operationTypeProvider.getInstance(Constants.PRZELEW_WYCHODZACY), "tt", konto5, konto2);
        System.out.println("konto5: " + konto5.getStan());
        sendElixir();
        System.out.println("konto5: " + konto5.getStan());

    }

    private void sendElixir() {
        for (Bank bank : bankProvider.getList()) {
            bank.wyslijPaczki();
        }
        for (Bank bank : bankProvider.getList()) {
            bank.pobierzPaczki();
        }
    }
}
