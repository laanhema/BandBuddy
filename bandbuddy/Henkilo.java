/**
 * 
 */
package bandbuddy;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Henkil�-luokka
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 12.03.2018
 */
public class Henkilo {
    private int         id                  = 0;
    private String      nimi                = "";
    private int         ika                 = 0;
    private String      sukupuoli           = "";
    private String      paikkakunta         = "";
    private String      vapaana             = "";
    private String      kokemus             = "";
    private String      yhteystiedot        = "";
    
    private static int  seuraavaId          = 1;
    
    
    /**
     * Parametriton konstruktori Henkil�lle
     */
    public Henkilo() {
        // t�m� on tulevaisuuden varalle jos lis�t��n uusi konstruktoreita
    }
        
        
    /**
     * Rekister�i henkil�n eli antaa henkil�lle id:n
     */
    public void rekisteroi() {
        this.id = seuraavaId++;
    }
    
    
    /**
     * Palauttaa henkil�n id:n
     * @return      henkil�n id
     */
    public int getId() {
        return this.id;
    }
    
    
    /**
     * Palauttaa henkil�n nimen
     * @return      henkil�n nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Asettaa henkil�lle uuden nimen
     * @param uusiNimi      uusi nimi
     */
    public void setNimi(String uusiNimi) {
        this.nimi = uusiNimi;
    }
    
    
    /**
     * Palauttaa henkil�n i�n
     * @return      henkil�n ik�
     */
    public int getIka() {
        return this.ika;
    }
    
    
    /**
     * Asettaa henkil�lle uuden i�n
     * @param uusiIka      uusi ik�
     */
    public void setIka(int uusiIka) {
        this.ika = uusiIka;
    }
    
    
    /**
     * Palauttaa henkil�n sukupuolen
     * @return      henkil�n sukupuoli
     */
    public String getSukupuoli() {
        return this.sukupuoli;
    }
    
    
    /**
     * Asettaa henkil�lle uuden sukupuolen
     * @param uusiSukupuoli     uusi sukupuoli
     */
    public void setSukupuoli(String uusiSukupuoli) {
        this.sukupuoli = uusiSukupuoli;
    }
    
    
    /**
     * Palauttaa mill� paikkakunnalla henkil� asuu
     * @return      henkil�n paikkakunta
     */
    public String getPaikkakunta() {
        return this.paikkakunta;
    }
    
    
    /**
     * Asettaa henkil�lle uuden paikkakunnan
     * @param uusiPaikkakunta      uusi paikkakunta
     */
    public void setPaikkakunta(String uusiPaikkakunta) {
        this.paikkakunta = uusiPaikkakunta;
    }
    
    
    /**
     * Palauttaa milloin henkil� on vapaana
     * @return      ajankohta
     */
    public String getVapaana() {
        return this.vapaana;
    }
    
    
    /**
     * Asettaa henkil�lle uuden ajankohdan milloin h�n on vapaana
     * @param uusiVapaana      uusi ajankohta
     */
    public void setVapaana(String uusiVapaana) {
        this.vapaana = uusiVapaana;
    }
    
    
    /**
     * Palauttaa henkil�n kokemuksen m��r�n
     * @return      kokemuksen m��r�
     */
    public String getKokemus() {
        return this.kokemus;
    }
    
    
    /**
     * Asettaa henkil�lle uuden kokemus-kent�n
     * @param uusiKokemus   uusi kokemus-kentt�
     */
    public void setKokemus(String uusiKokemus) {
        this.kokemus = uusiKokemus;
    }
    
    
    /**
     * Palauttaa henkil�n yhteystiedot
     * @return      henkil�n yhteystiedot
     */
    public String getYhteystiedot() {
        return this.yhteystiedot;
    }
    
    
    /**
     * Asettaa henkil�lle uudet yhteystiedot
     * @param uusiYhteystiedot   uudet yhteystiedot
     */
    public void setYhteystiedot(String uusiYhteystiedot) {
        this.yhteystiedot = uusiYhteystiedot;
    }
    

    /**
     * T�ytt�� henkil�n tiedot v�liaikaisilla tiedoilla
     */
    public void taytaValiaikaisetTiedot() {
        this.nimi =         "Testij�b� Testinen";
        this.ika =          20;
        this.sukupuoli =    "Mies";
        this.paikkakunta =  "Helsinki";
        this.vapaana =      "Viikonloppuisin";
        this.kokemus =      "2 vuotta";
        this.yhteystiedot = "0401234567";
    }
        
    
    /**
     * Tulostaa henkil�n tiedot tietovirtaan
     * @param os    mihin tietovirtaan tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Tulostaa henkil�n tiedot
     * @param out   mihin tietovirtaan tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("-------------------------------------------------------------------");
        out.println("id: "              + this.id);
        out.println("nimi: "            + this.nimi);
        out.println("ik�: "             + this.ika);
        out.println("sukupuoli: "       + this.sukupuoli);
        out.println("paikkakunta: "     + this.paikkakunta);
        out.println("vapaana: "         + this.vapaana);
        out.println("kokemus: "         + this.kokemus);
        out.println("yhteystiedot: "    + this.yhteystiedot);
    }
    

    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Henkilo testiHenkilo1 = new Henkilo();
        Henkilo testiHenkilo2 = new Henkilo();
        testiHenkilo1.tulosta(System.out);
        testiHenkilo2.tulosta(System.out);
        testiHenkilo1.rekisteroi();
        testiHenkilo2.rekisteroi();
        testiHenkilo1.tulosta(System.out);
        testiHenkilo2.tulosta(System.out);
        testiHenkilo1.taytaValiaikaisetTiedot();
        testiHenkilo2.taytaValiaikaisetTiedot();
        testiHenkilo1.tulosta(System.out);
        testiHenkilo2.tulosta(System.out);
    }
}
