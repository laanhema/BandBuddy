/**
 * 
 */
package bandbuddy;

import java.util.*;

/**
 * Instrumentti-luokka
 * Instrumentti2 luokan ylläpito alkiomuodossa.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentti implements Iterable<Instrumentti2> {

	private String tiedosto = "";
	/* Instrumenttien lista */
	private final Collection<Instrumentti2> alkiot = new ArrayList<Instrumentti2>();
	
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	 Instrumentti soitinlista = new Instrumentti();
         Instrumentti2 kitara = new Instrumentti2("kitara");
         Instrumentti2 rummut = new Instrumentti2("rummut");
         Instrumentti2 basso = new Instrumentti2("basso");

         soitinlista.lisaa(kitara);
         soitinlista.lisaa(rummut);
         soitinlista.lisaa(basso);
         
         
         List<Instrumentti2> soitinlista2 = soitinlista.soittimet(2);
         for (int i = 0; i < soitinlista2.size(); i++)
         System.out.println(soitinlista2.get(i).getInstrumentti());
         
    }
    /**
     * Hakee kaikki instrumentit
     * @param tunnusnro toistaiseksi ei mitään
     * @return tietorakenne jossa on kaikki instrumentit.
     */
    public List<Instrumentti2> soittimet(int tunnusnro) {
         List<Instrumentti2> kaikki = new ArrayList<Instrumentti2>();
         for (Instrumentti2 soitin : alkiot)
              kaikki.add(soitin);
         return kaikki;
    }
    /**
     * lisää instrumentin ja ottaa sen omistukseen tietorakenteessa
     * @param soitin lisättävä instrumentti.
     */
    public void lisaa(Instrumentti2 soitin) {
    	alkiot.add(soitin);
    }

    /**
     * @return instrumentin listan koko
     */
    public int getLkm() {
    	return alkiot.size();
    }
    /**
     * Instrumentin alustaminen
     */
    public Instrumentti() {
    	//
    }
    /**
     * Iteraattori kaikkien instrumenttejen läpikäymiseen.
     * return instrumentti-iteraattori
     */
	@Override
	public Iterator<Instrumentti2> iterator() {
		return alkiot.iterator();
	}

}
