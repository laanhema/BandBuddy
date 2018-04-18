/**
 * 
 */
package bandbuddy;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Instrumentit2-luokka
 * Luokka joka liitt�� instrumentin ja j�senen idt kesken��n.
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22 Mar 2018
 *
 */
public class HenkiloJaGenre {
	private int tunnusNro;
	private int henkilonNro;
	private int genrenNro;
	
	private static int seuraavaNro = 1;
	
	
    /**
     * Testit
     * @param args ei k�yt�ss�
     */	
    public static void main(String[] args) {
    	HenkiloJaGenre testi= new HenkiloJaGenre(3);
    	testi.tulosta(System.out);
    }
    
        
    /**
     * Alustaa parametrittoman soittajan ja instrumentin v�lisen yhteyden
     */
	public HenkiloJaGenre() {
		//
	}
	

	/**
	 * Alustetaan soittajan ja instrumentin v�lisen yhteyden soittajan j�senen viitten vietyn� parametrina.
	 * @param jasenro j�senen viite
	 */
	public HenkiloJaGenre(int jasenro) {
		this.henkilonNro = jasenro;
	}
	
	
	/**
	 * Luo HenkiloJaInstrumentti-olion ja antaa sen atribuuteiksi henkil�n id:n ja soittimen id:n
	 * @param henkilonId		henkil�n id
	 * @param soittimenId		soittimen id
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
	 * Palauttaa j�senen viitenumeron
	 * @return j�senen viitenumeron
	 */
	public int getHenkilonNro() {
		return this.henkilonNro;
	}
	
	
	/**
	 * @return palauttaa Instrumentit2 viitenumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * @return instrumentin viitenumeron
	 */
	public int getGenrenNro() {
		return genrenNro;
	}
	/**
	 * antaa seuraavan tunnusnumeron insrumentin ja sen soittajan viittelle.
     * @example
     * <pre name="test">
     *   HenkiloJaInstrumentti soittaja= new HenkiloJaInstrumentti(1);
     *   soittaja.rekisteroi();      
     *   soittaja.getTunnusNroT() === 1;
     *   HenkiloJaInstrumentti soittaja2= new HenkiloJaInstrumentti(1);
     *   soittaja2.rekisteroi();      
     *   soittaja2.getTunnusNroT() === 2;
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
	 * muuttaa henkil�n ja instrumentin yhteyden tiedot merkkijonoksi
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + getHenkilonNro() + "|" + getGenrenNro() + "|";	
	}
	
	/**
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
	 * Tulostaa j�senen tiedot
	 * @param os tietovirta johon tulostetaan
	 */
	public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

	
}
