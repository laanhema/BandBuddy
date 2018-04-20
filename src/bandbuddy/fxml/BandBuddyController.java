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
 * Ohjelman pääkontrolleri
 * @author Markus Mäntymaa & Lauri Makkonen
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
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "tallenna"
     * Kirjoittaa mahdolliset muutokset tiedostoon
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuTallenna(ActionEvent event) {
        bandbuddy.kirjoitaMuutokset();
        Dialogs.showMessageDialog("Tiedot tallennettu!");
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "sulje"
     * Sulkee ohjelman
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuSulje(ActionEvent event) {
        Platform.exit();
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "lisää uusi henkilö"
     * Avaa uuden ikkunan jossa luodaan uusi henkilö
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuLisaaUusiHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }
    

    /**
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "muokkaa henkilön tietoja"
     * Avaa uuden ikkunan jossa muokataan henkilön tietoja
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuMuokkaaHenkilonTietoja(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkilö-stringgridissä ei ole valittuna ketään, palataan takaisin
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "poista henkilön tiedot"
     * Avaa uuden ikkunan jossa varmistetaan halutaanko valitun henkilön tiedot poistaa
     * Kun painetaan kyllä, poistaa henkilön sekä sen instrumentit ja genret.
     * @param event             tapahtuma
     */
    @FXML private void painettuMenuPoista(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkilö-stringgridissä ei ole valittuna ketään, palataan takaisin
        HenkilonPoistoController.avaaHenkilonPoisto(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon mennään kun painetaan yläpalkin menusta "ohje"
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
    
    
//------------------------------------yläpalkin menun kontrollit päättyy tähän-----------------------------------
    
    
    /**
     * Aliohjelma johon mennään kun painetaan pääikkunassa "lisää henkilö"
     * Avaa uuden ikkunan jossa luodaan uusi henkilö
     * @param event             tapahtuma
     */
    @FXML private void painettuLisaaHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }


    /**
     * Aliohjelma johon mennään kun painetaan pääikkunassa "muokkaa henkilön tietoja"
     * Avaa uuden ikkunan jossa muokataan henkilön tietoja
     * @param event             tapahtuma
     */
    @FXML private void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkilö-stringgridissä ei ole valittuna ketään, palataan takaisin
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
        event.consume();
    }


    /**
     * Aliohjelma johon mennään kun pääikkunan hakukenttään kirjoitetaan jotain
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
     * Aliohjelma johon mennään kun klikataan pääikkunan henkilö-StringGridiä
     * @param event             tapahtuma
     */
    @FXML private void klikattuHenkiloStringGrid(MouseEvent event) {
        if (event.getClickCount() > 1) { // jos klikattiin enemmän kuin yhden kerran, avataan henkilön tiedot -ikkuna
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
        // ^ laitetaan henkilön kaikki instrumentit näkyviin
        
        List<HenkiloJaGenre> hjgLista = bandbuddy.genret(valittuHenkilo.getId());
        if (hjgLista.size() < 1) genreStringGrid.clear();
        List<String> genreLista = new ArrayList<String>();
        for (HenkiloJaGenre alkio : hjgLista) {
            genreLista.add(bandbuddy.genre(alkio.getGenrenNro()));
        }
        for (int i = 0; i < hjgLista.size(); i++) {
            lisaaGenreStringGridiin(genreLista);
        }
        // ^ laitetaan henkilön kaikki genret näkyviin
        
        event.consume();
    }
    
    
    /**
     * Aliohjelma johon mennään kun tuplaklikataan pääikkunan henkilö-StringGridiä
     * @param event             tapahtuma
     */
    @FXML private void tuplaklikattuHenkiloStringGrid(MouseEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return; event.consume(); // jos henkilö-stringgridissä ei ole valittuna ketään, palataan takaisin
        HenkilonNayttoController.avaaHenkilonNaytto(null, valittuHenkilo, bandbuddy); 
        event.consume();
    }
    
    
//----------------------------------------FXML:t päättyy tähän---------------------------------------------
    
    
    /**
     * Aliohjelma jota kutsutaan kun kontrolleri luodaan
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaStringGridit();
    }
    
    
    /**
     * Liittää bandbuddy-luokan kontrolleriin
     * @param bandbuddy     bandbuddy-luokka
     */
    void setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }
    
    
    /**
     * Laittaa kaikki henkilöt-taulukon alkiot näkymään henkilö-StringGridiin
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
     * Palauttaa henkilö-StringGridin tämän hetkisen valitun henkilön
     * @return          valittu henkilö
     */
    private Henkilo getValittuHenkiloStringGridista() {
        return henkiloStringGrid.getObject(henkiloStringGrid.getRowNr());
    }
    

    /**
     * Alustaa kaikki StringGridit
     * Laittaa StringGridien riveihin halutut tekstit ja säätää niiden asetukset
     */
    private void alustaStringGridit() {
        henkiloStringGrid.clear();
        instrumenttiStringGrid.clear();
        genreStringGrid.clear();
        
        String[] henkiloSGKolumnit = new String[7];
        henkiloSGKolumnit[0] = "Nimi";
        henkiloSGKolumnit[1] = "Ikä";
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
     * Lisää henkilön henkilö-StringGridiin
     * @param henkilo   lisättävä henkilö
     */
    private void lisaaHenkiloStringGridiin(Henkilo henkilo) {
        henkiloStringGrid.add(henkilo, henkilo.getNimi(), "" + henkilo.getIka(), henkilo.getPaikkakunta(), henkilo.getSukupuoli(), henkilo.getKokemus(), henkilo.getVapaana(), henkilo.getYhteystiedot());
    }
   
    
    /**
     * Lisää kaikki henkilön instrumentit instrumentti-StringGridiin
     * @param lisattavatInstrumentit   lisättävät instrumentit
     */
    private void lisaaInstrumenttiStringGridiin(List<String> lisattavatInstrumentit) {
        instrumenttiStringGrid.clear();
        for (String in : lisattavatInstrumentit)
        instrumenttiStringGrid.add(in);
    }
    
    
    /**
     * Lisää kaikki henkilön genret genre-StringGridiin
     * @param lisattavatGenret lisättävät genret
     */
    private void lisaaGenreStringGridiin(List<String> lisattavatGenret) {
        genreStringGrid.clear();
        for (String in : lisattavatGenret)
        genreStringGrid.add(in);
    }
    
    
    /**
     * Luo uuden henkilön ja avaa uuden ikkunan sen tietojen muokkaamiseen
     */
    private void uusiHenkilo() {
        Henkilo uusiHenkilo = new Henkilo();
        HenkilonLisaysController.avaaLisaaHenkilo(null, uusiHenkilo, bandbuddy);
        if (uusiHenkilo.getId() > 0) {
            bandbuddy.lisaa(uusiHenkilo);
            lisaaHenkiloStringGridiin(uusiHenkilo); 
        }
        // ^ jos henkilöä ei rekisteröity, ei lisätä sitä stringgridiin
    }   
}