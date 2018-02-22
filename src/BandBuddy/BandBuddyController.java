package BandBuddy;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;


/**
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 22.2.2018
 *
 */
public class BandBuddyController {
    
    @FXML
    private MenuItem menuSulje;
    
    @FXML
    private MenuItem menuTallenna;
    
    @FXML
    private MenuItem menuUusiHenkilo;
    
    @FXML
    private MenuItem menuMuokkaaHenkilonTietoja;

    @FXML
    private MenuItem menuPoista;

    @FXML
    private MenuItem menuOhje;
    
    @FXML
    private TableView<?> tableView;
    
    @FXML
    private Button LisaaHenkilo;
    
    @FXML
    private Button MuokkaaHenkilonTietoja;

    @FXML
    void painettuMenuOhje(ActionEvent event) {
    	 Dialogs.showMessageDialog("T�st� n�kee lis�tietoja");
    }

    @FXML
    void painettuMenuLisaaUusiHenkilo(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("jasenenlisays.fxml"), "BandBuddy", null, "");
    }
    @FXML
    void painettuMenuMuokkaaHenkilonTietoja(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("editointikentta.fxml"), "BandBuddy", null, "");
    }
    @FXML
    void painettuMenuPoista(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("dialogi_poisto.fxml"), "BandBuddy", null, "");
    }

    @FXML
    void painettuMenuSulje(ActionEvent event) {
    	 Dialogs.showMessageDialog("T�m� sulkee ohjelman");
    }
    @FXML
    void painettuMenuTallenna(ActionEvent event) {
    	 Dialogs.showMessageDialog("T�m� tallentaa kaikki muutokset");
    }
    @FXML
    void painettuLisaaHenkilo(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("jasenenlisays.fxml"), "BandBuddy", null, "");
    }
    @FXML
    void painettuTarkasteleHenkilonTietoja(MouseEvent event) {
    	if(event.getClickCount() == 2 ) {
    	    ModalController.showModal(BandBuddyController.class.getResource("tietokentta.fxml"), "BandBuddy", null, "");
    	}
    }
    @FXML
    void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
    	ModalController.showModal(BandBuddyController.class.getResource("editointikentta.fxml"), "BandBuddy", null, "");
    }
}