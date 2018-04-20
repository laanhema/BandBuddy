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
 * Kontrolleri "henkil�n poisto" -ikkunalle
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class HenkilonPoistoController implements ModalControllerInterface<Henkilo> {
    
    @FXML private Button poistaKylla;
    @FXML private Button poistaEi;
    
    private Henkilo     kasiteltavaHenkilo;
    private BandBuddy   bandbuddy;


    /**
     * Aliohjelma johon menn��n kun painetaan "ei"
     * Ei tehd� mit��n, suljetaan ikkuna
     * @param event             tapahtuma
     */
    @FXML private void painettuPoistaEi(ActionEvent event) {
        ModalController.closeStage(poistaEi);
        event.consume();
    }
    

    /**
     * Aliohjelma, johon menn��n kun painetaan "kyll�"
     * Poistetaan henkil�n instrumentit, genret ja itse henkil�
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
     * Aliohjelma johon menn��n kun kontrolleria aletaan k�ytt�m��n
     * Ei tee t�ll� hetkell� mit��n
     */
    @Override
    public void handleShown() {
        //
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
     * Avaa henkil�n poisto -ikkunan
     * @param modalityStage               mille stagelle ollaan modaalisia
     * @param valittuHenkilo              henkil� jota k�sitell��n
     * @param bandbuddy                   bandbuddy
     * @return                            modalcontrolleri
     */
    static Henkilo avaaHenkilonPoisto(Stage modalityStage, Henkilo valittuHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonPoistoController>showModal(HenkilonPoistoController.class.getResource("henkilonpoisto.fxml"), "Huomautus", modalityStage, valittuHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }
}