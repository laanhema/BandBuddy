package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
@SuppressWarnings("unused")
public class HenkilonMuokkausController implements ModalControllerInterface<String>, Initializable {
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

    
    @FXML
    void henkilonMuokkausTallennaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonMuokkausTallenna);
    }
    
    
    @FXML
    void henkilonMuokkausPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonMuokkausPeruuta);
    }

    
    @Override
    public String getResult() {
        return null;
    }

    
    @Override
    public void handleShown() {
        //
    }

    
    @Override
    public void setDefault(String arg0) {
        //
    }


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }
    
    
}