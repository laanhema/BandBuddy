package bandbuddy;

import java.util.*;

/**
 * BandBuddy-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class BandBuddy {
    
    private final Henkilot                  henkilot                    = new Henkilot();
    private final HenkilotJaInstrumentit    henkilotJaInstrumentit      = new HenkilotJaInstrumentit();
    private final Instrumentit              instrumentit                = new Instrumentit();
    private final HenkilotJaGenret          henkilotJaGenret            = new HenkilotJaGenret();
    private final Genret                    genret                      = new Genret();


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
     * Lisää genren tietorakenteeseen
     * @param genre lisättävä musiikkilaji
     */
    public void lisaa(Genre genre) {
        genret.lisaa(genre);
    }
    
    
    /**
     * Lisää henkilön ja instrumentin idn tietorakenteeseen
     * @param alkio lisättävä henkilö ja sen instrumentti
     */
    public void lisaa(HenkiloJaInstrumentti alkio) {
        henkilotJaInstrumentit.lisaa(alkio);
    }
    
    
    /**
     * Lisää henkilön ja instrumentin idn tietorakenteeseen
     * @param alkio lisättävä henkilö ja sen genre
     */
    public void lisaa(HenkiloJaGenre alkio) {
        henkilotJaGenret.lisaa(alkio);
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
     * @param henkilo yhdistettävä henkilö
     * @param instrumentti yhdistettävä instrumentti
     */
    public void lisaaHloInstrumentti(Henkilo henkilo, Instrumentti instrumentti) {
        HenkiloJaInstrumentti yhistys = new HenkiloJaInstrumentti(
                henkilo.getId(), instrumentti.getTunnusNro());
        henkilotJaInstrumentit.lisaa(yhistys);
    }

    
    /**
     * Yhdistää henkilön ja genren
     * @param henkilo yhdistettävä henkilö
     * @param genre yhdistettävä genre
     */
    public void lisaaHloGenre(Henkilo henkilo, Genre genre) {
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
     * Etsii tietyn henkilön kaikki genret
     * @param henkilonId tietyn henkilön id jolta genret halutaan
     * @return tietyn henkilön kaikki genret listana.
     */
    public List<HenkiloJaGenre> genret(int henkilonId) {
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
     * Etsii tietyn genren (merkkijonon) sen tunnusnumerolla
     * @param tunnusnro genre viitenumero
     * @return genre merkkijonona
     */
    public String genre(int tunnusnro) {
        return genret.hGenre(tunnusnro);
    }
    
    
    /**
     * Lukee tiedoston ja lisää tiedoston mukaiset alkiot henkilöt-taulukkoon
     */
    public void lueTiedostosta() {
        henkilot.lueTiedostosta();
        instrumentit.lueTiedostosta();
        genret.lueTiedostosta();
        henkilotJaInstrumentit.lueTiedostosta();
        henkilotJaGenret.lueTiedostosta();
    }
    
    
    /**
     * Kirjoittaa muutokset kaikkiin tiedostoihin 
     */
    public void kirjoitaMuutokset() {
        henkilot.kirjoitaTiedostoon();
        instrumentit.kirjoitaTiedostoon();
        genret.kirjoitaTiedostoon();
        henkilotJaInstrumentit.kirjoitaTiedostoon();
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
     * Etsii merkkijonoa vastaavan genren, jos löytyy
     * @param etsittava        etsittava genre
     * @return                 etsittävän genre, muutoin null
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
     * Poistaa tietyn henkilön kaikki genret tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonGenret(int henkilonId) {
        henkilotJaGenret.poistaHenkilonGenret(henkilonId);
    }
    
    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String nimiKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-Z|Ä|Ö]{1}[a-z|ä|ö]* [A-Z|Ä|Ö]{1}[a-z|ä|ö]*") && merkkijono.length() > 0 ) return "Kirjoita nimesi muodossa Etunimi Sukunimi!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String ikaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[0-9]{1,3}") && merkkijono.length() > 0 ) return "Kirjoita ikäsi käyttämällä vain numeroita!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String sukupuoliKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-z|Ä|Ö|ä|ö]*") && merkkijono.length() > 0 ) return "Tähän kenttään sallitaan vain aakkosia!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String paikkakuntaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-z|Ä|Ö|ä|ö]*") && merkkijono.length() > 0 ) return "Tähän kenttään sallitaan vain aakkosia!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String instrumentitKenttaTarkistus(String merkkijono) {
        if ( merkkijono.startsWith("-") || merkkijono.startsWith(" ") || merkkijono.endsWith("-") || merkkijono.endsWith(" ") )  return "Kirjoita tähän soittamasi instrumentit. Jos instrumentteja on monta erota ne pilkulla ja välilyönnillä toisistaan.";
        if ( !merkkijono.matches("[A-z|Ä|Ö|ä|ö| |-]*[,]+[ ]+[A-z|Ä|Ö|ä|ö |-].*|[A-z|Ä|Ö|ä|ö| |-]*") && merkkijono.length() > 0 ) return "Kirjoita tähän soittamasi instrumentit. Jos instrumentteja on monta erota ne pilkulla ja välilyönnillä toisistaan.";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String genretKenttaTarkistus(String merkkijono) {
        if ( merkkijono.startsWith("-") || merkkijono.startsWith(" ") || merkkijono.startsWith("'") || merkkijono.endsWith("-") || merkkijono.endsWith(" ") || merkkijono.endsWith("'")  )  return "Kirjoita tähän sinua kiinnostavat musiikkilajit. Jos lajeja on monta erota ne pilkulla ja välilyönnillä toisistaan.";
        if ( !merkkijono.matches("[A-z|Ä|Ö|ä|ö| |-|']*[,]+[ ]+[A-z|Ä|Ö|ä|ö |-|'].*|[A-z|Ä|Ö|ä|ö| |-|']*") && merkkijono.length() > 0 ) return "Kirjoita tähän sinua kiinnostavat musiikkilajit. Jos lajeja on monta erota ne pilkulla ja välilyönnillä toisistaan.";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String vapaanaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti täytetty kenttä";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String kokemusKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti täytetty kenttä";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikenttään kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikentän teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta näkyy viat
     * 
     */
    public String yhteystiedotKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti täytetty kenttä";
        return null;
    }
}