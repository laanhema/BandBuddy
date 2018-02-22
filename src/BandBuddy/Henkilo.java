/**
 * 
 */
package BandBuddy;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.2.2018
 *
 */
public class Henkilo {
    private int id;
    private int ika;
    private String sukupuoli;
    private String vapaana;
    private String kokemus;
    private String yhteystiedot;
    //soittonäyte tähän?
    
    private SimpleStringProperty nimi = new SimpleStringProperty("");
    private SimpleStringProperty genre = new SimpleStringProperty("");
    private SimpleStringProperty instrumentti = new SimpleStringProperty("");
    private SimpleStringProperty kaupunki = new SimpleStringProperty("");
    
    public Henkilo() {
        this("", "", "", "");
    }
    
    
    /**
     * @param nimi x
     * @param genre x
     * @param instrumentti x
     * @param kaupunki x
     * 
     */
    public Henkilo(String nimi, String genre, String instrumentti, String kaupunki) {
        setNimi(nimi);
        setGenre(genre);
        setInstrumentti(instrumentti);
        setKaupunki(kaupunki);

    }
    
    
    public String getNimi() {
        return nimi.get();
    }
    
    
    public String getGenre() {
        return genre.get();
    }
    
    
    public String getInstrumentti() {
        return instrumentti.get();
    }
    
    public String getKaupunki() {
        return kaupunki.get();
    }
    
    public void setNimi(String pNimi) {
        nimi.set(pNimi);
    }
    
    
    public void setGenre(String pGenre) {
        genre.set(pGenre);
    }
    
    
    public void setInstrumentti(String pInstrumentti) {
        instrumentti.set(pInstrumentti);
    }
    
    public void setKaupunki(String pKaupunki) {
        kaupunki.set(pKaupunki);
    }


    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }

}
