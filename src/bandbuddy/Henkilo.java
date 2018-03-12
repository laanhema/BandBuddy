/**
 * 
 */
package bandbuddy;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Henkilö-luokka
 * @author Markus Mäntymaa & Lauri Makkonen
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
     * Parametriton konstruktori Henkilölle
     */
    public Henkilo() {
        // tämä on tulevaisuuden varalle jos lisätään uusi konstruktoreita
    }
        
        
    /**
     * Rekisteröi henkilön eli antaa henkilölle id:n
     */
    public void rekisteroi() {
        this.id = seuraavaId++;
    }
    
    
    /**
     * Palauttaa henkilön id:n
     * @return      henkilön id
     */
    public int getId() {
        return this.id;
    }
    
    
    /**
     * Palauttaa henkilön nimen
     * @return      henkilön nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Asettaa henkilölle uuden nimen
     * @param uusiNimi      uusi nimi
     */
    public void setNimi(String uusiNimi) {
        this.nimi = uusiNimi;
    }
    
    
    /**
     * Palauttaa henkilön iän
     * @return      henkilön ikä
     */
    public int getIka() {
        return this.ika;
    }
    
    
    /**
     * Asettaa henkilölle uuden iän
     * @param uusiIka      uusi ikä
     */
    public void setIka(int uusiIka) {
        this.ika = uusiIka;
    }
    
    
    /**
     * Palauttaa henkilön sukupuolen
     * @return      henkilön sukupuoli
     */
    public String getSukupuoli() {
        return this.sukupuoli;
    }
    
    
    /**
     * Asettaa henkilölle uuden sukupuolen
     * @param uusiSukupuoli     uusi sukupuoli
     */
    public void setSukupuoli(String uusiSukupuoli) {
        this.sukupuoli = uusiSukupuoli;
    }
    
    
    /**
     * Palauttaa millä paikkakunnalla henkilö asuu
     * @return      henkilön paikkakunta
     */
    public String getPaikkakunta() {
        return this.paikkakunta;
    }
    
    
    /**
     * Asettaa henkilölle uuden paikkakunnan
     * @param uusiPaikkakunta      uusi paikkakunta
     */
    public void setPaikkakunta(String uusiPaikkakunta) {
        this.paikkakunta = uusiPaikkakunta;
    }
    
    
    /**
     * Palauttaa milloin henkilö on vapaana
     * @return      ajankohta
     */
    public String getVapaana() {
        return this.vapaana;
    }
    
    
    /**
     * Asettaa henkilölle uuden ajankohdan milloin hän on vapaana
     * @param uusiVapaana      uusi ajankohta
     */
    public void setVapaana(String uusiVapaana) {
        this.vapaana = uusiVapaana;
    }
    
    
    /**
     * Palauttaa henkilön kokemuksen määrän
     * @return      kokemuksen määrä
     */
    public String getKokemus() {
        return this.kokemus;
    }
    
    
    /**
     * Asettaa henkilölle uuden kokemus-kentän
     * @param uusiKokemus   uusi kokemus-kenttä
     */
    public void setKokemus(String uusiKokemus) {
        this.kokemus = uusiKokemus;
    }
    
    
    /**
     * Palauttaa henkilön yhteystiedot
     * @return      henkilön yhteystiedot
     */
    public String getYhteystiedot() {
        return this.yhteystiedot;
    }
    
    
    /**
     * Asettaa henkilölle uudet yhteystiedot
     * @param uusiYhteystiedot   uudet yhteystiedot
     */
    public void setYhteystiedot(String uusiYhteystiedot) {
        this.yhteystiedot = uusiYhteystiedot;
    }
    

    /**
     * Täyttää henkilön tiedot väliaikaisilla tiedoilla
     */
    public void taytaValiaikaisetTiedot() {
        this.nimi =         "Testijäbä Testinen";
        this.ika =          20;
        this.sukupuoli =    "Mies";
        this.paikkakunta =  "Helsinki";
        this.vapaana =      "Viikonloppuisin";
        this.kokemus =      "2 vuotta";
        this.yhteystiedot = "0401234567";
    }
        
    
    /**
     * Tulostaa henkilön tiedot tietovirtaan
     * @param os    mihin tietovirtaan tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Tulostaa henkilön tiedot
     * @param out   mihin tietovirtaan tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("-------------------------------------------------------------------");
        out.println("id: "              + this.id);
        out.println("nimi: "            + this.nimi);
        out.println("ikä: "             + this.ika);
        out.println("sukupuoli: "       + this.sukupuoli);
        out.println("paikkakunta: "     + this.paikkakunta);
        out.println("vapaana: "         + this.vapaana);
        out.println("kokemus: "         + this.kokemus);
        out.println("yhteystiedot: "    + this.yhteystiedot);
    }
    

    /**
     * @param args ei käytössä
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
