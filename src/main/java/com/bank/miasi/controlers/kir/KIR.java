package com.bank.miasi.controlers.kir;

import com.bank.miasi.model.kir.Paczka;

import java.util.List;
import java.util.UUID;

public interface KIR {

    UUID zaloguj(int idBanku, String haslo);

    void wyloguj(UUID idSesji);

    List<Paczka> sciagnijPaczkiDoBanku(UUID idSesji);

    void odbierzPaczkiZBanku(List<Paczka> listaPaczek, UUID idSesji);
}
