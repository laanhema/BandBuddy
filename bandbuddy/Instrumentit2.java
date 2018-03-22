/**
 * 
 */
package bandbuddy;

import java.io.*;

/**
 * Instrumentit2-luokka
 * Luokka joka liittää instrumentin ja jäsenen idt keskenään.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */

public class Instrumentit2 {
	private int tunnusNro;
	private int jasenNro;
	private int instrumentinNro;
	
	private static int seuraavaNro = 1;
    /**
     * Testit
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	Instrumentit2 testi= new Instrumentit2(3);
    	testi.tulosta(System.out);
    }
        
    /**
     * Alustaa parametrittoman soittajan ja instrumentin välisen yhteyden
     */
	public Instrumentit2() {
		//
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
	 * Aliustetaan soittajan ja instrumentin välisen yhteyden soittajan jäsenen viitten vietynä parametrina.
	 * @param jasenro jäsenen viite
	 */
	public Instrumentit2(int jasenro) {
		this.jasenNro = jasenro;
	}
	/**
	 * Palauttaa jäsenen viitenumeron
	 * @return jäsenen viitenumeron
	 */
	public int getJasenNro() {
		return jasenNro;
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
        out.println(jasenNro);
      }
	/**
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}

