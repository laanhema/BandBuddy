package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
@SuppressWarnings("unused")
public class HenkilonLisaysController implements ModalControllerInterface<String> {
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
     * Kirjoittaa kirjoitettu
     */
    public void lisaaHenkilo() {
        Henkilo henkilo = new Henkilo();
        henkilo.rekisteroi();
        
        henkilo.setNimi(nimiKentta.getText());
        henkilo.setIka(Mjonot.erotaInt(ikaKentta.getText(), 0));
        henkilo.setSukupuoli(sukupuoliKentta.getText());
        henkilo.setPaikkakunta(paikkakuntaKentta.getText());
        // henkilo.setInstrumentti(instrumenttiKentta.getText());
        // henkilo.setGenret(genretKentta.getText());
        henkilo.setVapaana(vapaanaKentta.getText());
        henkilo.setKokemus(kokemusKentta.getText());
        henkilo.setYhteystiedot(yhteystiedotKentta.getText());
        henkilo.tulosta(System.out);
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
        // TODO Auto-generated method stub
        
    }
    
    
}