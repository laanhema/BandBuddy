package bandbuddy.fxml;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import bandbuddy.BandBuddy;
import bandbuddy.Genre;
import bandbuddy.Henkilo;
import bandbuddy.HenkiloJaGenre;
import bandbuddy.HenkiloJaInstrumentti;
import bandbuddy.Instrumentti;
import fi.jyu.mit.fxgui.Dialogs;
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
 * Ohjelman p��kontrolleri
 * @author Markus M�ntymaa & Lauri Makkonen
 * @version 19.04.2018
 */
public class BandBuddyController implements Initializable {
    
    @FXML private MenuItem                  menuTallenna;
    @FXML private MenuItem                  menuSulje;
    @FXML private MenuItem                  menuUusiHenkilo;
    @FXML private MenuItem                  menuMuokkaaHenkilonTietoja;
    @FXML private MenuItem                  menuPoista;
    @FXML private MenuItem                  menuOhje;
    @FXML private Button                    LisaaHenkilo;
    @FXML private Button                    MuokkaaHenkilonTietoja;
    @FXML private TextField                 tarkennettuHaku;
    @FXML private StringGrid<Henkilo>       henkiloStringGrid;
    @FXML private StringGrid<Instrumentti>  instrumenttiStringGrid;
    @FXML private StringGrid<Genre>         genreStringGrid;
    
    private BandBuddy                       bandbuddy;

    
    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "tallenna"
     * Kirjoittaa mahdolliset muutokset tiedostoon
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuTallenna(ActionEvent event) {
        bandbuddy.kirjoitaMuutokset();
        Dialogs.showMessageDialog("Tiedot tallennettu!");
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "sulje"
     * Sulkee ohjelman
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuSulje(ActionEvent event) {
        Platform.exit();
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "lis�� uusi henkil�"
     * Avaa uuden ikkunan jossa luodaan uusi henkil�
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuLisaaUusiHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }
    

    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "muokkaa henkil�n tietoja"
     * Avaa uuden ikkunan jossa muokataan henkil�n tietoja
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuMuokkaaHenkilonTietoja(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkil�-stringgridiss� ei ole valittuna ket��n, palataan takaisin
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "poista henkil�n tiedot"
     * Avaa uuden ikkunan jossa varmistetaan halutaanko valitun henkil�n tiedot poistaa
     * Kun painetaan kyll�, poistaa henkil�n sek� sen instrumentit ja genret.
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuPoista(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkil�-stringgridiss� ei ole valittuna ket��n, palataan takaisin
        HenkilonPoistoController.avaaHenkilonPoisto(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan yl�palkin menusta "ohje"
     * Avaa selaimella sivun jossa kerrotaan tarkemmin ohjelmasta
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuOhje(ActionEvent event) {
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
    
    
    /**
     * Aliohjelma johon menn��n kun painetaan p��ikkunassa "lis�� henkil�"
     * Avaa uuden ikkunan jossa luodaan uusi henkil�
     * @param event             tapahtuma
     */
    @FXML private void painettuLisaaHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }


    /**
     * Aliohjelma johon menn��n kun painetaan p��ikkunassa "muokkaa henkil�n tietoja"
     * Avaa uuden ikkunan jossa muokataan henkil�n tietoja
     * @param event             tapahtuma
     */
    @FXML private void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkil�-stringgridiss� ei ole valittuna ket��n, palataan takaisin
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
        event.consume();
    }


    /**
     * Aliohjelma johon menn��n kun p��ikkunan hakukentt��n kirjoitetaan jotain
     * @param event             tapahtuma
     */
    @FXML private void kirjoitettuTarkennettuHaku(KeyEvent event) {
        henkiloStringGrid.clear();
        Henkilo temp = new Henkilo();
        for (int i = 0; i < bandbuddy.getHenkilotTaulukonAlkioidenMaara(); i++) {
            temp = bandbuddy.getHenkilo(i);
            if (temp.getNimi().toLowerCase().contains(tarkennettuHaku.getText().toLowerCase()))
            henkiloStringGrid.add(temp, temp.getNimi(), "" + temp.getIka(), temp.getSukupuoli(), temp.getPaikkakunta(), temp.getKokemus(), temp.getVapaana(), temp.getYhteystiedot() );
        }
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun klikataan p��ikkunan henkil�-StringGridi�
     * @param event             tapahtuma
     */
    @FXML private void klikattuHenkiloStringGrid(MouseEvent event) {
        if (event.getClickCount() > 1) { // jos klikattiin enemm�n kuin yhden kerran, avataan henkil�n tiedot -ikkuna
            tuplaklikattuHenkiloStringGrid(event);
            return;
        }
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return;
        
        List<HenkiloJaInstrumentti> hjiLista = bandbuddy.soittimet(valittuHenkilo.getId());
        if (hjiLista.size() < 1) instrumenttiStringGrid.clear();
        List<String> instrumenttiLista = new ArrayList<String>();
        for (HenkiloJaInstrumentti alkio : hjiLista) {
            instrumenttiLista.add(bandbuddy.soitin(alkio.getInstrumentinNro()));
        }
        for (int i = 0; i < hjiLista.size(); i++) {
            lisaaInstrumenttiStringGridiin(instrumenttiLista);
        }
        // ^ laitetaan henkil�n kaikki instrumentit n�kyviin
        
        List<HenkiloJaGenre> hjgLista = bandbuddy.genret(valittuHenkilo.getId());
        if (hjgLista.size() < 1) genreStringGrid.clear();
        List<String> genreLista = new ArrayList<String>();
        for (HenkiloJaGenre alkio : hjgLista) {
            genreLista.add(bandbuddy.genre(alkio.getGenrenNro()));
        }
        for (int i = 0; i < hjgLista.size(); i++) {
            lisaaGenreStringGridiin(genreLista);
        }
        // ^ laitetaan henkil�n kaikki genret n�kyviin
        
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon menn��n kun tuplaklikataan p��ikkunan henkil�-StringGridi�
     * @param event             tapahtuma
     */
    @FXML private void tuplaklikattuHenkiloStringGrid(MouseEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkil�-stringgridiss� ei ole valittuna ket��n, palataan takaisin
        HenkilonNayttoController.avaaHenkilonNaytto(null, valittuHenkilo, bandbuddy); 
        event.consume();
    }
    
    
//----------------------------------------FXML:t p��ttyy t�h�n---------------------------------------------
    
    
    /**
     * Aliohjelma jota kutsutaan kun kontrolleri luodaan
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaStringGridit();
    }
    
    
    /**
     * Liitt�� bandbuddy-luokan kontrolleriin
     * @param bandbuddy     bandbuddy-luokka
     */
    void setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }
    
    
    /**
     * Laittaa kaikki henkil�t-taulukon alkiot n�kym��n henkil�-StringGridiin
     */
    void laitaHenkilotTaulukkoStringGridiin() {
        henkiloStringGrid.clear();
        Henkilo temp = new Henkilo();
        for (int i = 0; i < bandbuddy.getHenkilotTaulukonAlkioidenMaara(); i++) {
            temp = bandbuddy.getHenkilo(i);
            henkiloStringGrid.add(temp, temp.getNimi(), "" + temp.getIka(), temp.getSukupuoli(), temp.getPaikkakunta(), temp.getKokemus(), temp.getVapaana(), temp.getYhteystiedot() );
        }
    }
    
    
    /**
     * Palauttaa henkil�-StringGridin t�m�n hetkisen valitun henkil�n
     * @return          valittu henkil�
     */
    private Henkilo getValittuHenkiloStringGridista() {
        return henkiloStringGrid.getObject(henkiloStringGrid.getRowNr());
    }
    

    /**
     * Alustaa kaikki StringGridit
     * Laittaa StringGridien riveihin halutut tekstit ja s��t�� niiden asetukset
     */
    private void alustaStringGridit() {
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
     * Lis�� henkil�n henkil�-StringGridiin
     * @param henkilo   lis�tt�v� henkil�
     */
    private void lisaaHenkiloStringGridiin(Henkilo henkilo) {
        henkiloStringGrid.add(henkilo, henkilo.getNimi(), "" + henkilo.getIka(), henkilo.getPaikkakunta(), henkilo.getSukupuoli(), henkilo.getKokemus(), henkilo.getVapaana(), henkilo.getYhteystiedot());
    }
   
    
    /**
     * Lis�� kaikki henkil�n instrumentit instrumentti-StringGridiin
     * @param lisattavatInstrumentit   lis�tt�v�t instrumentit
     */
    private void lisaaInstrumenttiStringGridiin(List<String> lisattavatInstrumentit) {
        instrumenttiStringGrid.clear();
        for (String in : lisattavatInstrumentit)
        instrumenttiStringGrid.add(in);
    }
    
    
    /**
     * Lis�� kaikki henkil�n genret genre-StringGridiin
     * @param lisattavatGenret lis�tt�v�t genret
     */
    private void lisaaGenreStringGridiin(List<String> lisattavatGenret) {
        genreStringGrid.clear();
        for (String in : lisattavatGenret)
        genreStringGrid.add(in);
    }
    
    
    /**
     * Luo uuden henkil�n ja avaa uuden ikkunan sen tietojen muokkaamiseen
     */
    private void uusiHenkilo() {
        Henkilo uusiHenkilo = new Henkilo();
        HenkilonLisaysController.avaaLisaaHenkilo(null, uusiHenkilo, bandbuddy);
        if (uusiHenkilo.getId() > 0) {
            bandbuddy.lisaa(uusiHenkilo);
            lisaaHenkiloStringGridiin(uusiHenkilo); 
        }
        // ^ jos henkil�� ei rekister�ity, ei lis�t� sit� stringgridiin
    }   
}