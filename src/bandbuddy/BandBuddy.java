/**
 * 
 */
package bandbuddy;

import java.util.*;

/**
 * BandBuddy-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 15.04.2018
 *
 */
public class BandBuddy {
    private Henkilot henkilot = new Henkilot();
    private final HenkilotJaInstrumentit henkilotJaInstrumentit = new HenkilotJaInstrumentit();
    private final Instrumentit instrumentit = new Instrumentit();


    /**
     * Parametriton konstruktori BandBuddylle
     */
    public BandBuddy() {
    }


    /**
     * Lisää henkilön henkilöt-taulukoon
     * @param henkilo   lisättävä henkilö
     */
    public void lisaa(Henkilo henkilo) {
        henkilot.lisaa(henkilo);
    }
    
    
    /**
     * Lisää instrumentin tietorakenteeseen
     * @param soitin lisättävä instrumentti
     */
    public void lisaa(Instrumentti soitin) {
        instrumentit.lisaa(soitin);
    }
    
    
    /**
     * Lisää henkilön ja instrumentin idn tietorakenteeseen
     * @param soittaja lisättävä henkilö ja sen instrumentti
     */
    public void lisaa(HenkiloJaInstrumentti soittaja) {
        henkilotJaInstrumentit.lisaa(soittaja);
    }

    
    /**
     * @param indeksi   henkilön paikka henkilöt-taulukossa
     * @return henkilö
     */
    public Henkilo getHenkilo(int indeksi) {
        return henkilot.getHenkilo(indeksi);
    }
    
    
    /**
     * @return henkilöt-taulukon koko
     */
    public int getHenkilotTaulukonKoko() {
        return henkilot.getKokoLkm();
    }
    
    
    /**
     * @return henkilöt-taulukko
     */
    public Henkilo[] getHenkilotTaulukko() {
        return henkilot.getTaulukko();
    }
    
    
    /**
     * @return henkilöt-taulukossa sijaitsevien alkioiden lukumäärä
     */
    public int getHenkilotTaulukonAlkioidenMaara() {
        return henkilot.getLkm();
    }
    

    /**
     * Yhdistää henkilön ja instrumentin
     * @param henkilo yhistettävä henkilö
     * @param instrumentti yhistettävä instrumentti
     */
    public void lisaaHloInstrumentti(Henkilo henkilo, Instrumentti instrumentti) {
        HenkiloJaInstrumentti yhistys = new HenkiloJaInstrumentti(
                henkilo.getId(), instrumentti.getTunnusNro());
        henkilotJaInstrumentit.lisaa(yhistys);
    }


    /**
     * Tulostaa henkilöt-taulukon
     */
    public void tulosta() {
        henkilot.tulosta();
    }


    /**
     * Etsii tietyn henkilön kaikki soittimet
     * @param henkilonId tietyn henkilön id jolta instrumentit halutaan
     * @return tietyn henkilön kaikki soittimet listana.
     */
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
        return henkilotJaInstrumentit.soittimet(henkilonId);
    }


    /**
     * Etsii tietyn soittimen (merkkijonon) sen tunnusnumerolla
     * @param tunnusnro soittimen viitenumero
     * @return soittimen merkkijonona
     */
    public String soitin(int tunnusnro) {
        return instrumentit.soitin(tunnusnro);
    }
    
    
    /**
     * Lukee tiedoston ja lisää tiedoston mukaiset alkiot henkilöt-taulukkoon
     */
    public void lueTiedostosta() {
        henkilot.lueTiedostosta();
        instrumentit.lueTiedostosta();
        henkilotJaInstrumentit.lueTiedostosta();
    }
    
    
    /**
     * Kirjoittaa muutokset kaikkiin tiedostoihin 
     */
    public void kirjoitaMuutokset() {
        henkilot.kirjoitaTiedostoon();
        instrumentit.kirjoitaTiedostoon();
        henkilotJaInstrumentit.kirjoitaTiedostoon();
    }
    
    
    /**
     * Etsii merkkijonoa vastaavan instrumentin, jos löytyy
     * @param etsittava        etsittava instrumentti
     * @return                 etsittävän instrumentin, muutoin null
     */
    public Instrumentti loytyykoInstrumentti(String etsittava) {
        return instrumentit.loytyykoInstrumenttia(etsittava);
    }
    
    /**
     * Poistaa tietyn henkilön kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonInstrumentit(int henkilonId) {
        henkilotJaInstrumentit.poistaHenkilonInstrumentit(henkilonId);
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        BandBuddy bandbuddy = new BandBuddy();
        Henkilo testiHenkilo1 = new Henkilo();
        Henkilo testiHenkilo2 = new Henkilo();
        testiHenkilo1.rekisteroi();
        testiHenkilo1.taytaValiaikaisetTiedot();
        testiHenkilo2.rekisteroi();
        testiHenkilo2.taytaValiaikaisetTiedot();
        bandbuddy.lisaa(testiHenkilo1);
        bandbuddy.lisaa(testiHenkilo2);

        int id1 = testiHenkilo1.getId();

        HenkiloJaInstrumentti soittaja = new HenkiloJaInstrumentti(id1);
        soittaja.vastaaKitaranSoittajaa(id1);
        Instrumentti soitin = new Instrumentti("kitara");
        Instrumentti soitin2 = new Instrumentti("basso");
        Instrumentti soitin3 = new Instrumentti("rummut");

        soitin.rekisteroi();
        soitin2.rekisteroi();
        soitin3.rekisteroi();
        bandbuddy.lisaa(soitin);
        bandbuddy.lisaa(soitin2);
        bandbuddy.lisaa(soitin3);

        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin);
        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin2);
        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin3);

        List<HenkiloJaInstrumentti> hlosoittimet = bandbuddy
                .soittimet(testiHenkilo1.getId());
        List<String> hlosoittimet1 = new ArrayList<String>();
        for (HenkiloJaInstrumentti iNumerot : hlosoittimet)
            hlosoittimet1.add(bandbuddy.soitin(iNumerot.getInstrumentinNro()));
        for (int i = 0; i < hlosoittimet.size(); i++)
            System.out.println(hlosoittimet1.get(i));
    }
}
