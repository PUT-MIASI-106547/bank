package com.bank.miasi;

import java.util.UUID;

public class Autoryzator {

    private UUID ID;
    private UUID tokenOperacji;

    /**
     * Utworzenie autoryzatora dla klienta
     *
     * @param klient
     * @param haslo
     */
    public Autoryzator(Klient klient, String haslo) {
        UUID hasloUUID = UUID.fromString(haslo);
        UUID gID = UUID.randomUUID();
        if (klient.checkHaslo(hasloUUID)) {
            this.ID = gID;

        }
    }

    /**
     * Utworzenie autoryzatora dla operacji
     *
     * @param operacja
     * @param kod
     */
    public Autoryzator(OperacjaBankowa operacja, String kod) {
        UUID kodUUID = UUID.fromString(kod);
        UUID gID = UUID.randomUUID();
        if (checkTokenOperacji(kodUUID)) {
            this.ID = gID;

        }
    }

    private boolean checkTokenOperacji(UUID token) {
        return (this.tokenOperacji.equals(token));
    }

    public void generateTokenOperacji(int nrTelefonu) {
        this.tokenOperacji = UUID.randomUUID();
        //wyslanie SMSa z tokenem do klienta
        //sendTokenSMS(tokenOperacji,nrTelefonu);

    }
}
