package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Genre-luokka
 * Yksittäinen genre
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class Genre {
    
	private int        tunnusNro;
	private String     genre           = "";
	
	private static int seuraavaNro     = 1;
	
    
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
     * Genre testigenre1 = new Genre("djent");
     * Genre testigenre2 = new Genre("rock");
     * testigenre1.rekisteroi();      
     * testigenre2.rekisteroi();  
     * testigenre1.getTunnusNro()             === 1;
     * testigenre2.getTunnusNro()             === 2;
     * testigenre1.getGenre()                 === "djent";
     * testigenre2.getGenre()                 === "rock";
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
	 * Genre testigenre4 = new Genre();
     * testigenre3.toString() === "3|Psytrance|";
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
	 * Genre testigenre6 = new Genre();
     * testigenre5.parse("");
     * testigenre6.parse("1|Pop|");
     * testigenre5.toString()           === "4|EDM|";
	 * testigenre6.toString()           === "1|Pop|";
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
	 * @example
	 * <pre name="test">
	 * Genre testigenre7 = new Genre();
	 * testigenre7.setNimi("Blues");
	 * testigenre7.toString() === "0|Blues|";
	 * </pre>
	 */
	public void setNimi(String merkkijono) {
	    this.genre = merkkijono;
	}
}