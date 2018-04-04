package bandbuddy.fxml;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import bandbuddy.BandBuddy;
import bandbuddy.Genre;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaInstrumentti;
import bandbuddy.Instrumentti;
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
import javafx.scene.input.MouseEvent;

/**
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class BandBuddyController implements Initializable {
    private BandBuddy bandbuddy;
    private static String instrumentti;
    @FXML private MenuItem menuSulje;
    @FXML private MenuItem menuTallenna;
    @FXML private MenuItem menuUusiHenkilo;
    @FXML private MenuItem menuMuokkaaHenkilonTietoja;
    @FXML private MenuItem menuPoista;
    @FXML private MenuItem menuOhje;
    @FXML private Button LisaaHenkilo;
    @FXML private Button MuokkaaHenkilonTietoja;
    @FXML private TextField tarkennettuHaku;
    @FXML private StringGrid<Henkilo> henkiloStringGrid;
    @FXML private StringGrid<Instrumentti> instrumenttiStringGrid;
    @FXML private StringGrid<Genre> genreStringGrid;

    
    @FXML void painettuMenuTallenna(ActionEvent event) {
        Dialogs.showMessageDialog("Tiedot tallennettu!");
        bandbuddy.kirjoitaMuutokset();
        event.consume();
    }
    
    
    @FXML void painettuMenuSulje(ActionEvent event) {
        Platform.exit();
        event.consume();
    }
    
    
    @FXML void painettuMenuLisaaUusiHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }
    

    @FXML void painettuMenuMuokkaaHenkilonTietoja(ActionEvent event) {

        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        ModalController.showModal(
                BandBuddyController.class.getResource("henkilonmuokkaus.fxml"),
                "Tietojen muokkaus", null, "");
        // otetaan valitun henkil�n tiedot k�ytt��n ja laitetaan ne uuteen ikkunaan
        
        /*
        String[] henkilonKentat = new String[7];
        henkilonKentat[0] = valittuHenkilo.getNimi();
        henkilonKentat[1] = "" + valittuHenkilo.getIka();
        henkilonKentat[2] = valittuHenkilo.getSukupuoli();
        henkilonKentat[3] = valittuHenkilo.getPaikkakunta();
        // getInstrumentit
        // getGenret
        henkilonKentat[4] = valittuHenkilo.getVapaana();
        henkilonKentat[5] = valittuHenkilo.getKokemus();
        henkilonKentat[6] = valittuHenkilo.getYhteystiedot();
        */
        
        event.consume();
    }
    
    
    @FXML void painettuMenuPoista(ActionEvent event) {
        ModalController.showModal(
                BandBuddyController.class.getResource("dialogi_poisto.fxml"),
                "bandbuddy", null, "");
        event.consume();
    }
    
    
    @FXML void painettuMenuOhje(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2018k/ht/laanhema");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
        event.consume();
    }
    
    
//------------------------------------yl�palkin menun kontrollit p��ttyy t�h�n-----------------------------------
    

    @FXML void painettuLisaaHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }


    @FXML void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
        ModalController.showModal(
                BandBuddyController.class.getResource("henkilonmuokkaus.fxml"),
                "Tietojen muokkaus", null, "");
        event.consume();
    }


    @FXML void kirjoitettuTarkennettuHaku(KeyEvent event) {
        Dialogs.showMessageDialog("T�st� voi hakea henkil�it�");
        event.consume();
    }
    
    
    @FXML void klikattuHenkiloStringGrid(MouseEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        // System.out.println(bandbuddy.soittimet(valittuHenkilo.getId()));
        List<HenkiloJaInstrumentti> hjiLista = bandbuddy.soittimet(valittuHenkilo.getId());
        List<String> instrumenttiLista = new ArrayList<String>();
        for (HenkiloJaInstrumentti iNumerot : hjiLista)
            instrumenttiLista.add(bandbuddy.soitin(iNumerot.getInstrumentinNro()));
        for (int i = 0; i < hjiLista.size(); i++)
           //  System.out.println(hjiLista.get(i));
        lisaaStringGridiin(instrumenttiLista);
        
        // valittuHenkilo.tulosta(System.out);
        // System.out.println(r);
        event.consume();
    }
    
    
//----------------------------------------FXML:t p��ttyy t�h�n---------------------------------------------
    
    
    /**
     * Palauttaa henkil�-StringGridin t�m�n hetkisen valitun henkil�n
     * @return      valittu henkil�
     */
    public Henkilo getValittuHenkiloStringGridista() {
        return henkiloStringGrid.getObject(henkiloStringGrid.getRowNr());
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaStringGridit();
    }


    /**
     * Alustaa StringGridit
     */
    public void alustaStringGridit() {
        henkiloStringGrid.clear();
        instrumenttiStringGrid.clear();
        genreStringGrid.clear();
        String[] henkiloSGKolumnit = new String[7];
        henkiloSGKolumnit[0] = "Nimi";
        henkiloSGKolumnit[1] = "Ik�";
        henkiloSGKolumnit[2] = "Sukupuoli";
        henkiloSGKolumnit[3] = "Kaupunki";
        henkiloSGKolumnit[4] = "Kokemus";
        henkiloSGKolumnit[5] = "Vapaana";
        henkiloSGKolumnit[6] = "Yhteystiedot";
        henkiloStringGrid.initTable(henkiloSGKolumnit);
        for (int i = 0; i < henkiloSGKolumnit.length; i++) {
            henkiloStringGrid.setColumnWidth(i, 80);
        }
        henkiloStringGrid.setColumnWidth(0, 130);
        henkiloStringGrid.setColumnWidth(1, 30);
        henkiloStringGrid.disableColumnReOrder();
        // henkiloStringGrid.setTableMenuButtonVisible(true);
        // henkiloStringGrid.setSortable(-1, false);
        
        instrumenttiStringGrid.initTable("Instrumentit");
        instrumenttiStringGrid.setColumnWidth(0, 187);
        genreStringGrid.initTable("Genret");
        genreStringGrid.setColumnWidth(0, 187);
    }
    
    
    /**
     * Liitt�� luokan BandBuddyController-luokkaan
     * @param bandbuddy     luokka mik� halutaan liitt��
     */
    public void setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }


    /**
     * Laittaa henkil�t-taulukon alkiot n�kym��n StringGridiin
     */
    public void laitaHenkilotTaulukkoStringGridiin() {
        Henkilo temp = new Henkilo();
        for (int i = 0; i < bandbuddy.getHenkilotTaulukonAlkioidenMaara(); i++) {
            temp = bandbuddy.getHenkilo(i);
            henkiloStringGrid.add(temp, temp.getNimi(), "" + temp.getIka(), temp.getSukupuoli(), temp.getPaikkakunta(), temp.getKokemus(), temp.getVapaana(), temp.getYhteystiedot() );
        }
    }
    
    
    /**
     * Lis�� henkil�n henkil�-StringGridiin
     * @param henkilo   lis�tt�v� henkil�
     */
    public void lisaaStringGridiin(Henkilo henkilo) {
        henkiloStringGrid.add(henkilo, henkilo.getNimi(), "" + henkilo.getIka(), henkilo.getPaikkakunta(), henkilo.getSukupuoli(), henkilo.getKokemus(), henkilo.getVapaana(), henkilo.getYhteystiedot());
    }
    
    
    
    
    /**
     * Lis�� instrumentit instrumentti-StringGridiin
     * @param lisattavatInstrumentit   lis�tt�v�t instrumentit
     */
    public void lisaaStringGridiin(List<String> lisattavatInstrumentit) {
        instrumenttiStringGrid.clear();
        for (String in : lisattavatInstrumentit)
        instrumenttiStringGrid.add(in);
    }
    

    /**
     * Luo uuden henkil�n
     */
    private void uusiHenkilo() {
        Henkilo uusi = new Henkilo();
        bandbuddy.lisaa(uusi);
        Instrumentti uusiInstrumentti = new Instrumentti();
        // uusi.taytaValiaikaisetTiedot();
        HenkilonLisaysController.avaaLisaaHenkilo(null, uusi, uusiInstrumentti);
        uusiInstrumentti.setNimi(instrumentti);
        lisaaStringGridiin(uusi);
    }


    /**
     * Palauttaa instrumentin
     * @param merkkijono    instrumentti
     */
    public static void getInstrumentti(String merkkijono) {
        instrumentti = merkkijono;
    }
    
    
    /**
     * @param valittuHenkilo        x   
     * @return                      x
     */
    public String[] luoStringTaulukkoHenkilonKentista(Henkilo valittuHenkilo) {
        String[] henkilonKentat = new String[7];
        henkilonKentat[0] = valittuHenkilo.getNimi();
        henkilonKentat[1] = "" + valittuHenkilo.getIka();
        henkilonKentat[2] = valittuHenkilo.getSukupuoli();
        henkilonKentat[3] = valittuHenkilo.getPaikkakunta();
        // getInstrumentit
        // getGenret
        henkilonKentat[4] = valittuHenkilo.getVapaana();
        henkilonKentat[5] = valittuHenkilo.getKokemus();
        henkilonKentat[6] = valittuHenkilo.getYhteystiedot();
        return henkilonKentat;
    }
    
    
}