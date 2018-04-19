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
 * Sis�lt�� listan t�ynn� HenkiloJaInstrumentti-luokan alkioita
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 18.04.2018
 */
public class HenkilotJaGenret implements Iterable<HenkiloJaGenre> {
    private String tiedostonNimi = "henkilotjagenret.dat";
    private final Collection<HenkiloJaGenre> alkiot = new ArrayList<HenkiloJaGenre>();


    /**
     * testip��ohjelma
     * @param args ei k�yt�ss�
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
     * Hakee kaikki tietyn soittajan genret
     * @param henkilonId henkil�n id
     * @return tietorakenne jossa on tietyn soittajan kaikki genret.
     * #import java.util.*;
     * @example
     * <pre name="test">
     *  HenkilotJaGenret hi = new HenkilotJaGenret();
     *  HenkiloJaGenre a = new HenkiloJaGenre(1); hi.lisaa(a);
     *  HenkiloJaGenre b = new HenkiloJaGenre(1); hi.lisaa(b);
     *  HenkiloJaGenre c = new HenkiloJaGenre(2); hi.lisaa(c);
     *  List<HenkiloJaGenre> kaikki;
     *  kaikki = hi.hGenret(1);
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
     * lis�� alkion tietorakenteeseen
     * @param alkio lis�tt�v� alkio
     */
    public void lisaa(HenkiloJaGenre alkio) {
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
    public HenkilotJaGenret() {
    }


    /**
     * @return listan iteraattorin
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  HenkilotJaGenret hJG = new HenkilotJaGenret();
     *  HenkiloJaGenre a = new HenkiloJaGenre(); hJG.lisaa(a);
     *  HenkiloJaGenre b = new HenkiloJaGenre(); hJG.lisaa(b);
     *  HenkiloJaGenre c = new HenkiloJaGenre(); hJG.lisaa(c);
     * 
     *  Iterator<HenkiloJaGenre> i2=hJG.iterator();
     *  i2.next() === a;
     *  i2.next() === b;
     *  i2.next() === c;
     *  i2.next() === b;  #THROWS NoSuchElementException  
     *  
     * </pre>
     */
    @Override
    public Iterator<HenkiloJaGenre> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Lukee tiedoston rivit ja luo sen mukaa alkioita tietorakenteeseen
     */
    public void lueTiedostosta() {
        String luettavanTiedostonNimi = this.tiedostonNimi;
        String tiedostonRivi = "";
        
        try ( Scanner fi = new Scanner(new FileInputStream(new File(luettavanTiedostonNimi))) ) {
            while (fi.hasNextLine()) {
                tiedostonRivi = fi.nextLine();
                if (!tiedostonRivi.startsWith(";")) { // jos rivi alkaa ; niin ei tehd� mit��n (aloitusrivi)
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
     * Lukee tietorakenteen alkiot ja luo sen mukaa rivej� tiedostoon
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
     * Poistaa tietyn henkil�n kaikki genret tietorakenteesta
     * @param henkilonId        henkil�n id
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