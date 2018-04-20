package bandbuddy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Henkilöt-luokka
 * Sisältää tietorakenteen josta löytyvät kaikki yksittäiset henkilöt
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class Henkilot {
    
    private Henkilo[]   henkilotTaulukko;
    private int         kokoLkm             = 0;
    private int         lkm                 = 0;
    private String      tiedostonNimi       = "henkilot.dat";


    /**
     * Parametriton konstruktori Henkilot-luokalle
     * luo 5-alkion kokoisen taulukon oletuksena
     */
    public Henkilot() {
        this.henkilotTaulukko = new Henkilo[5];
        this.kokoLkm = henkilotTaulukko.length;
    }


    /**
     * Palauttaa taulukon sisällä olevien henkilöiden määrän
     * @return      taulukon sisällä olevien henkilöiden määrä
     * @example
     * <pre name="test">
     * Henkilot testiTaulukko1 = new Henkilot();
     * testiTaulukko1.getLkm() === 0;
     * </pre>
     */
    public int getLkm() {
        return this.lkm;
    }


    /**
     * Palauttaa taulukon koon
     * @return      taulukon koko
     * @example
     * <pre name="test">
     * Henkilot testiTaulukko1 = new Henkilot();
     * testiTaulukko1.getKokoLkm() === 5;
     * </pre>
     */
    public int getKokoLkm() {
        return this.kokoLkm;
    }
    
    
    /**
     * @return henkilöt taulukko
     */
    public Henkilo[] getTaulukko() {
        return this.henkilotTaulukko;
    }


    /**
     * Lisää henkilön taulukkoon
     * @param lisattavaHenkilo      lisättävä henkilö
     * @example
     * <pre name="test">
     * Henkilot testiTaulukko1 = new Henkilot();
     * testiTaulukko1.getLkm() === 0;
     * Henkilo perusPate = new Henkilo();
     * perusPate.rekisteroi();
     * testiTaulukko1.lisaa(perusPate);
     * testiTaulukko1.getLkm() === 1;
     * </pre>
     */
    public void lisaa(Henkilo lisattavaHenkilo) {
        if (lkm == this.kokoLkm)
            this.kloonaa(); // luodaan isompi taulukko jos täynnä
        henkilotTaulukko[lkm++] = lisattavaHenkilo;
    }


    /**
     * Palauttaa tietyssä taulukon paikassa olevan henkilön
     * @param indeksi                       henkilön paikka
     * @return                              palauttaa henkilö-olion
     * @throws IndexOutOfBoundsException    jos koitetaan saada henkilöä paikasta jota ei ole olemassa tai jossa ei ole henkilöä 
     * @example
     * <pre name="test">
     * Henkilot testiTaulukko1 = new Henkilot();
     * Henkilo perusPate = new Henkilo();
     * perusPate.rekisteroi();
     * perusPate.setNimi("Perus-Pate");
     * Henkilo akuAnkka = new Henkilo();
     * akuAnkka.rekisteroi();
     * akuAnkka.setNimi("Aku Ankka");
     * Henkilo sepi = new Henkilo();
     * sepi.rekisteroi();
     * sepi.setNimi("Sepi");
     * Henkilo eiLisattu = new Henkilo();
     * eiLisattu.rekisteroi();
     * testiTaulukko1.lisaa(perusPate);
     * testiTaulukko1.lisaa(akuAnkka);
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.getHenkilo(2).getNimi() === "Sepi";
     * testiTaulukko1.getHenkilo(1).getNimi() === "Aku Ankka";
     * testiTaulukko1.getHenkilo(0).getNimi() === "Perus-Pate";
     * testiTaulukko1.getHenkilo(4) === eiLisattu; #THROWS IndexOutOfBoundsException
     * testiTaulukko1.getHenkilo(5) === eiLisattu; #THROWS IndexOutOfBoundsException
     * testiTaulukko1.getHenkilo(10) === eiLisattu; #THROWS IndexOutOfBoundsException
     */
    public Henkilo getHenkilo(int indeksi) throws IndexOutOfBoundsException {
        if (indeksi < 0 || indeksi > this.getLkm()) {
            throw new IndexOutOfBoundsException("Virheellinen indeksi.");
        }
        return this.henkilotTaulukko[indeksi];
    }


    /**
     * Tekee isomman taulukon ja kopioi edellisen taulukon alkiot siihen
     * Henkilot testiTaulukko1 = new Henkilot();
     * testiTaulukko1.getLkm()              === 0;
     * testiTaulukko.1getKokoLkm()          === 5;
     * Henkilo sepi = new Henkilo();
     * sepi.rekisteroi();
     * sepi.setNimi("Sepi");
     * sepi.taytaValiaikaisetTiedot();
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.getLkm()              === 5;
     * testiTaulukko.1getKokoLkm()          === 5;
     * testiTaulukko1.lisaa(sepi);
     * testiTaulukko1.getLkm()              === 6;
     * testiTaulukko.1getKokoLkm()          === 10;
     * testiTaulukko1.lisaa(sepi)
     * testiTaulukko1.lisaa(sepi)
     * testiTaulukko1.lisaa(sepi)
     * testiTaulukko1.lisaa(sepi)
     * testiTaulukko1.getLkm()              === 10;
     * testiTaulukko.1getKokoLkm()          === 10;
     * testiTaulukko1.lisaa(sepi)
     * testiTaulukko1.getLkm()              === 11;
     * testiTaulukko.1getKokoLkm()          === 20;
     */
    public void kloonaa() {
        Henkilo[] klooni = new Henkilo[this.lkm * 2];
        for (int i = 0; i < this.henkilotTaulukko.length; i++) {
            klooni[i] = this.getHenkilo(i);
        }
        this.henkilotTaulukko = klooni;
        this.kokoLkm = klooni.length;
    }


    /**
     * Tulostaa henkilöt-taulukon kokonaisuudessaan
     */
    public void tulosta() {
        for (int i = 0; i < this.lkm; i++) {
            henkilotTaulukko[i].tulosta(System.out);
        }
    }
    
    
    /**
     * Lukee tiedoston rivit ja luo sen mukaa henkilöitä taulukkoon
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.IOException;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * VertaaTiedosto.kirjoitaTiedosto("testi.dat", ";id|nimi|ika|sukupuoli|paikkakunta|vapaana|kokemus|yhteystiedot\n1|Erkki Esim|13|Mies|Hesa|viikonloppuisin|5v|040xxx\n2|Iso boi|26|Mies|Hesa|viikonloppuisin|10v|040xxx|");
     * Henkilot testiHenkilotLuokka = new Henkilot();
     * testiHenkilotLuokka.setLuettavaTiedosto("testi.dat");
     * testiHenkilotLuokka.lueTiedostosta();
     * VertaaTiedosto.tuhoaTiedosto("testi.dat");
     * testiHenkilotLuokka.getLkm() === 2;
     * testiHenkilotLuokka.getHenkilo(0).toString() === "1|Erkki Esim|13|Mies|Hesa|viikonloppuisin|5v|040xxx|";
     * testiHenkilotLuokka.getHenkilo(1).toString() === "2|Iso boi|26|Mies|Hesa|viikonloppuisin|10v|040xxx|";
     * </pre>
     */
    public void lueTiedostosta() {
        String luettavanTiedostonNimi = this.tiedostonNimi;
        String tiedostonRivi = "";
        
        try ( Scanner fi = new Scanner(new FileInputStream(new File(luettavanTiedostonNimi)), "ISO-8859-1") ) {
            
            while (fi.hasNextLine()) {
                tiedostonRivi = fi.nextLine();
                if (!tiedostonRivi.startsWith(";")) { // jos rivi alkaa ; niin ei tehdä mitään (aloitusrivi)
                Henkilo uusiHenkilo = new Henkilo();
                uusiHenkilo.parse(tiedostonRivi);
               
                lisaa(uusiHenkilo);
                
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedoston lukeminen ei onnistunut! " + fnfe.getMessage());
            try {
                new File("henkilot.dat").createNewFile();
            } catch (IOException ioe) {
                System.err.println("Tiedoston luominen ei onnistunut " + ioe.getMessage());
            }
        }
    }
    
    
	/**
	 * Laittaa luettavaksi tiedostoksi annetun merkkijonon
	 * @param tiedosto     tiedosto jota halutaan lukea
	 */
	public void setLuettavaTiedosto(String tiedosto) {
	    this.tiedostonNimi = tiedosto;
	}
    
    
    /**
     * Lukee taulukon alkiot ja luo sen mukaa rivejä tiedostoon
     */
    public void kirjoitaTiedostoon() {
        String kohdetiedostonNimi = this.tiedostonNimi;
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(kohdetiedostonNimi))) {
            fo.println(";id|nimi|ikä|sukupuoli|paikkakunta|vapaana|kokemus|yhteystiedot|");
            for (int i = 0; i < this.lkm; i++) {
                fo.println(this.getHenkilo(i).toString());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedostoon kirjoittaminen ei onnistunut! " + fnfe.getMessage());
        }
    }
    
    
    /**
     * Poistaa henkilön tietorakenteesta
     * @param henkilonId poistettavan henkilön id
     */
    public void poista(int henkilonId) {
        // Henkilo poistettavaHenkilo = this.getHenkilo(henkilonId);
        int id = henkilonId - 1;
        this.lkm--;
        for (int i = id; i < this.lkm; i++) {
            this.henkilotTaulukko[i] = this.henkilotTaulukko[i + 1];
        }
        this.henkilotTaulukko[lkm] = null;
    }
}