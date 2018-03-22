/**
 * 
 */
package bandbuddy;

import java.util.*;

/**
 * Instrumentti-luokka
 * Instrumentti2 luokan yll�pito alkiomuodossa.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentti implements Iterable<Instrumentti2> {

	private String tiedosto = "";
	/* Instrumenttien lista */
	private final Collection<Instrumentti2> alkiot = new ArrayList<Instrumentti2>();
	
    /**
     * Testip��ohjelma
     * @param args ei k�yt�ss�
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
     * @param tunnusnro toistaiseksi ei mit��n
     * @return tietorakenne jossa on kaikki instrumentit.
     */
    public List<Instrumentti2> soittimet(int tunnusnro) {
         List<Instrumentti2> kaikki = new ArrayList<Instrumentti2>();
         for (Instrumentti2 soitin : alkiot)
              kaikki.add(soitin);
         return kaikki;
    }
    /**
     * lis�� instrumentin ja ottaa sen omistukseen tietorakenteessa
     * @param soitin lis�tt�v� instrumentti.
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
     * Iteraattori kaikkien instrumenttejen l�pik�ymiseen.
     * return instrumentti-iteraattori
     */
	@Override
	public Iterator<Instrumentti2> iterator() {
		return alkiot.iterator();
	}

}
