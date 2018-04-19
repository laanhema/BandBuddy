package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Instrumentti-luokka
 * Yksittäinen instrumentti
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class Instrumentti {
    
	private int        tunnusNro;
	private String     instrumentti        = "";
	
	private static int seuraavaNro         = 1;
	
    
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
     *   Instrumentti testisoitin1 = new Instrumentti("kitara");
     *   testisoitin1.rekisteroi();      
     *   testisoitin1.getTunnusNro() === 1;
     *   Instrumentti testisoitin2 = new Instrumentti("rummut");
     *   testisoitin2.rekisteroi();  
     *   testisoitin2.getTunnusNro() === 2;
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
	 * Instrumentti testisoitin3 = new Instrumentti("kitara");
     * testisoitin3.rekisteroi();
     * testisoitin3.toString() === "3|kitara|";
     * Instrumentti testisoitin4 = new Instrumentti();
     * testisoitin4.toString() === "0||";      
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
	 * Instrumentti testisoitin5 = new Instrumentti("Banjo");
	 * testisoitin5.rekisteroi();
	 * testisoitin5.parse("");
	 * testisoitin5.toString() === "4|Banjo|";
	 * Instrumentti testisoitin6 = new Instrumentti();
     * testisoitin6.parse("1|Kitara|");
     * testisoitin6.toString() === "1|Kitara|";
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
}