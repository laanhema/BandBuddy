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
    private final HenkilotJaInstrumentit henkilotJaInstrumentit = new HenkilotJaInstrumentit();
    private final Instrumentit instrumentit = new Instrumentit();
    
    
    /**
     * Parametriton konstruktori BandBuddylle
     */
    public BandBuddy() {
        //
    }
    
    public void lisaa(Instrumentti soitin) {
        instrumentit.lisaa(soitin);
    }
    

    public void lisaa(HenkiloJaInstrumentti soittaja) {
    	henkilotJaInstrumentit.lisaa(soittaja);
    }
    
    public void lisaaHloInstrumentti(Henkilo henkilo, Instrumentti instrumentti) {
    	HenkiloJaInstrumentti yhistys = new HenkiloJaInstrumentti(henkilo.getId(), instrumentti.getTunnusNro());
    	henkilotJaInstrumentit.lisaa(yhistys);
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
    
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
        return henkilotJaInstrumentit.soittimet(henkilonId);
   }
    
    
    public String soitin(int tunnusnro) {
    	return instrumentit.soitin(tunnusnro);
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
      //  bandbuddy.tulosta();
        
        int id1 = testiHenkilo1.getId();
        // int id2 = testiHenkilo2.getId();
        
        HenkiloJaInstrumentti soittaja = new HenkiloJaInstrumentti(id1); 
        soittaja.vastaaKitaranSoittajaa(id1);
        Instrumentti soitin = new Instrumentti("kitara");
        Instrumentti soitin2 = new Instrumentti("basso");
        Instrumentti soitin3 = new Instrumentti("rummut");
        
        soitin.rekisteroi();
        soitin2.rekisteroi();
        soitin3.rekisteroi();
        bandbuddy.lisaa(soitin);
        bandbuddy.lisaa(soitin2);
        bandbuddy.lisaa(soitin3);
       // bandbuddy.tulosta();
        
        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin);
        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin2);
        bandbuddy.lisaaHloInstrumentti(testiHenkilo1, soitin3);
        
        List<HenkiloJaInstrumentti> hlosoittimet = bandbuddy.soittimet(testiHenkilo1.getId());
        List<String> hlosoittimet1= new ArrayList<String>();
        for (HenkiloJaInstrumentti iNumerot : hlosoittimet)
        	hlosoittimet1.add(bandbuddy.soitin(iNumerot.getInstrumentinNro()));
        for (int i = 0; i < hlosoittimet.size(); i++)
        System.out.println(hlosoittimet1.get(i));
    	}
}
