package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt()); 
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    @Test
    public void kaksiEriTuotetta() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(15));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    @Test
    public void kaksiSamaaTuotetta() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(10));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    @Test
    public void yksiTuoteLoppu() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    
    @Test
    public void aloitetaanAsiointiNollaa() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    @Test
    public void viiteNumeroKaikille() {
        when(viite.uusi()).thenReturn(2);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("aaaa", "12345");
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }

    
    @Test
    public void poistoKorista() {
        when(viite.uusi()).thenReturn(1);
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.poistaKorista(1);  
        k.tilimaksu("pekka", "12345");
        

        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));   //(String nimi, int viitenumero, String tililta, String tilille, int summ
    }


}