/**
 * 
 */
package bandbuddy;

/**
 * Henkilöt-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
public class Henkilot {
    private Henkilo[]   henkilotTaulukko;
    private int         kokoLkm             = 0;
    private int         lkm                 = 0;
    
    
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
    public int getLkm( ) {
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
    public int getKokoLkm( ) {
        return this.kokoLkm;
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
        if (lkm == this.kokoLkm ) this.kloonaa();  // luodaan isompi taulukko jos täynnä
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
        if ( indeksi < 0 || indeksi > this.getLkm() ) {
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
    }
}
