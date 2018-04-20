package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaGenre;
import bandbuddy.HenkiloJaInstrumentti;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * Kontrolleri "henkil�n n�ytt�" -ikkunalle
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class HenkilonNayttoController implements ModalControllerInterface<Henkilo> {

    @FXML private TextField nimiKentta;
    @FXML private TextField ikaKentta;
    @FXML private TextField sukupuoliKentta;
    @FXML private TextField paikkakuntaKentta;
    @FXML private TextField instrumentitKentta;
    @FXML private TextField genretKentta;
    @FXML private TextField vapaanaKentta;
    @FXML private TextField kokemusKentta;
    @FXML private TextField yhteystiedotKentta;
    @FXML private Button    henkilonNayttoTakaisin;
    
    private Henkilo         kasiteltavaHenkilo;
    private BandBuddy       bandbuddy;
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan "takaisin"
     * Sulkee ikkunan
     * @param event
     */
    @FXML private void painettuHenkilonNayttoTakaisin(ActionEvent event) {
        ModalController.closeStage(henkilonNayttoTakaisin);
        event.consume();
    }

    
    @Override
    public Henkilo getResult() {
        return null;
    }

    
    /**
     * Aliohjelma johon menn��n kun kontrolleria aletaan k�ytt�m��n
     * Laittaa tekstikenttien tyylit oikein ja t�ytt�� kent�t henkil�n tiedoilla
     */
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

    
    /**
     * Laittaa avaaLis��Henkil�st� tuodun henkil�n t�m�n luokan k�siteltavaHenkilo-attribuuttiin
     */
    @Override
    public void setDefault(Henkilo henkilo) {
        this.kasiteltavaHenkilo = henkilo; 
    }
    
    
    /**
     * Liitt�� bandbuddy-luokan kontrolleriin
     * @param bandbuddy     luokka mik� halutaan liitt��
     */
    private Object setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
        return null;
    }

    
    /**
     * Avaa henkil�n n�ytt� -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              henkil� jota k�sitell��n
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    static Henkilo avaaHenkilonNaytto(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonNayttoController>showModal(HenkilonNayttoController.class.getResource("henkilonnaytto.fxml"), "Henkil�n tiedot", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }
}