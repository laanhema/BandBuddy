package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import bandbuddy.Instrumentti;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class HenkilonMuokkausController implements ModalControllerInterface<BandBuddy>, Initializable {
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
    private BandBuddy bandbuddy;

    
    @FXML
    void henkilonMuokkausTallennaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonMuokkausTallenna);
        event.consume();
    }
    
    
    @FXML
    void henkilonMuokkausPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonMuokkausPeruuta);
        event.consume();
    }

    
    @Override
    public BandBuddy getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        //
    }

    
    @Override
    public void setDefault(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }
    
    
    /**
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              uusi henkilö jota käsitellään
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    public static Henkilo avaaHenkilonMuokkaus(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonLisaysController>showModal(HenkilonLisaysController.class.getResource("henkilonlisays.fxml"), "Uuden henkilön tiedot", modalityStage, valittuHenkilo, null);
    }
    
    
}