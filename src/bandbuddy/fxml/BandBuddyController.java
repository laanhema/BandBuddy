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
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class BandBuddyController implements Initializable {
    
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
    
    private BandBuddy bandbuddy;

    
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
        event.consume();
    }
    
    
    @FXML void painettuMenuPoista(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        HenkilonPoistoController.avaaHenkilonPoisto(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
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
    
    
//------------------------------------yläpalkin menun kontrollit päättyy tähän-----------------------------------
    

    @FXML void painettuLisaaHenkilo(ActionEvent event) {
        uusiHenkilo();
        event.consume();
    }


    @FXML void painettuMuokkaaHenkilonTietoja(ActionEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        HenkilonMuokkausController.avaaHenkilonMuokkaus(null, valittuHenkilo, bandbuddy);
        laitaHenkilotTaulukkoStringGridiin();
        event.consume();
    }


    @FXML void kirjoitettuTarkennettuHaku(KeyEvent event) {
    	henkiloStringGrid.clear();
        Henkilo temp = new Henkilo();
        for (int i = 0; i < bandbuddy.getHenkilotTaulukonAlkioidenMaara(); i++) {
            temp = bandbuddy.getHenkilo(i);
            if (temp.getNimi().toLowerCase().contains(tarkennettuHaku.getText().toLowerCase()))
            henkiloStringGrid.add(temp, temp.getNimi(), "" + temp.getIka(), temp.getSukupuoli(), temp.getPaikkakunta(), temp.getKokemus(), temp.getVapaana(), temp.getYhteystiedot() );
        }
        event.consume();
    }
    
    @FXML void klikattuHenkiloStringGrid(MouseEvent event) {
        Henkilo valittuHenkilo = getValittuHenkiloStringGridista();
        if (valittuHenkilo == null) return;
        // System.out.println(bandbuddy.soittimet(valittuHenkilo.getId()));
        List<HenkiloJaInstrumentti> hjiLista = bandbuddy.soittimet(valittuHenkilo.getId());
        if (hjiLista.size() < 1) instrumenttiStringGrid.clear();
        List<String> instrumenttiLista = new ArrayList<String>();
        for (HenkiloJaInstrumentti iNumerot : hjiLista)
            instrumenttiLista.add(bandbuddy.soitin(iNumerot.getInstrumentinNro()));
        for (int i = 0; i < hjiLista.size(); i++) {
            lisaaStringGridiin(instrumenttiLista);
        }
        
        List<HenkiloJaGenre> hjgLista = bandbuddy.hGenret(valittuHenkilo.getId());
        if (hjgLista.size() < 1) genreStringGrid.clear();
        List<String> genreLista = new ArrayList<String>();
        for (HenkiloJaGenre iNumerot : hjgLista)
            genreLista.add(bandbuddy.hGenre(iNumerot.getGenrenNro()));
        for (int i = 0; i < hjgLista.size(); i++) {
            lisaaGenreStringGridiin(genreLista);
        }
        // System.out.println(hjiLista.get(i));
        // valittuHenkilo.tulosta(System.out);
        // System.out.println(r);
        event.consume();
    }
    
    
//----------------------------------------FXML:t päättyy tähän---------------------------------------------
    
    
    /**
     * Palauttaa henkilö-StringGridin tämän hetkisen valitun henkilön
     * @return      valittu henkilö
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
     * Liittää luokan BandBuddyController-luokkaan
     * @param bandbuddy     luokka mikä halutaan liittää
     */
    public void setBandBuddy(BandBuddy bandbuddy) {
        this.bandbuddy = bandbuddy;
    }


    /**
     * Laittaa henkilöt-taulukon alkiot näkymään StringGridiin
     */
    public void laitaHenkilotTaulukkoStringGridiin() {
        henkiloStringGrid.clear();
        Henkilo temp = new Henkilo();
        for (int i = 0; i < bandbuddy.getHenkilotTaulukonAlkioidenMaara(); i++) {
            temp = bandbuddy.getHenkilo(i);
            henkiloStringGrid.add(temp, temp.getNimi(), "" + temp.getIka(), temp.getSukupuoli(), temp.getPaikkakunta(), temp.getKokemus(), temp.getVapaana(), temp.getYhteystiedot() );
        }
    }
    
    
    /**
     * Lisää henkilön henkilö-StringGridiin
     * @param henkilo   lisättävä henkilö
     */
    public void lisaaHenkiloStringGridiin(Henkilo henkilo) {
        henkiloStringGrid.add(henkilo, henkilo.getNimi(), "" + henkilo.getIka(), henkilo.getPaikkakunta(), henkilo.getSukupuoli(), henkilo.getKokemus(), henkilo.getVapaana(), henkilo.getYhteystiedot());
    }
   
    
    /**
     * Lisää instrumentit instrumentti-StringGridiin
     * @param lisattavatInstrumentit   lisättävät instrumentit
     */
    public void lisaaStringGridiin(List<String> lisattavatInstrumentit) {
        instrumenttiStringGrid.clear();
        for (String in : lisattavatInstrumentit)
        instrumenttiStringGrid.add(in);
    }
    
    /**
     * Lisää instrumentit instrumentti-StringGridiin
     * @param lisattavatInstrumentit   lisättävät instrumentit
     */
    public void lisaaGenreStringGridiin(List<String> lisattavatGenret) {
        genreStringGrid.clear();
        for (String ge : lisattavatGenret)
        genreStringGrid.add(ge);
    }
    

    /**
     * Luo uuden henkilön
     */
    private void uusiHenkilo() {
        Henkilo uusiHenkilo = new Henkilo();
        HenkilonLisaysController.avaaLisaaHenkilo(null, uusiHenkilo, bandbuddy);
        if (uusiHenkilo.getId() > 0) lisaaHenkiloStringGridiin(uusiHenkilo); 
        // jos henkilöä ei rekisteröity, ei lisätä sitä stringGridiin
    }
    
    
}