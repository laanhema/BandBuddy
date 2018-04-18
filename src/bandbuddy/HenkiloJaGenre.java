/**
 * 
 */
package bandbuddy;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * HenkiloJaGenre-luokka
 * Luokka joka liittää genren ja jäsenen idt keskenään.
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 18.04.2018
 *
 */
public class HenkiloJaGenre {
	private int tunnusNro;
	private int henkilonNro;
	private int genrenNro;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testipääluokka
     * @param args ei käytössä
     */	
    public static void main(String[] args) {
    	HenkiloJaGenre testi= new HenkiloJaGenre(3);
    	testi.tulosta(System.out);
    }
    
        
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
	 * @return palauttaa alkion viitenumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * @return genren viitenumeron
	 */
	public int getGenrenNro() {
		return genrenNro;
	}
	
	
	/**
	 * Rekisteröi HenkilöJaGenre-alkion
	 * Varmistaa että seuraava rekisteröitävä saa yhden suuremman id:n
     * @example
     * <pre name="test">
     *   HenkiloJaGenre testilinkki1 = new HenkiloJaGenre(1);
     *   testilinkki1.rekisteroi();      
     *   testilinkki1.getTunnusNro() === 1;
     *   HenkiloJaGenre testilinkki2 = new HenkiloJaGenre(1);
     *   testilinkki2.rekisteroi();      
     *   testilinkki2.getTunnusNro() === 2;
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
	 * Muuttaa alkion tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getHenkilonNro() + "|" + getGenrenNro() + "|";	
	}
	
	
	/**
	 * Poimii merkkijonosta tiedot ja laittaa ne alkion parametreihin
	 * Käytetään kun luetaan tiedostoa
	 * @param s tiedoston rivi josta otetaan tiedot
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		this.rekisteroi(Mjonot.erota(sb, '|', getTunnusNro()));
		henkilonNro = Mjonot.erota(sb, '|', getHenkilonNro());
		genrenNro = Mjonot.erota(sb, '|', getGenrenNro());
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
