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
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaGenre;
import bandbuddy.HenkiloJaInstrumentti;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 18.04.2018
 */
public class HenkilonNayttoController implements ModalControllerInterface<Henkilo>, Initializable {
    @FXML private Button henkilonNayttoTakaisin;
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
    void painettuHenkilonNayttoTakaisin(ActionEvent event) {
        ModalController.closeStage(henkilonNayttoTakaisin);
        event.consume();
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
        
        
        List<HenkiloJaGenre> hjgLista = bandbuddy.genret(kasiteltavaHenkilo.getId());
        List<String> genreLista = new ArrayList<String>();
        for (HenkiloJaGenre alkio : hjgLista) {    
            genreLista.add(bandbuddy.genre(alkio.getGenrenNro()));
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
     * Avaa henkilön näyttö -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              uusi henkilö jota käsitellään
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    public static Henkilo avaaHenkilonNaytto(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonNayttoController>showModal(HenkilonNayttoController.class.getResource("henkilonnaytto.fxml"), "Henkilön tiedot", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }


}