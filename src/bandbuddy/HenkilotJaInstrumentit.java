/**
 * 
 */
package bandbuddy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Instrumentit-luokka, yll�pit�� henkil�n ja instrumentin yhdist�misviitteit� alkiomuodossa.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class HenkilotJaInstrumentit implements Iterable<HenkiloJaInstrumentti> {
	// private String tiedosto = "";
	/* lista j�senen ja instrumentin yhdistelm�st� */
	private final Collection<HenkiloJaInstrumentti> alkiot = new ArrayList<HenkiloJaInstrumentti>();
	
	
    /**
     * testip��ohjelma
     * @param args ei k�yt�ss�
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
     * @param henkilonId soittimen numero
     * @return tietorakenne jossa on tietyn soittajan instrumentit.
     * #import java.util.*;
     * @example
     * <pre name="test">
     *  HenkilotJaInstrumentit hi = new HenkilotJaInstrumentit();
     *  HenkiloJaInstrumentti a = new HenkiloJaInstrumentti(1); hi.lisaa(a);
     *  HenkiloJaInstrumentti b = new HenkiloJaInstrumentti(1); hi.lisaa(b);
     *  HenkiloJaInstrumentti c = new HenkiloJaInstrumentti(2); hi.lisaa(c);
     *  List<HenkiloJaInstrumentti> kaikki;
     *  kaikki = hi.soittimet(1);
     *  kaikki.size() === 2; 
     * </pre> 
     */
    public List<HenkiloJaInstrumentti> soittimet(int henkilonId) {
         List<HenkiloJaInstrumentti> kaikki = new ArrayList<HenkiloJaInstrumentti>();
         for (HenkiloJaInstrumentti soittaja : alkiot)
              if (henkilonId == soittaja.getHenkilonNro()) kaikki.add(soittaja);
         return kaikki;
    }
    
    
    /**
     * lis�� instrumentin ja k�ytt�j�n suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param soitin instrumen
     */
    public void lisaa(HenkiloJaInstrumentti soitin) {
    	alkiot.add(soitin);
    }

    
    /**
     * @return instrumenttien lukum��r�.
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
    
    
	@Override
	public Iterator<HenkiloJaInstrumentti> iterator() {
		return alkiot.iterator();
	}

}

