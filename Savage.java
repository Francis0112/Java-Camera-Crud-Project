

package savage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.opencv.core.Core;


public class Savage extends Application {
    
    private double xoffset = 0;
    private double yoffset = 0;
    private ProgressBar pb;
    private ProgressIndicator pi;
   

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
       
        
        root.setOnMousePressed((MouseEvent event) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            xoffset = event.getSceneX();
            yoffset = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            stage.setX(event.getScreenX()-xoffset);
            stage.setY(event.getScreenY()-yoffset);
        });

        stage.setScene(scene);
        stage.show();
    }
    
    public void maing() throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getResource("mainUI.fxml"));
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        //stage.initStyle(StageStyle.UNDECORATED);
        
        root1.setOnMousePressed((MouseEvent event) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            xoffset = event.getSceneX();
            yoffset = event.getSceneY();
        });
        
        root1.setOnMouseDragged((MouseEvent event) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            stage.setX(event.getScreenX()-xoffset);
            stage.setY(event.getScreenY()-yoffset);
        });
       stage.setScene(scene);
       stage.show();  
    }
    
    public void OpenAddWindow() throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("AddWindow.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        
        root2.setOnMousePressed((MouseEvent event) -> {
           xoffset = event.getSceneX();
           yoffset = event.getSceneY();
        });
        
        root2.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX()-xoffset);
            stage.setY(event.getScreenY()-yoffset);
        });
        stage.setScene(scene);
        stage.show();
    }
    
    public void BacktoLoginWindow() throws IOException{
        Parent root3 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root3);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        
        root3.setOnMousePressed((MouseEvent event) -> {
           xoffset = event.getSceneX();
           yoffset = event.getSceneY();
        });
        
        root3.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX()-xoffset);
            stage.setY(event.getScreenY()-yoffset);
        });
        stage.setScene(scene);
        stage.show();
    }
   
    
    public void getURL_Register(){
        getHostServices().showDocument("http://localhost/gameWeb/signup.php");
    }
    
    public void getURL_Info(){
       getHostServices().showDocument("http://localhost/gameWeb/login.php");
    }
    
    public void processD(){
        pb = new ProgressBar();
        pi = new ProgressIndicator();
    }
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // load native library of opencv
        launch(args);
    }
}