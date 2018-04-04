/**
 * 
 */
package bandbuddy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Henkilöt-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class Henkilot {
    private Henkilo[] henkilotTaulukko;
    private int kokoLkm = 0;
    private int lkm = 0;
    private String tiedostonNimi = "henkilot.dat";


    /**
     * Parametriton konstruktori Henkilot-luokalle
     * luo 5-alkion kokoisen taulukon
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
     */
    public void lueTiedostosta() {
        String luettavanTiedostonNimi = this.tiedostonNimi;
        String tiedostonRivi = "";
        // StringBuilder tiedostonRiviSB;
        // StringBuilder idSB;
        // StringBuilder ikaSB;
        
        try ( Scanner fi = new Scanner(new FileInputStream(new File(luettavanTiedostonNimi))) ) {
            while (fi.hasNextLine()) {
                tiedostonRivi = fi.nextLine();
                if (!tiedostonRivi.startsWith(";")) { // jos rivi alkaa ; niin ei tehdä mitään (aloitusrivi)
                if (tiedostonRivi.contains("Ã¤") ) tiedostonRivi = tiedostonRivi.replaceAll("Ã¤", "ä");
                Henkilo uusiHenkilo = new Henkilo();
                uusiHenkilo.parse(tiedostonRivi);
                
                /*
                tiedostonRiviSB = new StringBuilder(tiedostonRivi);
                idSB = new StringBuilder(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.rekisteroi(Mjonot.erotaInt(idSB, -1));
                
                uusiHenkilo.setNimi(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                
                ikaSB = new StringBuilder(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.setIka(Mjonot.erotaInt(ikaSB, -1));
                
                uusiHenkilo.setSukupuoli(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.setPaikkakunta(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.setVapaana(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.setKokemus(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                uusiHenkilo.setYhteystiedot(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
                */
                lisaa(uusiHenkilo);
                
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Tiedoston lukeminen ei onnistunut! " + fnfe.getMessage());
        }
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        // Näitä ei tarvita tulevaisuudessa, poistetaan joskus
        Henkilot henkilot = new Henkilot();
        Henkilo testiHenkilo1 = new Henkilo();
        testiHenkilo1.rekisteroi();
        testiHenkilo1.taytaValiaikaisetTiedot();
        Henkilo testiHenkilo2 = new Henkilo();
        testiHenkilo2.rekisteroi();
        testiHenkilo2.taytaValiaikaisetTiedot();

        henkilot.lisaa(testiHenkilo1);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.lisaa(testiHenkilo2);
        henkilot.tulosta();
        //henkilot.getHenkilo(50);
        henkilot.lueTiedostosta();
        henkilot.tulosta();
    }
}
