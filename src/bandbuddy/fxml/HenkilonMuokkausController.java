package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bandbuddy.BandBuddy;
import bandbuddy.Genre;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaGenre;
import bandbuddy.HenkiloJaInstrumentti;
import bandbuddy.Instrumentti;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 16.04.2018
 */
public class HenkilonMuokkausController implements ModalControllerInterface<Henkilo>, Initializable {
    @FXML private Button henkilonMuokkausTallenna;
    @FXML private Button henkilonMuokkausPeruuta;
    @FXML private TextField nimiKentta;
    @FXML private TextField ikaKentta;
    @FXML private TextField sukupuoliKentta;
    @FXML private TextField paikkakuntaKentta;
    @FXML private TextField instrumentitKentta;
    @FXML private TextField genretKentta;
    @FXML private TextField vapaanaKentta;
    @FXML private TextField kokemusKentta;
    @FXML private TextField yhteystiedotKentta;
    private Henkilo kasiteltavaHenkilo;
    private BandBuddy bandbuddy;

    
    @FXML
    void henkilonMuokkausTallennaPainettu(ActionEvent event) {
        this.kasiteltavaHenkilo.setNimi(this.nimiKentta.getText().trim());
        this.kasiteltavaHenkilo.setIka(Mjonot.erotaInt(this.ikaKentta.getText().trim(), 0));
        this.kasiteltavaHenkilo.setSukupuoli(this.sukupuoliKentta.getText().trim());
        this.kasiteltavaHenkilo.setPaikkakunta(this.paikkakuntaKentta.getText().trim());
        StringBuilder instrumentit = new StringBuilder(this.instrumentitKentta.getText().trim());
        luoInstrumentit(instrumentit);
        
        StringBuilder genret = new StringBuilder(this.genretKentta.getText().trim());
        luoGenret(genret);
  
        this.kasiteltavaHenkilo.setVapaana(this.vapaanaKentta.getText().trim());
        this.kasiteltavaHenkilo.setKokemus(this.kokemusKentta.getText().trim());
        this.kasiteltavaHenkilo.setYhteystiedot(this.yhteystiedotKentta.getText().trim());
                 
        ModalController.closeStage(henkilonMuokkausTallenna);
        event.consume();
    }
    
    
    /**
     * Luo merkkijonon perusteella henkilölle uudet instrumentit
     * @param instrumentit        käsiteltävä merkkijono
     */
    public void luoInstrumentit(StringBuilder instrumentit) {
        bandbuddy.poistaHenkilonInstrumentit(kasiteltavaHenkilo.getId());
        if (instrumentit.length() > 0) {
            while (instrumentit.length() > 0) {
                String uusiInstrumenttiString = Mjonot.erota(instrumentit, ',', instrumentit.toString()).trim();
                Instrumentti uusiInstrumentti = new Instrumentti();
                uusiInstrumentti = bandbuddy.loytyykoInstrumentti(uusiInstrumenttiString);
                if (uusiInstrumentti == null) { // ei löytynyt jos null
                uusiInstrumentti = new Instrumentti(uusiInstrumenttiString);
                uusiInstrumentti.rekisteroi();
                bandbuddy.lisaa(uusiInstrumentti);
                }
               
                // tarkistetaan oliko henkilöllä jo aikaisemmin tätä instrumenttia
                
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
     * Luo merkkijonon perusteella henkilölle uudet instrumentit
     * @param instrumentit        käsiteltävä merkkijono
     */
    public void luoGenret(StringBuilder genret) {
        bandbuddy.poistaHenkilonGenret(kasiteltavaHenkilo.getId());
        if (genret.length() > 0) {
            while (genret.length() > 0) {
                String uusiGenreString = Mjonot.erota(genret, ',', genret.toString()).trim();
                Genre uusiGenre = new Genre();
                uusiGenre = bandbuddy.loytyykoGenre(uusiGenreString);
                if (uusiGenre == null) { // ei löytynyt jos null
                uusiGenre = new Genre(uusiGenreString);
                uusiGenre.rekisteroi();
                bandbuddy.lisaa(uusiGenre);
                }
               
                // tarkistetaan oliko henkilöllä jo aikaisemmin tätä genreä
                
                List<HenkiloJaGenre> hjgLista = bandbuddy.hGenret(kasiteltavaHenkilo.getId());
                boolean loytyiko = false;
                for (HenkiloJaGenre alkio : hjgLista) {
                    if (bandbuddy.soitin(alkio.getGenrenNro()).equalsIgnoreCase(uusiGenreString)) {
                        loytyiko = true;
                    }
                }
                if (loytyiko == false) {
                    bandbuddy.lisaaHlogenre(kasiteltavaHenkilo, uusiGenre);
                }
            }
        }
    }
    
    
    
    @FXML
    void henkilonMuokkausPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonMuokkausPeruuta);
        event.consume();
    }

    
    @Override
    public Henkilo getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        this.nimiKentta.setText(kasiteltavaHenkilo.getNimi());        
        this.ikaKentta.setText("" + kasiteltavaHenkilo.getIka());        
        this.sukupuoliKentta.setText(kasiteltavaHenkilo.getSukupuoli());   
        this.paikkakuntaKentta.setText(kasiteltavaHenkilo.getPaikkakunta()); 
        List<HenkiloJaInstrumentti> hjiLista = bandbuddy.soittimet(kasiteltavaHenkilo.getId());
        List<String> instrumenttiLista = new ArrayList<String>();
        for (HenkiloJaInstrumentti alkio : hjiLista) {    
            instrumenttiLista.add(bandbuddy.soitin(alkio.getInstrumentinNro()));
        }
        StringBuilder instrumentitSB = new StringBuilder(instrumenttiLista.toString());
        instrumentitSB.deleteCharAt(0);
        instrumentitSB.deleteCharAt(instrumentitSB.length()-1);
        this.instrumentitKentta.setText(instrumentitSB.toString());
        
        List<HenkiloJaGenre> hjgLista = bandbuddy.hGenret(kasiteltavaHenkilo.getId());
        List<String> genreLista = new ArrayList<String>();
        for (HenkiloJaGenre alkio : hjgLista) {    
            genreLista.add(bandbuddy.hGenre(alkio.getGenrenNro()));
        }
        StringBuilder genretSB = new StringBuilder(genreLista.toString());
        genretSB.deleteCharAt(0);
        genretSB.deleteCharAt(genretSB.length()-1);
        this.genretKentta.setText(genretSB.toString());  
        
        this.vapaanaKentta.setText(kasiteltavaHenkilo.getVapaana());     
        this.kokemusKentta.setText(kasiteltavaHenkilo.getKokemus());     
        this.yhteystiedotKentta.setText(kasiteltavaHenkilo.getYhteystiedot());
    }

    
    @Override
    public void setDefault(Henkilo henkilo) {
        this.kasiteltavaHenkilo = henkilo; 
    }
    
    
    private Object setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
        return null;
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }
    
    
    /**
     * Avaa henkilön muokkaus -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              uusi henkilö jota käsitellään
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    public static Henkilo avaaHenkilonMuokkaus(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonMuokkausController>showModal(HenkilonMuokkausController.class.getResource("henkilonmuokkaus.fxml"), "Tietojen muokkaus", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }


}