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
public class Instrumentit implements Iterable<Instrumentti> {
	// private String tiedosto = "";
	/* Instrumenttien lista */
	private final Collection<Instrumentti> alkiot = new ArrayList<Instrumentti>();
	
	
    /**
     * Testipääohjelma
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	 Instrumentit soitinlista = new Instrumentit();
         Instrumentti kitara = new Instrumentti("kitara");
         Instrumentti rummut = new Instrumentti("rummut");
         Instrumentti basso = new Instrumentti("basso");

         soitinlista.lisaa(kitara);
         soitinlista.lisaa(rummut);
         soitinlista.lisaa(basso);
         
         
       //  List<Instrumentti> soitinlista2 = soitinlista.soittimet(2);
       //  for (int i = 0; i < soitinlista2.size(); i++)
       //  System.out.println(soitinlista2.get(i).getInstrumentti());
         
         
    }
    /**
     * Hakee kaikki instrumentit
     * @param tunnusnro toistaiseksi ei mitään
     * @return tietorakenne jossa on kaikki instrumentit.
     */
    public String soitin(int tunnusnro) {
         List<Instrumentti> kaikki = new ArrayList<Instrumentti>();
         for (Instrumentti soitin : alkiot) {
        	  kaikki.add(soitin);
         }
         for (int i = 0; i < kaikki.size(); i++) {
              if (tunnusnro == kaikki.get(i).getTunnusNro())return kaikki.get(i).getInstrumentti();
         }
         return "ei toimi";        
    }
    
    
    /**
     * lisää instrumentin ja ottaa sen omistukseen tietorakenteessa
     * @param soitin lisättävä instrumentti.
     */
    public void lisaa(Instrumentti soitin) {
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
    public Instrumentit() {
    	//
    }
    
    
    /**
     * Iteraattori kaikkien instrumenttejen läpikäymiseen.
     * return instrumentti-iteraattori
     */
	@Override
	public Iterator<Instrumentti> iterator() {
		return alkiot.iterator();
	}

}
