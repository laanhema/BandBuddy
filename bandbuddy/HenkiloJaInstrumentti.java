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
public class HenkiloJaInstrumentti {
	private int tunnusNro;
	private int jasenNro;
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
		this.jasenNro = jasenro;
	}
	
	
	/**
	 * Luo HenkiloJaInstrumentti-olion ja antaa sen atribuuteiksi henkilön id:n ja soittimen id:n
	 * @param jasenNro		henkilön id
	 * @param soitinNro		soittimen id
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
	 * Palauttaa jäsenen viitenumeron
	 * @return jäsenen viitenumeron
	 */
	public int getHenkilonNro() {
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

