/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savage;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DrawerKekController implements Initializable {

    @FXML
    private VBox box;
    @FXML
    private ImageView image;
    @FXML
    private ImageView Animation_logo;
    @FXML
    private JFXButton exit_btn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Exit1(ActionEvent event) throws IOException {
            
          Stage stage = (Stage) exit_btn.getScene().getWindow();
          stage.close();
        
          Savage sav = new Savage();
          sav.BacktoLoginWindow();
          
    }
    
}
