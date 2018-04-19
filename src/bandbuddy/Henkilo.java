package bandbuddy;

import java.io.OutputStream;
import java.io.PrintStream;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Henkil�-luokka
 * Yksitt�inen henkil�
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class Henkilo {
    
    private int         id              = 0;
    private String      nimi            = "";
    private int         ika             = 0;
    private String      sukupuoli       = "";
    private String      paikkakunta     = "";
    private String      vapaana         = "";
    private String      kokemus         = "";
    private String      yhteystiedot    = "";
                        
    private static int  seuraavaId      = 1;


    /**
     * Parametriton konstruktori Henkil�lle
     */
    public Henkilo() {
    }


    /**
     * Rekister�i henkil�n eli antaa henkil�lle id:n
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getId() === 0;
     * testiHen1.rekisteroi();
     * testiHen1.getId() === 1;
     * testiHen1.rekisteroi();
     * testiHen1.getId() === 1;
     * </pre>
     */
    public void rekisteroi() {
        if (this.id > 0)
            return;
        this.id = seuraavaId++;
    }
    
    
    /**
     * Laittaa henkil�n id:ksi annetun numeron
     * @param numero        laitettava numero
     */
    public void rekisteroi(int numero) {
        if (this.id > 0)
            return;
        this.id = numero;
        seuraavaId++;
    }


    /**
     * T�ytt�� henkil�n tiedot v�liaikaisilla tiedoilla
     * @example
     * <pre name="test">
     * #import fi.jyu.mit.ohj2.Suuntaaja.*;
     * StringOutput so = new StringOutput();
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.taytaValiaikaisetTiedot();
     * testiHen1.tulosta(System.out);
     * String tulos = 
     *      "-------------------------------------------------------------------\n" + 
     *      "id: 0\n" + 
     *      "nimi: Testij�b� Testinen\n" + 
     *      "ik�: 20\n" +
     *      "sukupuoli: Mies\n" +
     *      "paikkakunta: Helsinki\n" +
     *      "vapaana: Viikonloppuisin\n" +
     *      "kokemus: 2 vuotta\n" +
     *      "yhteystiedot: 0401234567\n";
     * so.ero(tulos) === null;
     * </pre>
     * 
     */
    public void taytaValiaikaisetTiedot() {
        this.nimi = "Testij�b� Testinen";
        this.ika = 20;
        this.sukupuoli = "Mies";
        this.paikkakunta = "Helsinki";
        this.vapaana = "Viikonloppuisin";
        this.kokemus = "2 vuotta";
        this.yhteystiedot = "0401234567";
    }


    /**
     * Palauttaa henkil�n id:n
     * @return      henkil�n id
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getId() === 0;
     * </pre>
     */
    public int getId() {
        return this.id;
    }


    /**
     * Palauttaa henkil�n nimen
     * @return      henkil�n nimi
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getNimi() === "";
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }


    /**
     * Asettaa henkil�lle uuden nimen
     * @param uusiNimi      uusi nimi
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setNimi("Aapo");
     * testiHen1.getNimi() === "Aapo";
     * </pre>
     */
    public void setNimi(String uusiNimi) {
        this.nimi = uusiNimi;
    }


    /**
     * Palauttaa henkil�n i�n
     * @return      henkil�n ik�
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getIka() === 0;
     * </pre>
     */
    public int getIka() {
        return this.ika;
    }


    /**
     * Asettaa henkil�lle uuden i�n
     * @param uusiIka      uusi ik�
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setIka(25);
     * testiHen1.getIka() === 25;
     * </pre>
     */
    public void setIka(int uusiIka) {
        this.ika = uusiIka;
    }


    /**
     * Palauttaa henkil�n sukupuolen
     * @return      henkil�n sukupuoli
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getSukupuoli() === "";
     * </pre>
     */
    public String getSukupuoli() {
        return this.sukupuoli;
    }


    /**
     * Asettaa henkil�lle uuden sukupuolen
     * @param uusiSukupuoli     uusi sukupuoli
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setSukupuoli("Mies");
     * testiHen1.getSukupuoli() === "Mies";
     * </pre>
     */
    public void setSukupuoli(String uusiSukupuoli) {
        this.sukupuoli = uusiSukupuoli;
    }


    /**
     * Palauttaa mill� paikkakunnalla henkil� asuu
     * @return      henkil�n paikkakunta
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getPaikkakunta() === "";
     * </pre>
     */
    public String getPaikkakunta() {
        return this.paikkakunta;
    }


    /**
     * Asettaa henkil�lle uuden paikkakunnan
     * @param uusiPaikkakunta      uusi paikkakunta
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setPaikkakunta("Pieks�m�ki");
     * testiHen1.getPaikkakunta() === "Pieks�m�ki";
     */
    public void setPaikkakunta(String uusiPaikkakunta) {
        this.paikkakunta = uusiPaikkakunta;
    }


    /**
     * Palauttaa milloin henkil� on vapaana
     * @return      ajankohta
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getVapaana() === "";
     */
    public String getVapaana() {
        return this.vapaana;
    }


    /**
     * Asettaa henkil�lle uuden ajankohdan milloin h�n on vapaana
     * @param uusiVapaana      uusi ajankohta
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setVapaana("Nyt");
     * testiHen1.getVapaana() === "Nyt";
     */
    public void setVapaana(String uusiVapaana) {
        this.vapaana = uusiVapaana;
    }


    /**
     * Palauttaa henkil�n kokemuksen m��r�n
     * @return      kokemuksen m��r�
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getKokemus() === "";
     */
    public String getKokemus() {
        return this.kokemus;
    }


    /**
     * Asettaa henkil�lle uuden kokemus-kent�n
     * @param uusiKokemus   uusi kokemus-kentt�
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setKokemus("Puoli vuotta");
     * testiHen1.getKokemus() === "Puoli vuotta";
     */
    public void setKokemus(String uusiKokemus) {
        this.kokemus = uusiKokemus;
    }


    /**
     * Palauttaa henkil�n yhteystiedot
     * @return      henkil�n yhteystiedot
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.getYhteystiedot() === "";
     */
    public String getYhteystiedot() {
        return this.yhteystiedot;
    }


    /**
     * Asettaa henkil�lle uudet tiedot "yhteystiedot"-kentt��n
     * @param uusiYhteystiedot   uudet yhteystiedot
     * @example
     * <pre name="test">
     * Henkilo testiHen1 = new Henkilo();
     * testiHen1.setYhteystiedot("040XXXXXXX");
     * testiHen1.getYhteystiedot() === "040XXXXXXX";
     */
    public void setYhteystiedot(String uusiYhteystiedot) {
        this.yhteystiedot = uusiYhteystiedot;
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
        out.println(
                "-------------------------------------------------------------------");
        out.println("id: " +            this.id);
        out.println("nimi: " +          this.nimi);
        out.println("ik�: " +           this.ika);
        out.println("sukupuoli: " +     this.sukupuoli);
        out.println("paikkakunta: " +   this.paikkakunta);
        out.println("vapaana: " +       this.vapaana);
        out.println("kokemus: " +       this.kokemus);
        out.println("yhteystiedot: " +  this.yhteystiedot);
    }

    
    /**
     * Muuttaa henkil�n tiedot merkkijonoksi tiedostoon kirjoittamista varten
     */
    @Override
    public String toString() {
        return "" + this.id + "|" + this.nimi + "|" + this.ika + "|" + this.sukupuoli + "|" + this.paikkakunta + "|" + this.vapaana + "|" + this.kokemus + "|" + this.yhteystiedot + "|";
    }
    
    
    /**
     * Poimii merkkijonosta henkil�n tiedot ja laittaa ne sen attribuutteihin
     * @param merkkijono k�sitelt�v� merkkijono
     * @example
     * <pre name="test">
     * Henkilo testiHenkilo1 = new Henkilo();
     * testiHenkilo1.parse("1|Roope Ankka|50|Mies|Ankkalinna|Iltaisin|10v|0401234567|");
     * testiHenkilo1.toString() === "1|Roope Ankka|50|Mies|Ankkalinna|Iltaisin|10v|0401234567|";
     * Henkilo testiHenkilo2 = new Henkilo();
     * testiHenkilo2.parse("");
     * testiHenkilo2.toString() === "-1||-1||||||";
     * </pre>
     */
    public void parse(String merkkijono) {
        StringBuilder tiedostonRiviSB = new StringBuilder(merkkijono);
        StringBuilder idSB = new StringBuilder(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.rekisteroi(Mjonot.erotaInt(idSB, -1));
        
        this.setNimi(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        
        StringBuilder ikaSB = new StringBuilder(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.setIka(Mjonot.erotaInt(ikaSB, -1));
        
        this.setSukupuoli(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.setPaikkakunta(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.setVapaana(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.setKokemus(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
        this.setYhteystiedot(Mjonot.erota(tiedostonRiviSB, '|', false).trim());
    }
}