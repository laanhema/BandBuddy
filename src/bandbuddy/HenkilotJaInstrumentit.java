/**
 * 
 */
package bandbuddy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * HenkiloJaInstrumentit-luokka
 * Sisältää listan täynnä HenkiloJaInstrumentti-luokan alkioita
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 18.04.2018
 */
public class HenkilotJaInstrumentit implements Iterable<HenkiloJaInstrumentti> {
    private String tiedostonNimi = "henkilotjainstrumentit.dat";
    /* lista jäsenen ja instrumentin yhdistelmästä */
    private final Collection<HenkiloJaInstrumentti> alkiot = new ArrayList<HenkiloJaInstrumentti>();


    /**
     * testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        HenkilotJaInstrumentit soittajalista = new HenkilotJaInstrumentit();
        HenkiloJaInstrumentti jaba = new HenkiloJaInstrumentti(1);
        jaba.vastaaKitaranSoittajaa(1);
        HenkiloJaInstrumentti dude = new HenkiloJaInstrumentti(1);
        dude.vastaaKitaranSoittajaa(1);
        HenkiloJaInstrumentti basisti = new HenkiloJaInstrumentti(3);

        soittajalista.lisaa(jaba);
        soittajalista.lisaa(dude);
        soittajalista.lisaa(basisti);

        List<HenkiloJaInstrumentti> soittajalista2 = soittajalista.soittimet(1);
        for (int i = 0; i < soittajalista2.size(); i++)
            System.out.println(soittajalista2.get(i).getHenkilonNro());
    }


    /**
     * Hakee kaikki tietyn soittajan instrumentit
     * @param henkilonId soittimen numero
     * @return tietorakenne jossa on tietyn soittajan instrumentit.
     * #import java.util.*;
     * @example
     * <pre name="test">
     *  HenkilotJaInstrumentit hi = new HenkilotJaInstrumentit();
     *  HenkiloJaInstrumentti a = new HenkiloJaInstrumentti(1); hi.lisaa(a);
     *  HenkiloJaInstrumentti b = new HenkiloJaInstrumentti(1); hi.lisaa(b);
     *  HenkiloJaInstrumentti c = new HenkiloJaInstrumentti(2); hi.lisaa(c);
     *  List<HenkiloJaInstrumentti> kaikki;
     *  kaikki = hi.soittimet(1);
     *  kaikki.size() === 2; 
     * </pre> 
     */
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
        List<HenkiloJaInstrumentti> kaikki = new ArrayList<HenkiloJaInstrumentti>();
        for (HenkiloJaInstrumentti soittaja : alkiot)
            if (henkilonId == soittaja.getHenkilonNro())
                kaikki.add(soittaja);
        return kaikki;
    }


    /**
     * lisää instrumentin ja käyttäjän suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param alkio lisättävä alkio
     */
    public void lisaa(HenkiloJaInstrumentti alkio) {
        alkiot.add(alkio);
    }


    /**
     * @return listan koko
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * Listan alustaminen
     */
    public HenkilotJaInstrumentit() {
    }


    /**
     * @return listan iteraattorin
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  HenkilotJaInstrumentit hJI = new HenkilotJaInstrumentit();
     *  HenkiloJaInstrumentti a = new HenkiloJaInstrumentti(); hJI.lisaa(a);
     *  HenkiloJaInstrumentti b = new HenkiloJaInstrumentti(); hJI.lisaa(b);
     *  HenkiloJaInstrumentti c = new HenkiloJaInstrumentti(); hJI.lisaa(c);
     * 
     *  Iterator<HenkiloJaInstrumentti> i2=hJI.iterator();
     *  i2.next() === a;
     *  i2.next() === b;
     *  i2.next() === c;
     *  i2.next() === b;  #THROWS NoSuchElementException  
     *  
     * </pre>
     */
    @Override
    public Iterator<HenkiloJaInstrumentti> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * 
     * Lukee tiedoston rivit ja luo sen mukaa alkioita tietorakenteeseen
     */
    public void lueTiedostosta() {
        String luettavanTiedostonNimi = this.tiedostonNimi;
        String tiedostonRivi = "";
        
        try ( Scanner fi = new Scanner(new FileInputStream(new File(luettavanTiedostonNimi))) ) {
            while (fi.hasNextLine()) {
                tiedostonRivi = fi.nextLine();
                if (!tiedostonRivi.startsWith(";")) { // jos rivi alkaa ; niin ei tehdä mitään (aloitusrivi)
                HenkiloJaInstrumentti uusiAlkio = new HenkiloJaInstrumentti();
                uusiAlkio.parse(tiedostonRivi);
                lisaa(uusiAlkio);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedoston lukeminen ei onnistunut! " + fnfe.getMessage());
        }
    }


    /**
     * Lukee tietorakenteen alkiot ja luo sen mukaa rivejä tiedostoon
     */
    public void kirjoitaTiedostoon() {
        String kohdetiedostonNimi = this.tiedostonNimi;
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(kohdetiedostonNimi))) {
            fo.println(";hiid| id| nid|");
            for (HenkiloJaInstrumentti in : this.alkiot) {
                fo.println(in.toString());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedostoon kirjoittaminen ei onnistunut! " + fnfe.getMessage());
        }
    }
    
    
    /**
     * Poistaa tietyn henkilön kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonInstrumentit(int henkilonId) {
        Iterator<HenkiloJaInstrumentti> iter = this.iterator();
        HenkiloJaInstrumentti alkio = new HenkiloJaInstrumentti();
        while (iter.hasNext()) {
            alkio = iter.next();
            if (alkio.getHenkilonNro() == henkilonId) {
                iter.remove();
            }
        }
    }
}