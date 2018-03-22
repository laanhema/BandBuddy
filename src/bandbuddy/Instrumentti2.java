/**
 * 
 */
package bandbuddy;

import java.io.*;
/**
 * Instrumentti2-luokka Yksittäisten instrumenttien alkiot
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class Instrumentti2 {
	private int tunnusNro;
	private String instrumentti;
	
	private static int seuraavaNro = 1;
    /**
     * Testiohjelma
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
         Instrumentti2 kitara = new Instrumentti2("kitara");
         kitara.tulosta(System.out);
    }
    /**
     * Alustetaan instrumentti ilman parametria
     */
	public Instrumentti2() {
		//
	}
	
	/**
	 * Alustetaan instrumentti parametrin kanssa
	 * @param instrumentti instrumentin nimi
	 */
	public Instrumentti2(String instrumentti) {
		this.instrumentti = instrumentti;
	}
	/**
	 * @return instrumentin viite.
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	/**
	 * @return Instrumentin nimi.
	 */
	public String getInstrumentti() {
		return instrumentti;
	}
	/**
	 * Asettaa seuraavan numeron instrumentin viitteelle.
	 * @return uusi tunnusnumero.
	 */
	public int rekisteroi() {
		tunnusNro = seuraavaNro;
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
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */ 
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}
