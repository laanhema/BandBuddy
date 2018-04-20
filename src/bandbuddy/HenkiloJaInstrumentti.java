package bandbuddy;

import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * HenkiloJaInstrumentti-luokka
 * Luokka joka liitt�� instrumentin ja j�senen idt kesken��n.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class HenkiloJaInstrumentti {
    
	private int        henkilonNro;
	private int        instrumentinNro;
	
	
    /**
     * Parametriton muodostaja HenkiloJaInstrumentti-oliolle
     */
	public HenkiloJaInstrumentti() {
	}
	

	/**
	 * Alustaa HenkiloJaInstrumentti-olion ja laittaa henkil�n id:n sen attribuutteihin
	 * @param henkilonId henkil�n viite
	 */
	public HenkiloJaInstrumentti(int henkilonId) {
		this.henkilonNro = henkilonId;
	}
	
	
	/**
	 * Luo HenkiloJaInstrumentti-olion ja antaa sen atribuuteiksi henkil�n id:n ja soittimen id:n
	 * @param henkilonId		henkil�n id
	 * @param soittimenId		soittimen id
	 */
	public HenkiloJaInstrumentti(int henkilonId, int soittimenId) {
		this.henkilonNro = henkilonId;
		this.instrumentinNro = soittimenId;
	}
	
	
	/**
	 * Apumetodi, jolla voi testailla
	 * @param nro soittajan numero
	 */
	public void vastaaKitaranSoittajaa(int nro) {
          henkilonNro = nro;
          instrumentinNro = 1;
    }
	
	
	/**
	 * Palauttaa henkil�n viitenumeron
	 * @return henkil�n viitenumeron
	 */
	public int getHenkilonNro() {
		return this.henkilonNro;
	}
	
	
	/**
	 * Palauttaa instrumentin viitenumeron
	 * @return instrumentin viitenumeron
	 */
	public int getInstrumentinNro() {
		return instrumentinNro;
	}
	
	
	/**
	 * Tulostaa alkion tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
        out.println(henkilonNro);
    }
	
	
	/**
	 * muuttaa henkil�n ja instrumentin yhteyden tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return getHenkilonNro() + "|" + getInstrumentinNro() + "|";	
	}
	
	
	/**
	 * Poimii merkkijonosta tiedot ja laittaa ne olion attribuutteihin
	 * K�ytet��n kun luetaan tiedostoa
	 * @param s tiedoston rivi josta otetaan tiedot
	 * @example
	 * <pre name="test">
	 * HenkiloJaInstrumentti testialkio1 = new HenkiloJaInstrumentti(3, 7);
     * testialkio1.getHenkilonNro()         === 3;
     * testialkio1.getInstrumentinNro()     === 7;
     * testialkio1.toString()               === "3|7|";
     * testialkio1.parse("11|2|");
     * testialkio1.getHenkilonNro()         === 11;
     * testialkio1.getInstrumentinNro()     === 2;
	 * </pre>
	 */
	public void parse(String s) {
		StringBuffer sb = new StringBuffer(s);
		henkilonNro = Mjonot.erota(sb, '|', getHenkilonNro());
		instrumentinNro = Mjonot.erota(sb, '|', getInstrumentinNro());
	}
	
	
	/**
	 * Tulostaa j�senen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }	
}