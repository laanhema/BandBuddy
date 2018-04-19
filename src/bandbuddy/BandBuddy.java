package bandbuddy;

import java.util.*;

/**
 * BandBuddy-luokka
 * @author Markus M�ntymaa & Lauri Makkonen
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
     * Lis�� henkil�n henkil�t-taulukoon
     * @param henkilo   lis�tt�v� henkil�
     */
    public void lisaa(Henkilo henkilo) {
        henkilot.lisaa(henkilo);
    }
    
    
    /**
     * Lis�� instrumentin tietorakenteeseen
     * @param soitin lis�tt�v� instrumentti
     */
    public void lisaa(Instrumentti soitin) {
        instrumentit.lisaa(soitin);
    }
    
    
    /**
     * Lis�� genren tietorakenteeseen
     * @param genre lis�tt�v� musiikkilaji
     */
    public void lisaa(Genre genre) {
        genret.lisaa(genre);
    }
    
    
    /**
     * Lis�� henkil�n ja instrumentin idn tietorakenteeseen
     * @param alkio lis�tt�v� henkil� ja sen instrumentti
     */
    public void lisaa(HenkiloJaInstrumentti alkio) {
        henkilotJaInstrumentit.lisaa(alkio);
    }
    
    
    /**
     * Lis�� henkil�n ja instrumentin idn tietorakenteeseen
     * @param alkio lis�tt�v� henkil� ja sen genre
     */
    public void lisaa(HenkiloJaGenre alkio) {
        henkilotJaGenret.lisaa(alkio);
    }

    
    /**
     * @param indeksi   henkil�n paikka henkil�t-taulukossa
     * @return henkil�
     */
    public Henkilo getHenkilo(int indeksi) {
        return henkilot.getHenkilo(indeksi);
    }
    
    
    /**
     * @return henkil�t-taulukon koko
     */
    public int getHenkilotTaulukonKoko() {
        return henkilot.getKokoLkm();
    }
    
    
    /**
     * @return henkil�t-taulukko
     */
    public Henkilo[] getHenkilotTaulukko() {
        return henkilot.getTaulukko();
    }
    
    
    /**
     * @return henkil�t-taulukossa sijaitsevien alkioiden lukum��r�
     */
    public int getHenkilotTaulukonAlkioidenMaara() {
        return henkilot.getLkm();
    }
    

    /**
     * Yhdist�� henkil�n ja instrumentin
     * @param henkilo yhdistett�v� henkil�
     * @param instrumentti yhdistett�v� instrumentti
     */
    public void lisaaHloInstrumentti(Henkilo henkilo, Instrumentti instrumentti) {
        HenkiloJaInstrumentti yhistys = new HenkiloJaInstrumentti(
                henkilo.getId(), instrumentti.getTunnusNro());
        henkilotJaInstrumentit.lisaa(yhistys);
    }

    
    /**
     * Yhdist�� henkil�n ja genren
     * @param henkilo yhdistett�v� henkil�
     * @param genre yhdistett�v� genre
     */
    public void lisaaHloGenre(Henkilo henkilo, Genre genre) {
        HenkiloJaGenre yhistys = new HenkiloJaGenre(
                henkilo.getId(), genre.getTunnusNro());
        henkilotJaGenret.lisaa(yhistys);
    }
    

    /**
     * Tulostaa henkil�t-taulukon
     */
    public void tulosta() {
        henkilot.tulosta();
    }


    /**
     * Etsii tietyn henkil�n kaikki soittimet
     * @param henkilonId tietyn henkil�n id jolta instrumentit halutaan
     * @return tietyn henkil�n kaikki soittimet listana.
     */
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
        return henkilotJaInstrumentit.soittimet(henkilonId);
    }
    
    
    /**
     * Etsii tietyn henkil�n kaikki genret
     * @param henkilonId tietyn henkil�n id jolta genret halutaan
     * @return tietyn henkil�n kaikki genret listana.
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
     * Lukee tiedoston ja lis�� tiedoston mukaiset alkiot henkil�t-taulukkoon
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
     * Etsii merkkijonoa vastaavan instrumentin, jos l�ytyy
     * @param etsittava        etsittava instrumentti
     * @return                 etsitt�v�n instrumentin, muutoin null
     */
    public Instrumentti loytyykoInstrumentti(String etsittava) {
        return instrumentit.loytyykoInstrumenttia(etsittava);
    }
    
    
    /**
     * Etsii merkkijonoa vastaavan genren, jos l�ytyy
     * @param etsittava        etsittava genre
     * @return                 etsitt�v�n genre, muutoin null
     */
    public Genre loytyykoGenre(String etsittava) {
        return genret.loytyykoGenrea(etsittava);
    }
    
    
    /**
     * Poistaa tietyn henkil�n tietorakenteesta
     * @param poistettavanHenkilonId    sen henkil�n id joka halutaan poistaa 
     */
    public void poistaHenkilo(int poistettavanHenkilonId) {
        this.henkilot.poista(poistettavanHenkilonId);
    }
    
    
    
    /**
     * Poistaa tietyn henkil�n kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkil�n id
     */
    public void poistaHenkilonInstrumentit(int henkilonId) {
        henkilotJaInstrumentit.poistaHenkilonInstrumentit(henkilonId);
    }
    
    
    /**
     * Poistaa tietyn henkil�n kaikki genret tietorakenteesta
     * @param henkilonId        henkil�n id
     */
    public void poistaHenkilonGenret(int henkilonId) {
        henkilotJaGenret.poistaHenkilonGenret(henkilonId);
    }
    
    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String nimiKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-Z|�|�]{1}[a-z|�|�]* [A-Z|�|�]{1}[a-z|�|�]*") && merkkijono.length() > 0 ) return "Kirjoita nimesi muodossa Etunimi Sukunimi!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String ikaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[0-9]{1,3}") && merkkijono.length() > 0 ) return "Kirjoita ik�si k�ytt�m�ll� vain numeroita!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String sukupuoliKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-z|�|�|�|�]*") && merkkijono.length() > 0 ) return "T�h�n kentt��n sallitaan vain aakkosia!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String paikkakuntaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches("[A-z|�|�|�|�]*") && merkkijono.length() > 0 ) return "T�h�n kentt��n sallitaan vain aakkosia!";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String instrumentitKenttaTarkistus(String merkkijono) {
        if ( merkkijono.startsWith("-") || merkkijono.startsWith(" ") || merkkijono.endsWith("-") || merkkijono.endsWith(" ") )  return "Kirjoita t�h�n soittamasi instrumentit. Jos instrumentteja on monta erota ne pilkulla ja v�lily�nnill� toisistaan.";
        if ( !merkkijono.matches("[A-z|�|�|�|�| |-]*[,]+[ ]+[A-z|�|�|�|� |-].*|[A-z|�|�|�|�| |-]*") && merkkijono.length() > 0 ) return "Kirjoita t�h�n soittamasi instrumentit. Jos instrumentteja on monta erota ne pilkulla ja v�lily�nnill� toisistaan.";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String genretKenttaTarkistus(String merkkijono) {
        if ( merkkijono.startsWith("-") || merkkijono.startsWith(" ") || merkkijono.startsWith("'") || merkkijono.endsWith("-") || merkkijono.endsWith(" ") || merkkijono.endsWith("'")  )  return "Kirjoita t�h�n sinua kiinnostavat musiikkilajit. Jos lajeja on monta erota ne pilkulla ja v�lily�nnill� toisistaan.";
        if ( !merkkijono.matches("[A-z|�|�|�|�| |-|']*[,]+[ ]+[A-z|�|�|�|� |-|'].*|[A-z|�|�|�|�| |-|']*") && merkkijono.length() > 0 ) return "Kirjoita t�h�n sinua kiinnostavat musiikkilajit. Jos lajeja on monta erota ne pilkulla ja v�lily�nnill� toisistaan.";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String vapaanaKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti t�ytetty kentt�";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String kokemusKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti t�ytetty kentt�";
        return null;
    }

    
    /**
     * Tarkistaa onko tekstikentt��n kirjoitettu teksti oikeaa muotoa
     * @param merkkijono        tekstikent�n teksti
     * @return                  null, jos ei ole vikaa
     *                          muutoin palauttaa merkkijonon josta n�kyy viat
     * 
     */
    public String yhteystiedotKenttaTarkistus(String merkkijono) {
        if ( !merkkijono.matches(".*") && merkkijono.length() > 0 ) return "Virheellisesti t�ytetty kentt�";
        return null;
    }
}