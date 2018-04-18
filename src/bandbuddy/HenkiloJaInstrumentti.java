/**
 * 
 */
package bandbuddy;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * HenkiloJaInstrumentti-luokka
 * Luokka joka liittää instrumentin ja jäsenen idt keskenään.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 18.04.2018
 *
 */
public class HenkiloJaInstrumentti {
	private int tunnusNro;
	private int henkilonNro;
	private int instrumentinNro;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testipääluokka
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	HenkiloJaInstrumentti testi= new HenkiloJaInstrumentti(3);
    	testi.tulosta(System.out);
    }
    
        
    /**
     * Parametriton muodostaja HenkiloJaInstrumentti-oliolle
     */
	public HenkiloJaInstrumentti() {
	}
	

	/**
	 * Alustaa HenkiloJaInstrumentti-olion ja laittaa henkilön id:n sen attribuutteihin
	 * @param henkilonId henkilön viite
	 */
	public HenkiloJaInstrumentti(int henkilonId) {
		this.henkilonNro = henkilonId;
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
	 * Palauttaa henkilön viitenumeron
	 * @return henkilön viitenumeron
	 */
	public int getHenkilonNro() {
		return this.henkilonNro;
	}
	
	
	/**
	 * Palauttaa alkion viitenumeron
	 * @return palauttaa alkion viitenumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * Palauttaa instrumentin viitenumeron
	 * @return instrumentin viitenumeron
	 */
	public int getInstrumentinNro() {
		return instrumentinNro;
	}
	
	
	/**
	 * Rekisteröi alkion.
	 * Varmistaa että seuraava alkio saa yhden suuremman id:n.
     * @example
     * <pre name="test">
     *   HenkiloJaInstrumentti soittaja= new HenkiloJaInstrumentti(1);
     *   soittaja.rekisteroi();      
     *   soittaja.getTunnusNro() === 1;
     *   HenkiloJaInstrumentti soittaja2= new HenkiloJaInstrumentti(1);
     *   soittaja2.rekisteroi();      
     *   soittaja2.getTunnusNro() === 2;
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
	 * Tulostaa alkion tiedot
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
	 * Poimii merkkijonosta tiedot ja laittaa ne olion attribuutteihin
	 * Käytetään kun luetaan tiedostoa
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