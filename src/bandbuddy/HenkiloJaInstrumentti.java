/**
 * 
 */
package bandbuddy;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Instrumentit2-luokka
 * Luokka joka liittää instrumentin ja jäsenen idt keskenään.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class HenkiloJaInstrumentti {
	private int tunnusNro;
	private int henkilonNro;
	private int instrumentinNro;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testit
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	HenkiloJaInstrumentti testi= new HenkiloJaInstrumentti(3);
    	testi.tulosta(System.out);
    }
    
        
    /**
     * Alustaa parametrittoman soittajan ja instrumentin välisen yhteyden
     */
	public HenkiloJaInstrumentti() {
		//
	}
	

	/**
	 * Alustetaan soittajan ja instrumentin välisen yhteyden soittajan jäsenen viitten vietynä parametrina.
	 * @param jasenro jäsenen viite
	 */
	public HenkiloJaInstrumentti(int jasenro) {
		this.henkilonNro = jasenro;
	}
	
	
	/**
	 * Luo HenkiloJaInstrumentti-olion ja antaa sen atribuuteiksi henkilön id:n ja soittimen id:n
	 * @param henkilonId		henkilön id
	 * @param soittimenId		soittimen id
	 */
	public HenkiloJaInstrumentti(int henkilonId, int soittimenId) {
		this.henkilonNro = henkilonId;
		this.instrumentinNro = soittimenId;
	}
	
	
	/**
	 * Apumetodi, jolla voi testailla
	 * @param nro soittajan numero
	 */
	public void vastaaKitaranSoittajaa(int nro) {
          henkilonNro = nro;
          instrumentinNro = 1;
    }
	
	
	/**
	 * Palauttaa jäsenen viitenumeron
	 * @return jäsenen viitenumeron
	 */
	public int getHenkilonNro() {
		return this.henkilonNro;
	}
	
	
	/**
	 * @return palauttaa Instrumentit2 viitenumeron
	 */
	public int getTunnusNro() {
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
	public void rekisteroi() {
		tunnusNro = seuraavaNro++;
	}
	
	
    /**
     * Laittaa alkion tunnusNro:ksi annetun numeron
     * @param numero        laitettava numero
     */
    public void rekisteroi(int numero) {
        if (this.tunnusNro > 0)
            return;
        this.tunnusNro = numero;
        seuraavaNro++;
    }
	
	
	/**
	 * Tulostaa instrumentin tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
        out.println(henkilonNro);
      }
	
	
	/**
	 * muuttaa henkilön ja instrumentin yhteyden tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getHenkilonNro() + "|" + getInstrumentinNro() + "|";	
	}
	
	/**
	 * @param s tiedoston rivi josta otetaan tiedot
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		this.rekisteroi(Mjonot.erota(sb, '|', getTunnusNro()));
		henkilonNro = Mjonot.erota(sb, '|', getHenkilonNro());
		instrumentinNro = Mjonot.erota(sb, '|', getInstrumentinNro());
	}
	
	
	@Override
	public int hashCode() {
	    return tunnusNro;
	}
	
	
	/**
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

	
}