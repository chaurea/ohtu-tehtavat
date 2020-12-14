package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private Tekoaly tekoaly;

    public KPSTekoaly() {
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String toisenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toisenSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return toisenSiirto;
    }

}