package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * HenkiloJaGenre-luokka
 * Luokka joka liittää genren ja jäsenen idt keskenään.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class HenkiloJaGenre {
    
	private int        henkilonNro;
	private int        genrenNro;
	
	 
    /**
     * Alustaa HenkiloJaGenre-olion ilman parametrejä
     */
	public HenkiloJaGenre() {
	}
	

	/**
	 * Alustaa HenkiloJaGenre-olion viemällä henkilön id:n parametrina ja laittamalla sen attribuutiksi.
	 * @param henkilonId jäsenen viite
	 */
	public HenkiloJaGenre(int henkilonId) {
		this.henkilonNro = henkilonId;
	}
	
	
	/**
	 * Luo HenkiloJaGenre-olion ja antaa sen attribuuteiksi henkilön id:n ja genren id:n
	 * @param henkilonId		henkilön id
	 * @param genrenId		genren id
	 */
	public HenkiloJaGenre(int henkilonId, int genrenId) {
		this.henkilonNro = henkilonId;
		this.genrenNro = genrenId;
	}
	
	
	/**
	 * Apumetodi, jolla voi testailla
	 * @param nro soittajan numero
	 */
	public void vastaaKitaranSoittajaa(int nro) {
          henkilonNro = nro;
          genrenNro = 1;
    }
	
	
	/**
	 * Palauttaa henkilön viitenumeron
	 * @return henkilön viitenumeron
	 */
	public int getHenkilonNro() {
		return this.henkilonNro;
	}
	
	
	/**
	 * @return genren viitenumeron
	 */
	public int getGenrenNro() {
		return genrenNro;
	}
	

	/**
	 * Tulostaa instrumentin tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
        out.println(henkilonNro);
      }
	
	
	/**
	 * Muuttaa alkion tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return getHenkilonNro() + "|" + getGenrenNro() + "|";	
	}
	
	
	/**
	 * Poimii merkkijonosta tiedot ja laittaa ne alkion parametreihin
	 * Käytetään kun luetaan tiedostoa
	 * @param s tiedoston rivi josta otetaan tiedot
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		henkilonNro = Mjonot.erota(sb, '|', getHenkilonNro());
		genrenNro = Mjonot.erota(sb, '|', getGenrenNro());
	}
	

	/**
	 * Tulostaa jäsenen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }	
}