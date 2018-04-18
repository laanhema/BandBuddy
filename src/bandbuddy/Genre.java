/**
 * 
 */
package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Genre-luokka
 * Yksittäinen genre
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 18.04.2018
 *
 */
public class Genre {
	private int tunnusNro;
	private String genre = "";
	
	private static int seuraavaNro = 1;
	
    
    /**
     * Alustetaan genre ilman parametria
     */
	public Genre() {
	}
	
	
	/**
	 * Alustetaan genre parametrin kanssa
	 * @param genre     genren nimi merkkijonona
	 */
	public Genre(String genre) {
		this.genre = genre;
	}
	
	
	/**
	 * Antaa genren tunnusnumeron
	 * @return     genren tunnusnumero
	 */
	public int getTunnusNro() {
		return this.tunnusNro;
	}
	
	
	/**
	 * Antaa genren nimen
	 * @return     genren nimi
	 */
	public String getGenre() {
		return this.genre;
	}
	
		
	/**
	 * Rekisteröi genren
	 * Asettaa genrelle tunnusnumeron ja varmistaa että seuraavalle rekisteröitävälle
	 * genrelle tulee yhden suurempi tunnusnumero
     * @example
     * <pre name="test">
     *   Genre testigenre1 = new Genre("djent");
     *   testigenre1.rekisteroi();      
     *   testigenre1.getTunnusNro() === 1;
     *   Genre testigenre2 = new Genre("rock");
     *   testigenre2.rekisteroi();  
     *   testigenre2.getTunnusNro() === 2;
     * </pre>
     */
	public void rekisteroi() {
		tunnusNro = seuraavaNro++;
	}
	
	
    /**
     * Rekisteröi genren laittamalla genren tunnusNro:ksi annetun numeron
     * ja varmistaa että seuraava rekisteröitävä genre saa yhden suuremman tunnusnumeron
     * Tätä metodia käytetään kun luetaan tiedostosta
     * @param numero        laitettava numero
     */
    public void rekisteroi(int numero) {
        if (this.tunnusNro > 0) return;
        this.tunnusNro = numero;
        seuraavaNro++;
    }
	
	
	/**
	 * Muuttaa instrumentin tiedot merkkijonoksi
	 * @example
	 * <pre name="test">
	 * Genre testigenre3 = new Genre("Psytrance");
     * testigenre3.rekisteroi();
     * testigenre3.toString() === "3|Psytrance|";
     * Genre testigenre4 = new Genre();
     * testigenre4.toString() === "0||";      
	 * </pre>
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getGenre() + "|";
	}
	
	
	/**
	 * Poimii merkkijonosta genren tiedot ja asettaa ne sen parametreihin
	 * @param s merkkijono josta otetaan tiedot
	 * @example
	 * <pre name="test">
	 * Genre testigenre5 = new Genre("EDM");
	 * testigenre5.rekisteroi();
     * testigenre5.parse("");
     * testigenre5.toString() === "4|EDM|";
	 * Genre testigenre6 = new Genre();
	 * testigenre6.parse("1|Pop|");
	 * testigenre6.toString() === "1|Pop|";
	 * </pre>
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		this.rekisteroi(Mjonot.erota(sb, '|', getTunnusNro()));
		setNimi(Mjonot.erota(sb, '|', getGenre()));
	}
	
	
	/**
	 * Tulostaa genren tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
        out.println(genre);
    }
	
	
    /**
     * Tulostaa genren tiedot
     * @param os tietovirta johon tulostetaan
     */ 
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
	
	
	/**
	 * Asettaa genrelle nimen
	 * @param merkkijono   asetettava nimi
	 */
	public void setNimi(String merkkijono) {
	    this.genre = merkkijono;
	}
	
	
	@Override
	public int hashCode() {
	    return tunnusNro;
	}
	

    /**
     * Testipääohjelma
     * @param args ei käytössä
     */ 
    public static void main(String[] args) {
         Genre metalli = new Genre("metalli");
         metalli.tulosta(System.out);
    }
	
    
}
