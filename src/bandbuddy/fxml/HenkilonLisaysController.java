package bandbuddy.fxml;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;

import bandbuddy.BandBuddy;
import bandbuddy.Genre;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaGenre;
import bandbuddy.HenkiloJaInstrumentti;
import bandbuddy.Instrumentti;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 18.04.2018
 */
public class HenkilonLisaysController implements ModalControllerInterface<Henkilo> {

    @FXML private TextField nimiKentta;
    @FXML private TextField ikaKentta;
    @FXML private TextField sukupuoliKentta;
    @FXML private TextField paikkakuntaKentta;
    @FXML private TextField instrumentitKentta;
    @FXML private TextField genretKentta;
    @FXML private TextField vapaanaKentta;
    @FXML private TextField kokemusKentta;
    @FXML private TextField yhteystiedotKentta;
    @FXML private Button henkilonLisaysLisaaJasen;
    @FXML private Button henkilonLisaysPeruuta;

    private Henkilo kasiteltavaHenkilo;
    private BandBuddy bandbuddy;
    
    
    @FXML void henkilonLisaysLisaaJasenPainettu(ActionEvent event) {
        lisaaHenkilo();
        ModalController.closeStage(henkilonLisaysLisaaJasen);
        event.consume();
    }
    

    @FXML void henkilonLisaysPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonLisaysPeruuta);
        event.consume();
    }
    
    
    @FXML void kirjoitettuNimiKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.nimiKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuIkaKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.ikaKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuSukupuoliKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.sukupuoliKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuPaikkakuntaKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.paikkakuntaKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuInstrumentitKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.instrumentitKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML
    void kirjoitettuGenretKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.genretKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuVapaanaKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.vapaanaKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuKokemusKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.kokemusKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }


    @FXML void kirjoitettuYhteystiedotKentta(KeyEvent event) {
        String virhe = null;
        TextField kentta = (TextField) event.getSource();
        virhe = bandbuddy.yhteystiedotKenttaTarkistus(kentta.getText());
        
        if ( virhe != null) {
            kentta.getStyleClass().clear();
            kentta.getStyleClass().add("error");
            Dialogs.setToolTipText(kentta, virhe);
            onkoKentatTaytettyOikein();
            event.consume();
            return;
        }
        kentta.getStyleClass().clear();
        kentta.getStyleClass().add("normal");
        onkoKentatTaytettyOikein();
        event.consume();
    }
      
    
    /**
     * Asettaa henkil�n tiedot tekstikenttien mukaan ja rekister�i henkil�n
     */
    public void lisaaHenkilo() {
        this.kasiteltavaHenkilo.rekisteroi();
        this.kasiteltavaHenkilo.setNimi(this.nimiKentta.getText().trim());
        this.kasiteltavaHenkilo.setIka(Mjonot.erotaInt(this.ikaKentta.getText(), 0));
        this.kasiteltavaHenkilo.setSukupuoli(this.sukupuoliKentta.getText().trim());
        this.kasiteltavaHenkilo.setPaikkakunta(this.paikkakuntaKentta.getText().trim());
        StringBuilder instrumentit = new StringBuilder(this.instrumentitKentta.getText().trim());
        luoInstrumentit(instrumentit);
        StringBuilder genret = new StringBuilder(this.genretKentta.getText().trim());
        luoGenret(genret);
        this.kasiteltavaHenkilo.setVapaana(vapaanaKentta.getText());
        this.kasiteltavaHenkilo.setKokemus(kokemusKentta.getText());
        this.kasiteltavaHenkilo.setYhteystiedot(yhteystiedotKentta.getText());
    }

    
    /**
     * Luo merkkijonon perusteella henkil�lle uudet instrumentit
     * @param instrumentit        k�sitelt�v� merkkijono
     */
    public void luoInstrumentit(StringBuilder instrumentit) {
        bandbuddy.poistaHenkilonInstrumentit(kasiteltavaHenkilo.getId());
        if (instrumentit.length() > 0) {
            while (instrumentit.length() > 0) {
                String uusiInstrumenttiString = Mjonot.erota(instrumentit, ',', instrumentit.toString()).trim();
                Instrumentti uusiInstrumentti = new Instrumentti();
                uusiInstrumentti = bandbuddy.loytyykoInstrumentti(uusiInstrumenttiString);
                if (uusiInstrumentti == null) { // ei l�ytynyt jos null
                uusiInstrumentti = new Instrumentti(uusiInstrumenttiString);
                uusiInstrumentti.rekisteroi();
                bandbuddy.lisaa(uusiInstrumentti);
                }
               
                // tarkistetaan oliko henkil�ll� jo aikaisemmin t�t� instrumenttia
                
                List<HenkiloJaInstrumentti> hjiLista = bandbuddy.soittimet(kasiteltavaHenkilo.getId());
                boolean loytyiko = false;
                for (HenkiloJaInstrumentti alkio : hjiLista) {
                    if (bandbuddy.soitin(alkio.getInstrumentinNro()).equalsIgnoreCase(uusiInstrumenttiString)) {
                        loytyiko = true;
                    }
                }
                if (loytyiko == false) {
                    bandbuddy.lisaaHloInstrumentti(kasiteltavaHenkilo, uusiInstrumentti);
                }
            }
        }
    }
    
    
    /**
     * Luo merkkijonon perusteella henkil�lle uudet genret
     * @param genret        k�sitelt�v� merkkijono
     */
    public void luoGenret(StringBuilder genret) {
        bandbuddy.poistaHenkilonGenret(kasiteltavaHenkilo.getId());
        if (genret.length() > 0) {
            while (genret.length() > 0) {
                String uusiGenreString = Mjonot.erota(genret, ',', genret.toString()).trim();
                Genre uusiGenre= new Genre();
                uusiGenre = bandbuddy.loytyykoGenre(uusiGenreString);
                if (uusiGenre == null) { // ei l�ytynyt jos null
                uusiGenre = new Genre(uusiGenreString);
                uusiGenre.rekisteroi();
                bandbuddy.lisaa(uusiGenre);
                }
               
                // tarkistetaan oliko henkil�ll� jo aikaisemmin t�t� genre�
                
                List<HenkiloJaGenre> hjgLista = bandbuddy.genret(kasiteltavaHenkilo.getId());
                boolean loytyiko = false;
                for (HenkiloJaGenre alkio : hjgLista) {
                    if (bandbuddy.genre(alkio.getGenrenNro()).equalsIgnoreCase(uusiGenreString)) {
                        loytyiko = true;
                    }
                }
                if (loytyiko == false) {
                    bandbuddy.lisaaHloGenre(kasiteltavaHenkilo, uusiGenre);
                }
            }
        }
    }
    
    
    @Override
    public Henkilo getResult() {
        return null;
    }


    @Override
    public void handleShown() {
        this.nimiKentta.getStyleClass().clear();
        this.nimiKentta.getStyleClass().add("normal");
        
        this.ikaKentta.getStyleClass().clear();
        this.ikaKentta.getStyleClass().add("normal");
        
        this.sukupuoliKentta.getStyleClass().clear();
        this.sukupuoliKentta.getStyleClass().add("normal");
        
        this.paikkakuntaKentta.getStyleClass().clear();
        this.paikkakuntaKentta.getStyleClass().add("normal");
        
        this.instrumentitKentta.getStyleClass().clear();
        this.instrumentitKentta.getStyleClass().add("normal");
        
        this.genretKentta.getStyleClass().clear();
        this.genretKentta.getStyleClass().add("normal");
        
        this.vapaanaKentta.getStyleClass().clear();
        this.vapaanaKentta.getStyleClass().add("normal");
        
        this.kokemusKentta.getStyleClass().clear();
        this.kokemusKentta.getStyleClass().add("normal");
        
        this.yhteystiedotKentta.getStyleClass().clear();
        this.yhteystiedotKentta.getStyleClass().add("normal");
        
        this.henkilonLisaysLisaaJasen.setDisable(true);
    }
    

    @Override
    public void setDefault(Henkilo uusiHenkilo) {
        this.kasiteltavaHenkilo = uusiHenkilo;
    }
   
    
    /**
     * Avaa henkil�n lis�ys -ikkunan
     * @param modalityStage     mille stagelle ollaan modaalisia
     * @param uusiHenkilo       uusi henkil� jota k�sitell��n
     * @param bandbuddy         bandbuddy-luokka
     * @return                  modalcontrolleri
     */
    
    public static Henkilo avaaLisaaHenkilo(Stage modalityStage, Henkilo uusiHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonLisaysController>showModal(HenkilonLisaysController.class.getResource("henkilonlisays.fxml"), "Uuden henkil�n tiedot", modalityStage, uusiHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }

    
    private Object setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
        return null;
    }

    
    /**
     * Tarkistaa onko kaikki kent�t t�ytetty oikein, varmistaa ett� kaikkiin pakollisiin kenttiin on laitettu 
     * jotain ja laittaa "lis��"-painikkeen painettavaksi-tilaan jos edell� mainitut kriteerit t�yttyv�t
     * 
     */
    public void onkoKentatTaytettyOikein() {
        // katsotaan onko tekstikentiss� virheit� tarkistamalla niiden tyylit
        List<String> normal = FXCollections.observableArrayList();
        normal.add("normal");
        if ( this.nimiKentta.getStyleClass().equals(normal)         && 
             this.ikaKentta.getStyleClass().equals(normal)          && 
             this.sukupuoliKentta.getStyleClass().equals(normal)    && 
             this.paikkakuntaKentta.getStyleClass().equals(normal)  && 
             this.instrumentitKentta.getStyleClass().equals(normal) && 
             this.genretKentta.getStyleClass().equals(normal)       && 
             this.vapaanaKentta.getStyleClass().equals(normal)      && 
             this.kokemusKentta.getStyleClass().equals(normal)      && 
             this.yhteystiedotKentta.getStyleClass().equals(normal) && 
             this.nimiKentta.getText().length() > 0                 &&
             this.instrumentitKentta.getText().length() > 0         &&
             this.yhteystiedotKentta.getText().length() > 0 
            ) {
            this.henkilonLisaysLisaaJasen.setDisable(false);
            return;
        } 
        this.henkilonLisaysLisaaJasen.setDisable(true);
        return;
    }
    
    
}