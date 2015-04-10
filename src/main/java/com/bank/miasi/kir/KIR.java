package com.bank.miasi.kir;

import java.util.List;
import java.util.UUID;

public interface KIR {

    public UUID zaloguj(int idBanku, String haslo);

    public void wyloguj(UUID idSesji);

    public List<Paczka> sciagnijPaczkiDoBanku(UUID idSesji);

    public void odbierzPaczkiZBanku(List<Paczka> listaPaczek, UUID idSesji);
}
