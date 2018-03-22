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
public class Instrumentit implements Iterable<Instrumentit2> {

	private String tiedosto = "";
	/* lista jäsenen ja instrumentin yhdistelmästä */
	private final Collection<Instrumentit2> alkiot = new ArrayList<Instrumentit2>();
	
    /**
     * testipääohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    	 Instrumentit soittajalista = new Instrumentit();
         Instrumentit2 jaba = new Instrumentit2(1);
         jaba.vastaaKitaranSoittajaa(1);
         Instrumentit2 dude = new Instrumentit2(1);
         dude.vastaaKitaranSoittajaa(1);
         Instrumentit2 basisti = new Instrumentit2(3);

         soittajalista.lisaa(jaba);
         soittajalista.lisaa(dude);
         soittajalista.lisaa(basisti);
         
         
         List<Instrumentit2> soittajalista2 = soittajalista.soittimet(1);
         for (int i = 0; i < soittajalista2.size(); i++)
         System.out.println(soittajalista2.get(i).getJasenNro());
         
    }
    /**
     * Hakee kaikki tietyn instrumentin soittajat
     * @param tunnusnro soittimen numero
     * @return tietorakenne jossa on tietyn instrumentin soittajat.
     */
    public List<Instrumentit2> soittimet(int tunnusnro) {
         List<Instrumentit2> kaikki = new ArrayList<Instrumentit2>();
         for (Instrumentit2 soittaja : alkiot)
              if (tunnusnro == soittaja.getInstrumentinNro()) kaikki.add(soittaja);
         return kaikki;
    }
    /**
     * lisää instrumentin ja käyttäjän suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param soitin instrumen
     */
    public void lisaa(Instrumentit2 soitin) {
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
    public Instrumentit() {
    	//
    }
    /**
     * Iteraattori kaikkien instrumenttejen ja niiden soittajien läpikäymiseen.
     * return instrumentti/käyttäjäiteraattori
     */
	@Override
	public Iterator<Instrumentit2> iterator() {
		return alkiot.iterator();
	}

}

