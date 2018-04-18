package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 16.04.2018
 */
public class HenkilonPoistoController implements ModalControllerInterface<Henkilo>, Initializable {
    
    private Henkilo kasiteltavaHenkilo;
    private BandBuddy bandbuddy;
    
    @FXML private Button poistaKylla;
    @FXML private Button poistaEi;



    @FXML void painettuPoistaEi(ActionEvent event) {
        ModalController.closeStage(poistaEi);
        event.consume();
    }
    

    @FXML void painettuPoistaKylla(ActionEvent event) {
        bandbuddy.poistaHenkilonInstrumentit(this.kasiteltavaHenkilo.getId());
        bandbuddy.poistaHenkilonGenret(this.kasiteltavaHenkilo.getId());
        bandbuddy.poistaHenkilo(this.kasiteltavaHenkilo.getId());
        ModalController.closeStage(poistaKylla);
        event.consume();
    }
 
    @Override
    public Henkilo getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        //
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
     * Avaa henkilön poisto -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              uusi henkilö jota käsitellään
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    public static Henkilo avaaHenkilonPoisto(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonPoistoController>showModal(HenkilonPoistoController.class.getResource("henkilonpoisto.fxml"), "Huomautus", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }


}