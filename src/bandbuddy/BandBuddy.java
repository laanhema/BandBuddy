/**
 * 
 */
package bandbuddy;

/**
 * BandBuddy-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
public class BandBuddy {
    private Henkilot henkilot = new Henkilot();
    
    // TODO - BANDBUDDY - kaikki testit tänne
    
    /**
     * Parametriton konstruktori BandBuddylle
     */
    public BandBuddy() {
        //
    }
    
    
    /**
     * Metodi, joka...
     * @param henkilo x
     */
    public void lisaa(Henkilo henkilo) {
        henkilot.lisaa(henkilo);
    }
    
    
    /**
     * Metodi, joka...
     */
    public void tulosta() {
        henkilot.tulosta();
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
        bandbuddy.tulosta();
    }
}
