package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KiviPaperiSakset{

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String toisenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toisenSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return toisenSiirto;
    }
}
