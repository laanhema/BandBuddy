/**
 * 
 */
package bandbuddy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Instrumentit-luokka, ylläpitää henkilön ja instrumentin yhdistämisviitteitä alkiomuodossa.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class HenkilotJaInstrumentit implements Iterable<HenkiloJaInstrumentti> {
	// private String tiedosto = "";
	/* lista jäsenen ja instrumentin yhdistelmästä */
	private final Collection<HenkiloJaInstrumentti> alkiot = new ArrayList<HenkiloJaInstrumentti>();
	
	
    /**
     * testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    	 HenkilotJaInstrumentit soittajalista = new HenkilotJaInstrumentit();
         HenkiloJaInstrumentti jaba = new HenkiloJaInstrumentti(1);
         jaba.vastaaKitaranSoittajaa(1);
         HenkiloJaInstrumentti dude = new HenkiloJaInstrumentti(1);
         dude.vastaaKitaranSoittajaa(1);
         HenkiloJaInstrumentti basisti = new HenkiloJaInstrumentti(3);

         soittajalista.lisaa(jaba);
         soittajalista.lisaa(dude);
         soittajalista.lisaa(basisti);
         
         
         List<HenkiloJaInstrumentti> soittajalista2 = soittajalista.soittimet(1);
         for (int i = 0; i < soittajalista2.size(); i++)
         System.out.println(soittajalista2.get(i).getHenkilonNro());
         
    }
    
    
    /**
     * Hakee kaikki tietyn soittajan instrumentit
     * @param tunnusnro soittimen numero
     * @return tietorakenne jossa on tietyn soittajan instrumentit.
     */
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
         List<HenkiloJaInstrumentti> kaikki = new ArrayList<HenkiloJaInstrumentti>();
         for (HenkiloJaInstrumentti soittaja : alkiot)
              if (henkilonId == soittaja.getHenkilonNro()) kaikki.add(soittaja);
         return kaikki;
    }
    
    
    /**
     * lisää instrumentin ja käyttäjän suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param soitin instrumen
     */
    public void lisaa(HenkiloJaInstrumentti soitin) {
    	alkiot.add(soitin);
    }

    
    /**
     * @return instrumenttien lukumäärä.
     */
    public int getLkm() {
    	return alkiot.size();
    }
    
    
    /**
     * Instrumenttien alustaminen
     */
    public HenkilotJaInstrumentit() {
    	//
    }
    
    
    /**
     * Iteraattori kaikkien instrumenttejen ja niiden soittajien läpikäymiseen.
     * return instrumentti/käyttäjäiteraattori
     */
	@Override
	public Iterator<HenkiloJaInstrumentti> iterator() {
		return alkiot.iterator();
	}

}

