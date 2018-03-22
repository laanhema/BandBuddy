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

public class Instrumentit2 {
	private int tunnusNro;
	private int jasenNro;
	private int instrumentinNro;
	
	private static int seuraavaNro = 1;
    /**
     * Testit
     * @param args ei k�yt�ss�
     */	
    public static void main(String[] args) {
    	Instrumentit2 testi= new Instrumentit2(3);
    	testi.tulosta(System.out);
    }
        
    /**
     * Alustaa parametrittoman soittajan ja instrumentin v�lisen yhteyden
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
	 * Aliustetaan soittajan ja instrumentin v�lisen yhteyden soittajan j�senen viitten vietyn� parametrina.
	 * @param jasenro j�senen viite
	 */
	public Instrumentit2(int jasenro) {
		this.jasenNro = jasenro;
	}
	/**
	 * Palauttaa j�senen viitenumeron
	 * @return j�senen viitenumeron
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
	 * Tulostaa j�senen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

}

