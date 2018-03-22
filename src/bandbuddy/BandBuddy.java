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
    private final Instrumentit instrumentit = new Instrumentit();
    private final Instrumentti instrumentti = new Instrumentti();
    
    
    /**
     * Parametriton konstruktori BandBuddylle
     */
    public BandBuddy() {
    }
    
    
    /**
     * Lisää henkilön taulukkoon
     * @param henkilo   Lisättävä henkilö
     */
    public void lisaa(Henkilo henkilo) {
        henkilot.lisaa(henkilo);
    }
    
    
    /**
     * Tulostaa koko taulukon
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
    
    
    /** 
     * Lisää instrumentin
     * @param soitin    soitin
     */
    public void lisaa(Instrumentti2 soitin) {
        instrumentti.lisaa(soitin);
    }
    
    
    /**
     * Liittää instrumentin henkilöön
     * @param soittaja  soittaja
     */
    public void lisaa(Instrumentit2 soittaja) {
        instrumentit.lisaa(soittaja);
    }
    

}
