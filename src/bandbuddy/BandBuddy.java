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
    private final HenkilotJaGenret henkilotJaGenret = new HenkilotJaGenret();
    private final Genret genret = new Genret();


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
     * Lisää instrumentin tietorakenteeseen
     * @param soitin lisättävä instrumentti
     */
    public void lisaa(Genre g) {
        genret.lisaa(g);
    }
    
    
    /**
     * Lisää henkilön ja instrumentin idn tietorakenteeseen
     * @param soittaja lisättävä henkilö ja sen instrumentti
     */
    public void lisaa(HenkiloJaGenre g) {
        henkilotJaGenret.lisaa(g);
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
     * Yhdistää henkilön ja instrumentin
     * @param henkilo yhistettävä henkilö
     * @param instrumentti yhistettävä instrumentti
     */
    public void lisaaHlogenre(Henkilo henkilo, Genre genre) {
        HenkiloJaGenre yhistys = new HenkiloJaGenre(
                henkilo.getId(), genre.getTunnusNro());
        henkilotJaGenret.lisaa(yhistys);
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
    public String hGenre(int tunnusnro) {
        return genret.hGenre(tunnusnro);
    }
    
    /**
     * Etsii tietyn henkilön kaikki soittimet
     * @param henkilonId tietyn henkilön id jolta instrumentit halutaan
     * @return tietyn henkilön kaikki soittimet listana.
     */
    public List<HenkiloJaGenre> hGenret(int henkilonId) {
        return henkilotJaGenret.hGenret(henkilonId);
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
        genret.lueTiedostosta();
        henkilotJaGenret.lueTiedostosta();
    }
    
    
    /**
     * Kirjoittaa muutokset kaikkiin tiedostoihin 
     */
    public void kirjoitaMuutokset() {
        henkilot.kirjoitaTiedostoon();
        instrumentit.kirjoitaTiedostoon();
        henkilotJaInstrumentit.kirjoitaTiedostoon();
        genret.kirjoitaTiedostoon();
        henkilotJaGenret.kirjoitaTiedostoon();
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
     * Etsii merkkijonoa vastaavan instrumentin, jos löytyy
     * @param etsittava        etsittava instrumentti
     * @return                 etsittävän instrumentin, muutoin null
     */
    public Genre loytyykoGenre(String etsittava) {
        return genret.loytyykoGenrea(etsittava);
    }
    
    
    /**
     * Poistaa tietyn henkilön tietorakenteesta
     * @param poistettavanHenkilonId    sen henkilön id joka halutaan poistaa 
     */
    public void poistaHenkilo(int poistettavanHenkilonId) {
        this.henkilot.poista(poistettavanHenkilonId);
    }
    
    
    
    /**
     * Poistaa tietyn henkilön kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonInstrumentit(int henkilonId) {
        henkilotJaInstrumentit.poistaHenkilonInstrumentit(henkilonId);
    }
    
    /**
     * Poistaa tietyn henkilön kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonGenret(int henkilonId) {
        henkilotJaGenret.poistaHenkilonGenret(henkilonId);
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
        
        Genre g1 = new Genre("proge");
        
        g1.rekisteroi();
        
        bandbuddy.lisaa(g1);

        soitin.rekisteroi();
        soitin2.rekisteroi();
        soitin3.rekisteroi();
        bandbuddy.lisaa(soitin);
        bandbuddy.lisaa(soitin2);
        bandbuddy.lisaa(soitin3);

        bandbuddy.lisaaHlogenre(testiHenkilo1, g1);
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
        
        List<HenkiloJaGenre> hlogenret = bandbuddy
                .hGenret(testiHenkilo1.getId());
        List<String> hlogenret1 = new ArrayList<String>();
        for (HenkiloJaGenre iNumerot : hlogenret)
            hlogenret1.add(bandbuddy.hGenre(iNumerot.getGenrenNro()));
        for (int i = 0; i < hlogenret.size(); i++)
            System.out.println(hlogenret1.get(i));
    }
}
