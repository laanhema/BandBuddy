package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * Kontrolleri "henkilön poisto" -ikkunalle
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class HenkilonPoistoController implements ModalControllerInterface<Henkilo> {
    
    @FXML private Button poistaKylla;
    @FXML private Button poistaEi;
    
    private Henkilo     kasiteltavaHenkilo;
    private BandBuddy   bandbuddy;


    /**
     * Aliohjelma johon mennään kun painetaan "ei"
     * Ei tehdä mitään, suljetaan ikkuna
     * @param event             tapahtuma
     */
    @FXML private void painettuPoistaEi(ActionEvent event) {
        ModalController.closeStage(poistaEi);
        event.consume();
    }
    

    /**
     * Aliohjelma, johon mennään kun painetaan "kyllä"
     * Poistetaan henkilön instrumentit, genret ja itse henkilö
     * Suljetaan ikkuna
     * @param event             tapahtuma
     */
    @FXML private void painettuPoistaKylla(ActionEvent event) {
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

    
    /**
     * Aliohjelma johon mennään kun kontrolleria aletaan käyttämään
     * Ei tee tällä hetkellä mitään
     */
    @Override
    public void handleShown() {
        //
    }

    
    /**
     * Laittaa avaaLisääHenkilöstä tuodun henkilön tämän luokan käsiteltavaHenkilo-attribuuttiin
     */
    @Override
    public void setDefault(Henkilo henkilo) {
        this.kasiteltavaHenkilo = henkilo; 
    }
    
    
    /**
     * Liittää bandbuddy-luokan kontrolleriin
     * @param bandbuddy     luokka mikä halutaan liittää
     */
    private Object setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
        return null;
    }

    
    /**
     * Avaa henkilön poisto -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              henkilö jota käsitellään
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    static Henkilo avaaHenkilonPoisto(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonPoistoController>showModal(HenkilonPoistoController.class.getResource("henkilonpoisto.fxml"), "Huomautus", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }
}