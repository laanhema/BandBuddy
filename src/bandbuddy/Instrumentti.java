/**
 * 
 */
package bandbuddy;

import java.io.*;
/**
 * Instrumentti-luokka Yksitt�isten instrumenttien alkiot
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentti {
	private int tunnusNro;
	private String instrumentti;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testiohjelma
     * @param args ei k�yt�ss�
     */	
    public static void main(String[] args) {
         Instrumentti kitara = new Instrumentti("kitara");
         kitara.tulosta(System.out);
    }
    
    
    /**
     * Alustetaan instrumentti ilman parametria
     */
	public Instrumentti() {
		//
	}
	
	
	/**
	 * Alustetaan instrumentti parametrin kanssa
	 * @param instrumentti instrumentin nimi
	 */
	public Instrumentti(String instrumentti) {
		this.instrumentti = instrumentti;
		
	}
	
	
	/**
	 * @return instrumentin viite.
	 */
	public int getTunnusNro() {
		return this.tunnusNro;
	}
	
	
	/**
	 * @return Instrumentin nimi.
	 */
	public String getInstrumentti() {
		return this.instrumentti;
	}
	
		
	/**
	 * Asettaa seuraavan numeron instrumentin viitteelle.
	 * @return uusi tunnusnumero.
	 *
     * @example
     * <pre name="test">
     *   Instrumentti testi = new Instrumentti("kitara");
     *   testi.rekisteroi();      
     *   testi.getTunnusNro() === 1;
     *   Instrumentti testi1 = new Instrumentti("rummut");
     *   testi1.rekisteroi();  
     *   testi1.getTunnusNro() === 2;
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
        out.println(instrumentti);
    }
	
	
	/**
	 * Asettaa instrumentille nimen
	 * @param merkkijono   asetettava nimi
	 */
	public void setNimi(String merkkijono) {
	    this.instrumentti = merkkijono;
	}
	
	
	 /**
	 * Tulostaa j�senen tiedot
	 * @param os tietovirta johon tulostetaan
	 */ 
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}
