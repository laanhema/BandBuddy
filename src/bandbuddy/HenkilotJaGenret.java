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
 * HenkiloJaInstrumentit-luokka : Sisältää listan täynnä HenkiloJaInstrumentti-luokan alkioita
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class HenkilotJaGenret implements Iterable<HenkiloJaGenre> {
    private String tiedostonNimi = "henkilotjagenret.dat";
    /* lista jäsenen ja instrumentin yhdistelmästä */
    private final Collection<HenkiloJaGenre> alkiot = new ArrayList<HenkiloJaGenre>();


    /**
     * testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        HenkilotJaGenret soittajalista = new HenkilotJaGenret();
        HenkiloJaGenre jaba = new HenkiloJaGenre(1);
        jaba.vastaaKitaranSoittajaa(1);
        HenkiloJaGenre dude = new HenkiloJaGenre(1);
        dude.vastaaKitaranSoittajaa(1);
        HenkiloJaGenre basisti = new HenkiloJaGenre(3);

        soittajalista.lisaa(jaba);
        soittajalista.lisaa(dude);
        soittajalista.lisaa(basisti);

        List<HenkiloJaGenre> soittajalista2 = soittajalista.hGenret(1);
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
    public List<HenkiloJaGenre> hGenret(int henkilonId) {
        List<HenkiloJaGenre> kaikki = new ArrayList<HenkiloJaGenre>();
        for (HenkiloJaGenre g : alkiot)
            if (henkilonId == g.getHenkilonNro())
                kaikki.add(g);
        return kaikki;
    }


    /**
     * lisää instrumentin ja käyttäjän suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param soitin instrumen
     */
    public void lisaa(HenkiloJaGenre soitin) {
        alkiot.add(soitin);
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
    public HenkilotJaGenret() {
    }


    /**
     * @return listan iteraattorin
     */
    @Override
    public Iterator<HenkiloJaGenre> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Lukee tiedoston rivit ja luo sen mukaa henkilöitä taulukkoon
     */
    public void lueTiedostosta() {
        String luettavanTiedostonNimi = this.tiedostonNimi;
        String tiedostonRivi = "";
        
        try ( Scanner fi = new Scanner(new FileInputStream(new File(luettavanTiedostonNimi))) ) {
            while (fi.hasNextLine()) {
                tiedostonRivi = fi.nextLine();
                if (!tiedostonRivi.startsWith(";")) { // jos rivi alkaa ; niin ei tehdä mitään (aloitusrivi)
                HenkiloJaGenre uusiAlkio = new HenkiloJaGenre();
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
            fo.println(";hiid| id| gid|");
            for (HenkiloJaGenre ge : this.alkiot) {
                fo.println(ge.toString());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedostoon kirjoittaminen ei onnistunut! " + fnfe.getMessage());
        }
    }
    
    
    /**
     * Poistaa tietyn henkilön kaikki instrumentit tietorakenteesta
     * @param henkilonId        henkilön id
     */
    public void poistaHenkilonGenret(int henkilonId) {
        Iterator<HenkiloJaGenre> iter = this.iterator();
        HenkiloJaGenre alkio = new HenkiloJaGenre();
        while (iter.hasNext()) {
            alkio = iter.next();
            if (alkio.getHenkilonNro() == henkilonId) {
                iter.remove();
            }
        }
    }
}