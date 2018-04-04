package bandbuddy.fxml;

import bandbuddy.BandBuddy;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Markus Mäntymaa & Lauri Makkonen
 * @version 03.04.2018
 */
public class BandBuddyMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("BandBuddyView.fxml"));
            final Pane root = ldr.load();
            final BandBuddyController bandbuddyCtrl = (BandBuddyController) ldr.getController();
            
            
            BandBuddy bandbuddy = new BandBuddy();
            bandbuddyCtrl.setBandBuddy(bandbuddy);
            bandbuddy.lueTiedostosta();
            bandbuddyCtrl.laitaHenkilotTaulukkoStringGridiin();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("bandbuddy.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("BandBuddy");
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}