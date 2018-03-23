/**
 * 
 */
package bandbuddy;

import java.io.*;

/**
 * Instrumentit2-luokka
 * Luokka joka liitt�� instrumentin ja j�senen idt kesken��n.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class HenkiloJaInstrumentti {
	private int tunnusNro;
	private int jasenNro;
	private int instrumentinNro;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testit
     * @param args ei k�yt�ss�
     */	
    public static void main(String[] args) {
    	HenkiloJaInstrumentti testi= new HenkiloJaInstrumentti(3);
    	testi.tulosta(System.out);
    }
    
        
    /**
     * Alustaa parametrittoman soittajan ja instrumentin v�lisen yhteyden
     */
	public HenkiloJaInstrumentti() {
		//
	}
	

	/**
	 * Alustetaan soittajan ja instrumentin v�lisen yhteyden soittajan j�senen viitten vietyn� parametrina.
	 * @param jasenro j�senen viite
	 */
	public HenkiloJaInstrumentti(int jasenro) {
		this.jasenNro = jasenro;
	}
	
	
	/**
	 * Luo HenkiloJaInstrumentti-olion ja antaa sen atribuuteiksi henkil�n id:n ja soittimen id:n
	 * @param henkilonId		henkil�n id
	 * @param soittimenId		soittimen id
	 */
	public HenkiloJaInstrumentti(int henkilonId, int soittimenId) {
		this.jasenNro = henkilonId;
		this.instrumentinNro = soittimenId;
	}
	
	
	/**
	 * Apumetodi, jolla voi testailla
	 * @param nro soittajan numero
	 */
	public void vastaaKitaranSoittajaa(int nro) {
          jasenNro = nro;
          instrumentinNro = 1;
    }
	
	
	/**
	 * Palauttaa j�senen viitenumeron
	 * @return j�senen viitenumeron
	 */
	public int getHenkilonNro() {
		return this.jasenNro;
	}
	
	
	/**
	 * @return palauttaa Instrumentit2 viitenumeron
	 */
	public int getTunnusNroT() {
		return tunnusNro;
	}
	
	
	/**
	 * @return instrumentin viitenumeron
	 */
	public int getInstrumentinNro() {
		return instrumentinNro;
	}
	/**
	 * antaa seuraavan tunnusnumeron insrumentin ja sen soittajan viittelle.
	 * @return uusi tunnusnumero
     * @example
     * <pre name="test">
     *   HenkiloJaInstrumentti soittaja= new HenkiloJaInstrumentti(1);
     *   soittaja.rekisteroi();      
     *   soittaja.getTunnusNroT() === 1;
     *   HenkiloJaInstrumentti soittaja2= new HenkiloJaInstrumentti(1);
     *   soittaja2.rekisteroi();      
     *   soittaja2.getTunnusNroT() === 2;
     * </pre>
     */
	public int rekisteroi() {
		tunnusNro = seuraavaNro++;
		return tunnusNro;
	}
	
	
	/**
	 * Tulostaa instrumentin tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
        out.println(jasenNro);
      }
	
	
	/**
	 * Tulostaa j�senen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}

