/**
 * 
 */
package bandbuddy;

import java.io.*;
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
	
	
	public void vastaaKitaraa(int nro) {
		if (nro == 1) this.instrumentti = "kitara";
	}
	
	
	/**
	 * Asettaa seuraavan numeron instrumentin viitteelle.
	 * @return uusi tunnusnumero.
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
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */ 
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}
