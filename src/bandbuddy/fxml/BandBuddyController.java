package bandbuddy.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import bandbuddy.BandBuddy;
import bandbuddy.Henkilo;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 13.03.2018
 */
public class BandBuddyController implements Initializable {
    private BandBuddy                   bandbuddy;
    @FXML private MenuItem              menuSulje;
    @FXML private MenuItem              menuTallenna;
    @FXML private MenuItem              menuUusiHenkilo;    
    @FXML private MenuItem              menuMuokkaaHenkilonTietoja;
    @FXML private MenuItem              menuPoista;
    @FXML private MenuItem              menuOhje;
    @FXML private Button                LisaaHenkilo;
    @FXML private Button                MuokkaaHenkilonTietoja;
    @FXML private TextField             tarkennettuHaku;
    @FXML private StringGrid<Henkilo>    stringGrid;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaStringGrid();
    }
    
    
    @FXML void painettuMenuOhje(ActionEvent event) {
    	 Dialogs.showMessageDialog("Tästä näkee lisätietoja");
    }
    

    @FXML void painettuMenuLisaaUusiHenkilo(ActionEvent event) {
        uusiHenkilo();
    }
    
    
    @FXML void painettuMenuMuokkaaHenkilonTietoja(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("henkilonmuokkaus.fxml"), "Tietojen muokkaus", null, "");
    }
    
    
    @FXML void painettuMenuPoista(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("dialogi_poisto.fxml"), "bandbuddy", null, "");
    }
    

    @FXML void painettuMenuSulje(ActionEvent event) {
         Platform.exit();
    }
    
    
    @FXML void painettuMenuTallenna(ActionEvent event) {
    	 Dialogs.showMessageDialog("Tämä tallentaa kaikki muutokset");
    }
    
    
    @FXML void painettuLisaaHenkilo(ActionEvent event) {
        uusiHenkilo();
    }
    
    
    /*
    @FXML void painettuTarkasteleHenkilonTietoja(MouseEvent event) {
    	if(event.getClickCount() == 2 ) {
    	    ModalController.showModal(BandBuddyController.class.getResource("tietokentta.fxml"), "bandbuddy", null, "");
    	}
    }
    */
    
    
    @FXML void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("henkilonmuokkaus.fxml"), "Tietojen muokkaus", null, "");
    }
    
    
    @FXML void kirjoitettuTarkennettuHaku(KeyEvent event) {
        Dialogs.showMessageDialog("Tästä voi hakea henkilöitä");
    }


    /**
     * Liittää luokan BandBuddyController-luokkaan
     * @param bandbuddy     luokka mikä halutaan liittää
     */
    public void setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }


    /**
     * Alustaa StringGridin
     */
    public void alustaStringGrid() {
        stringGrid.clear();
        
        String[] kolumnit = new String[8];
        

        
        kolumnit[0] = "nimi";
        kolumnit[1] = "id";
        kolumnit[2] = "ikä";
        kolumnit[3] = "kaupunki";
        kolumnit[4] = "sukupuoli";
        kolumnit[5] = "kokemus";
        kolumnit[6] = "vapaana";
        kolumnit[7] = "YT";
        
        
        /*
        String[] kolumnit = new String[4];
        kolumnit[0] = "Nimi";
        kolumnit[1] = "Genre";
        kolumnit[2] = "Instrumentti";
        kolumnit[3] = "Kaupunki";
        */
        
        stringGrid.initTable(kolumnit); 
        
        for (int i = 0; i < kolumnit.length; i++) {
            stringGrid.setColumnWidth(i, 72);
        }

        
        /*
        stringGrid.setColumnWidth(0, 244);
        for (int i = 1; i < kolumnit.length; i++) {
            stringGrid.setColumnWidth(i, 110);
        }
        */
        
        
        
        stringGrid.disableColumnReOrder();
        // stringGrid.setTableMenuButtonVisible(true);
        //stringGrid.setSortable(-1, false);
    }
    
    
    /**
     * Luo uuden henkilön
     */
    private void uusiHenkilo() {
        Henkilo uusi = new Henkilo();
        bandbuddy.lisaa(uusi);
        // uusi.taytaValiaikaisetTiedot();
        HenkilonLisaysController.avaaLisaaHenkilo(null, uusi);
        lisaaStringGridiin(uusi);
    }
   
    
    /**
     * Lisää henkilön StringGridiin
     * @param henkilo x
     */
    public void lisaaStringGridiin(Henkilo henkilo) {
        stringGrid.add(henkilo.getNimi(), "" + henkilo.getId(), "" + henkilo.getIka(), henkilo.getPaikkakunta(), henkilo.getSukupuoli(), henkilo.getKokemus(), henkilo.getVapaana(), henkilo.getYhteystiedot());
    }
    
    
    
}