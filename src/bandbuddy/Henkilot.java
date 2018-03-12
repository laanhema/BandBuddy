/**
 * 
 */
package bandbuddy;

/**
 * Henkilöt-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 12.03.2018
 *
 */
public class Henkilot {
    private Henkilo[]   henkilotTaulukko;
    private int         kokoLkm             = 0;
    private int         lkm                 = 0;
    
    
    /**
     * Parametriton konstruktori Henkilot-luokalle
     */
    public Henkilot() {
        this.henkilotTaulukko = new Henkilo[5];
        this.kokoLkm = henkilotTaulukko.length;
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
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
    }


    /**
     * Lisää henkilön taulukkoon
     * @param lisattavaHenkilo      lisättävä henkilö
     */
    public void lisaa(Henkilo lisattavaHenkilo) {
        if (lkm == this.kokoLkm ) this.kloonaa();  // luodaan isompi taulukko jos täynnä
        henkilotTaulukko[lkm++] = lisattavaHenkilo;
    }
    
    
    /**
     * Palauttaa tietyssä taulukon paikassa olevan henkilön
     * @param indeksi       henkilön paikka
     * @return              henkilö
     */
    public Henkilo getHenkilo(int indeksi) {
        return this.henkilotTaulukko[indeksi];
    }
    
   
    /**
     * Tekee isomman taulukon ja kopioi edellisen taulukon alkiot siihen
     */
    public void kloonaa() {
        Henkilo[] klooni = new Henkilo[this.lkm*2];
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
   
    
}
