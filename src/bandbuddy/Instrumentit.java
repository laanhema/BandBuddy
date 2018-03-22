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
public class Instrumentit implements Iterable<Instrumentit2> {

	private String tiedosto = "";
	/* lista j�senen ja instrumentin yhdistelm�st� */
	private final Collection<Instrumentit2> alkiot = new ArrayList<Instrumentit2>();
	
    /**
     * testip��ohjelma
     * @param args ei k�yt�ss�
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
     * lis�� instrumentin ja k�ytt�j�n suhteen ja ottaa sen omistukseensa tietorakenteessa.
     * @param soitin instrumen
     */
    public void lisaa(Instrumentit2 soitin) {
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
    public Instrumentit() {
    	//
    }
    /**
     * Iteraattori kaikkien instrumenttejen ja niiden soittajien l�pik�ymiseen.
     * return instrumentti/k�ytt�j�iteraattori
     */
	@Override
	public Iterator<Instrumentit2> iterator() {
		return alkiot.iterator();
	}

}

