package bandbuddy.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaInstrumentti;
import bandbuddy.Instrumentti;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 16.04.2018
 */
public class HenkilonLisaysController implements ModalControllerInterface<Henkilo> {

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

    private Henkilo kasiteltavaHenkilo;
    private BandBuddy bandbuddy;
    
    
    @FXML void henkilonLisaysLisaaJasenPainettu(ActionEvent event) {
        lisaaHenkilo();
        ModalController.closeStage(henkilonLisaysLisaaJasen);
        event.consume();
    }
    
    
    @FXML void henkilonLisaysPeruutaPainettu(ActionEvent event) {
        ModalController.closeStage(henkilonLisaysPeruuta);
        event.consume();
    }
    
    
    /**
     * Asettaa henkilön tiedot tekstikenttien mukaan ja rekisteröi henkilön
     */
    public void lisaaHenkilo() {
        this.kasiteltavaHenkilo.rekisteroi();
        this.kasiteltavaHenkilo.setNimi(this.nimiKentta.getText().trim());
        this.kasiteltavaHenkilo.setIka(Mjonot.erotaInt(this.ikaKentta.getText(), 0));
        this.kasiteltavaHenkilo.setSukupuoli(this.sukupuoliKentta.getText().trim());
        this.kasiteltavaHenkilo.setPaikkakunta(this.paikkakuntaKentta.getText().trim());
        StringBuilder instrumentit = new StringBuilder(this.instrumentitKentta.getText().trim());
        luoInstrumentit(instrumentit);
        // genret
        this.kasiteltavaHenkilo.setVapaana(vapaanaKentta.getText());
        this.kasiteltavaHenkilo.setKokemus(kokemusKentta.getText());
        this.kasiteltavaHenkilo.setYhteystiedot(yhteystiedotKentta.getText());
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
    
    
    @Override
    public Henkilo getResult() {
        return null;
    }


    @Override
    public void handleShown() {
        //
    }
    

    @Override
    public void setDefault(Henkilo uusiHenkilo) {
        this.kasiteltavaHenkilo = uusiHenkilo;
    }
   
    
    /**
     * @param modalityStage     mille stagelle ollaan modaalisia
     * @param uusiHenkilo       uusi henkilö jota käsitellään
     * @param bandbuddy         bandbuddy-luokka
     * @return                  modalcontrolleri
     */
    
    public static Henkilo avaaLisaaHenkilo(Stage modalityStage, Henkilo uusiHenkilo, BandBuddy bandbuddy) {
        return ModalController.<Henkilo, HenkilonLisaysController>showModal(HenkilonLisaysController.class.getResource("henkilonlisays.fxml"), "Uuden henkilön tiedot", modalityStage, uusiHenkilo, ctrl -> ctrl.setBandBuddy(bandbuddy));
    }

    
    private Object setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
        return null;
    }
    
    
}