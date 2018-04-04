/**
 * 
 */
package bandbuddy;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Instrumentti-luokka Yksittäisten instrumenttien alkiot
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentti {
	private int tunnusNro;
	private String instrumentti;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testiohjelma
     * @param args ei käytössä
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
	public void rekisteroi() {
		tunnusNro = seuraavaNro++;
	}
	
	
    /**
     * Laittaa instrumentin tunnusNro:ksi annetun numeron
     * @param numero        laitettava numero
     */
    public void rekisteroi(int numero) {
        if (this.tunnusNro > 0)
            return;
        this.tunnusNro = numero;
        seuraavaNro++;
    }
	
	
	/**
	 * muuttaa instrumentin tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getInstrumentti() + "|";
		
	}
	
	@Override
	public int hashCode() {
	    return tunnusNro;
	}
	/**
	 * @param s tiedoston rivi josta otetaan tiedot
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		this.rekisteroi(Mjonot.erota(sb, '|', getTunnusNro()));
		setNimi(Mjonot.erota(sb, '|', getInstrumentti()));
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
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */ 
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}
