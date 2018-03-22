package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
public class HenkilonLisaysController implements ModalControllerInterface<Henkilo> {
    private Henkilo kasiteltavaHenkilo;
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
    
    
    @FXML
    void henkilonLisaysLisaaJasenPainettu(ActionEvent event) {
        lisaaHenkilo();
        ModalController.closeStage(henkilonLisaysLisaaJasen);
    }
    
    @FXML
    void henkilonLisaysPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonLisaysPeruuta);
    }
    
    /**
     * Asettaa henkilön tiedot tekstikenttien mukaan ja rekisteröi henkilön
     */
    public void lisaaHenkilo() {
        kasiteltavaHenkilo.rekisteroi();
        kasiteltavaHenkilo.setNimi(nimiKentta.getText());
        kasiteltavaHenkilo.setIka(Mjonot.erotaInt(ikaKentta.getText(), 0));
        kasiteltavaHenkilo.setSukupuoli(sukupuoliKentta.getText());
        kasiteltavaHenkilo.setPaikkakunta(paikkakuntaKentta.getText());
        //kasiteltavaHenkilo.setInstrumentti(instrumentitKentta.getText());
        //kasiteltavaHenkilo.setGenret(genretKentta.getText());
        kasiteltavaHenkilo.setVapaana(vapaanaKentta.getText());
        kasiteltavaHenkilo.setKokemus(kokemusKentta.getText());
        kasiteltavaHenkilo.setYhteystiedot(yhteystiedotKentta.getText());
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
    public void setDefault(Henkilo uusi) {
        kasiteltavaHenkilo = uusi;
    }
    
    
    /**
     * @param modalityStage x
     * @param uusi x
     * @return x
     */
    public static Henkilo avaaLisaaHenkilo(Stage modalityStage, Henkilo uusi) {
        return ModalController.<Henkilo, HenkilonLisaysController>showModal(HenkilonLisaysController.class.getResource("henkilonlisays.fxml"), "Uuden henkilön tiedot", modalityStage, uusi, null);
    }
    
    
}