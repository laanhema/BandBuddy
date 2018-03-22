/**
 * 
 */
package bandbuddy;

import java.util.*;
/**
 * BandBuddy-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class BandBuddy {
    
    private Henkilot henkilot = new Henkilot();
    private final Instrumentit instrumentit = new Instrumentit();
    private final Instrumentti instrumentti = new Instrumentti();
    
    
    /**
     * Parametriton konstruktori BandBuddylle
     */
    public BandBuddy() {
        //
    }
    
    public void lisaa(Instrumentti2 soitin) {
        instrumentti.lisaa(soitin);
    }
    
    

    public void lisaa(Instrumentit2 soittaja) {
    	instrumentit.lisaa(soittaja);
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
