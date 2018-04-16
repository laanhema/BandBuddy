/**
 * 
 */
package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Instrumentti-luokka
 * Yksittäinen instrumentti
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 15.04.2018
 *
 */
public class Instrumentti {
	private int tunnusNro;
	private String instrumentti;
	
	private static int seuraavaNro = 1;
	
    
    /**
     * Alustetaan instrumentti ilman parametria
     */
	public Instrumentti() {
	}
	
	
	/**
	 * Alustetaan instrumentti parametrin kanssa
	 * @param instrumentti     instrumentin nimi merkkijonona
	 */
	public Instrumentti(String instrumentti) {
		this.instrumentti = instrumentti;
	}
	
	
	/**
	 * Antaa instrumentin tunnusnumeron
	 * @return     instrumentin tunnusnumero
	 */
	public int getTunnusNro() {
		return this.tunnusNro;
	}
	
	
	/**
	 * Antaa instrumentin nimen
	 * @return     instrumentin nimi
	 */
	public String getInstrumentti() {
		return this.instrumentti;
	}
	
		
	/**
	 * Rekisteröi instrumentin
	 * Asettaa instrumentille tunnusnumeron ja varmistaa että seuraavalle rekisteröitävälle
	 * instrumentille tulee yhden suurempi tunnusnumero
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
     * Rekisteröi instrumentin laittamalla instrumentin tunnusNro:ksi annetun numeron
     * ja varmistaa että seuraava rekisteröitävä instrumentti saa yhden suuremman tunnusnumeron
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
	 * Instrumentti testi1 = new Instrumentti("kitara");
     * testi1.rekisteroi();
     * testi1.toString() === "1|kitara|";
     * Instrumentti testi2 = new Instrumentti();
     * testi2.toString() === "0||";      
	 * </pre>
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getInstrumentti() + "|";
	}
	
	
	/**
	 * Poimii merkkijonosta instrumentin tiedot ja asettaa ne sen parametreihin
	 * @param s merkkijono josta otetaan tiedot
	 * @example
	 * <pre name="test">
	 * Instrumentti testi1 = new Instrumentti();
	 * testi1.parse("1|Kitara|);
	 * testi1.toString === "1|Kitara|";
	 * Instrumentti testi2 = new Instrumentti("Banjo");
	 * testi2.rekisteroi();
	 * testi2.parse("");
	 * testi2.toString === "2|Banjo|";
	 * </pre>
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
     * Tulostaa instrumentin tiedot
     * @param os tietovirta johon tulostetaan
     */ 
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
	
	
	/**
	 * Asettaa instrumentille nimen
	 * @param merkkijono   asetettava nimi
	 */
	public void setNimi(String merkkijono) {
	    this.instrumentti = merkkijono;
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
         Instrumentti kitara = new Instrumentti("kitara");
         kitara.tulosta(System.out);
    }
	
    
}
