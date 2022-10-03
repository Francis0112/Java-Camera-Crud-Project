      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package savage;

import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {
    
     private final Connection conn = new DBConnect().con;
     private PreparedStatement ps;
     private ResultSet rs;
     private final double xoffset = 0;
     private final double yoffset = 0;
     private Alert alert;
     
     
     
     
    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private AnchorPane anchorpane2;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView image1;

    @FXML
    private Button loginBtn;
    
    @FXML
    private Hyperlink info_url;

    @FXML
    private Hyperlink info_register;
   

    
    @FXML
    void info_register1() {
    info_register.setOnMouseClicked((MouseEvent event) -> {
        Savage sav = new Savage();
        sav.getURL_Register();
    });
    }

    @FXML
    void info_url1() {
    info_url.setOnMouseClicked((MouseEvent event) -> {
        Savage sav = new Savage();
        sav.getURL_Info();
    });
    }
    

        @FXML
    void logine(ActionEvent event) {
     
     String sql ="select * from users where binary username=? and binary password=?";
     
            try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs = ps.executeQuery();
            int y = 2;
            while(y == 2){
            if(rs.next()){
               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("welcome to FDCIS!");
               alert.showAndWait();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                 Savage sav = new Savage();
                 sav.maing(); 
                break;
                
            }
            else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("invalid username or password.");
               alert.showAndWait();
                username.setText(null);
                password.setText(null);
                username.requestFocus();
                return;
            }
            
            }
            conn.close();
            
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    
      @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }
    
     @FXML
    void loginPress(KeyEvent event) throws Exception {
        if(event.getCode().equals(KeyCode.ENTER)){
            String sql ="select * from users where binary username=? and binary password=?";
     
            try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs = ps.executeQuery();
            int y = 2;
            while(y == 2){
            if(rs.next()){
               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("welcome to FDCIS!");
               alert.showAndWait();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                 Savage sav = new Savage();
                 sav.maing(); 
                break;
                
            }
            else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("invalid username or password.");
               alert.showAndWait();
                username.setText(null);
                password.setText(null);
                username.requestFocus();
                return;
            }
            
            }
            conn.close();
            
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    @FXML
    void passFieldLogin(KeyEvent event) throws Exception{
        if(event.getCode().equals(KeyCode.ENTER)){
            String sql ="select * from users where binary username=? and binary password=?";
            
            try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs = ps.executeQuery();
            int y = 2;
            while(y == 2){
            if(rs.next()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("FDCIS");
                alert.setHeaderText(null);
                alert.setContentText("welcome to FDCIS!");
                alert.showAndWait();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Savage sav = new Savage(); 
                sav.maing(); 
                break;
                
            }
            else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("invalid username or password.");
               alert.showAndWait();
                username.setText(null);
                password.setText(null);
                username.requestFocus();
                return;
            }
            
            }
            conn.close();
            
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
      @FXML
    void textFieldLogin(KeyEvent event) throws Exception {
        if(event.getCode().equals(KeyCode.ENTER)){
        String sql ="select * from users where binary username=? and binary password=?";
     
            try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs = ps.executeQuery();
            int y = 2;
            while(y == 2){
            if(rs.next()){
               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("welcome to FDCIS!");
               alert.showAndWait();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                 Savage sav = new Savage();
                 sav.maing(); 
                break;  
            }
            else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("FDCIS");
               alert.setHeaderText(null);
               alert.setContentText("invalid username or password.");
               alert.showAndWait();
                username.setText(null);
                password.setText(null);
                username.requestFocus();
                return;
            }
            
            }
            conn.close();
            
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
}
