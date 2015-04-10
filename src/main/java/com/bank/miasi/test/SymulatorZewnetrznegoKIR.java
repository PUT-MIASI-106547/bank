package com.bank.miasi.test;

import com.bank.miasi.exceptions.UnauthorizedException;
import com.bank.miasi.kir.KIR;
import com.bank.miasi.kir.Paczka;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SymulatorZewnetrznegoKIR implements KIR {

    Map<Integer, List<Paczka>> listaPaczek = new HashMap<>();
    Map<Integer, String> autoryzowaneBanki = new HashMap<>();
    Map<UUID, Integer> sesje = new HashMap<>();

    public SymulatorZewnetrznegoKIR() {
        autoryzowaneBanki.put(4, "test");
    }

    @Override
    public UUID zaloguj(int idBanku, String haslo) {
        String hasloOczekiwane = autoryzowaneBanki.get(idBanku);
        if (hasloOczekiwane == null || !hasloOczekiwane.equals(haslo)) {
            throw new UnauthorizedException();
        }
        UUID randomUUID = UUID.randomUUID();
        sesje.put(randomUUID, idBanku);
        return randomUUID;
    }

    @Override
    public void wyloguj(UUID idSesji) {
        sesje.remove(idSesji);
    }

    @Override
    public List<Paczka> sciagnijPaczkiDoBanku(UUID idSesji) {
        Integer idBanku = sesje.get(idSesji);
        if (idBanku == null) {
            throw new UnauthorizedException();
        }
        List<Paczka> lista = listaPaczek.get(idBanku);
        listaPaczek.remove(idBanku);
        return lista;
    }

    @Override
    public void odbierzPaczkiZBanku(List<Paczka> wyslanePaczki, UUID idSesji) {
        if (sesje.get(idSesji) == null) {
            throw new UnauthorizedException();
        }
        for (Paczka paczka : wyslanePaczki) {
             List<Paczka> lista = listaPaczek.get(paczka.getIdBankuOdbiorcy());

            if(lista == null) {
            lista = new ArrayList<Paczka>();
            lista.add(paczka);
            listaPaczek.put(paczka.getIdBankuOdbiorcy(), lista);
            } else 
            {
                lista.add(paczka);
                listaPaczek.put(paczka.getIdBankuOdbiorcy(), lista);
            }
        }
    }
    
    public void zasymuluj(KIR kir, int iloscPaczek) {
        List<Paczka> lista = new ArrayList<>();
        for (int i = 0; i < iloscPaczek; i++) {
    
            lista.add(SymulacjaPaczki.zasymulujStanPaczki());
        }
        
        listaPaczek.put(4, lista);
    }
    
}
