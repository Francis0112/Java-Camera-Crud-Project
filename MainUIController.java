/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.sql.*;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MainUIController implements Initializable {
    
    private final Connection conn = new DBConnect().con;
    private PreparedStatement ps;
    private ResultSet rs;
    private final ObservableList<people> data = FXCollections.observableArrayList();
    private Image image;
    private Image imageInput;
    private File file;
    private FileChooser fileChooser;
    private FileInputStream fileInputStream;
    private VideoCapture capture;
    private boolean cameraActive;
    private ScheduledExecutorService timer;
    private Mat frame;
    private InputStream is;
    private OutputStream os;
    
   
        
 
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label label1;

    @FXML
    private ImageView userimage;

    @FXML
    private Label usernamelabel;

    @FXML
    private Label passwordlabel;

    @FXML
    private JFXTextField searchpeople;

    @FXML
    private TableView<people> tablev;

    @FXML
    private TableColumn<people, String> usernameColumn;

    @FXML
    private TableColumn<people, String> passwordColumn;
    
    @FXML
    private ImageView image_view;

    @FXML
    private JFXButton CAM_btn;

    @FXML
    private JFXButton File_btn;

    @FXML
    private TextArea text_area;

    @FXML
    private JFXTextField username_textbox;

    @FXML
    private JFXPasswordField password_textbox;
    
    @FXML
    private JFXButton ADD_Data_btn;

    @FXML
    private JFXButton Edit_Data_btn;

    @FXML
    private JFXButton Delete_Data_btn;
    
    @FXML
    private JFXButton Done_Data_btn;
    
    @FXML
    private Label ID_label;

    @FXML
    private JFXButton clear_btn;
    
    @FXML
    private JFXButton Take_btn;
    
    @FXML
    private ImageView image_lay;
    
    @FXML
    private JFXNodesList nodelist;
    
   
    
    
    
    @FXML
    void Data_add(ActionEvent event) throws IOException {
        mysqlAdd();
    }
    
    @FXML
    void Done_Data(ActionEvent event) throws IOException {
        mysqlDoneEdit();
    }

    @FXML
    void Edit_Data(ActionEvent event) {
        mysqlEdit();
    }
    
    @FXML
    void Delete_Data(ActionEvent event) {
        mysqlDelete();
    }
    
    @FXML
    void clear_user(ActionEvent event) {
        clearFields();
    }

    
    @FXML
    void Choose_image(ActionEvent event) {
      chooseProfileImage();
    }
    
    

    @FXML
    void Start_cam(ActionEvent event) {
        startCamera();
    }
    
      @FXML
    void Take_cam(ActionEvent event) {
        snapShot();
    }

    
    
    @FXML
    void berk(KeyEvent event) {
        searchTableData();
    }
    
     @FXML
    void selectedRow(MouseEvent event) throws IOException {
       displayInformation();
    }
    
     @FXML
    void pressed_downed(KeyEvent event) {
        pressItDownBabe();
    }
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       this.fileInputStream = null;
       this.is = null;
       this.os = null;
       this.capture = new VideoCapture();
       this.cameraActive = false;
       
       
     
        try {

            VBox box = FXMLLoader.load(getClass().getResource("drawerKek.fxml"));
            drawer.setSidePane(box);

            HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(hamburger);
            burgerTask.setRate(-1);

            hamburger.setOnMouseClicked((MouseEvent event) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                if (drawer.isShown()) {
                    label1.setVisible(false);
                    drawer.close();
                } else {
                    drawer.open();
                    label1.setVisible(true);
                    label1.setText("FDCIS");
                }
            });

        } catch (IOException ex) {
            System.out.println(ex);
        }
               
        try {
            String sql = "select * from users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new people(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        tablev.setItems(data);
        image = new Image(getClass().getResource("image/Minecraft_Zombie.png").toExternalForm());
        image_view.setImage(image);
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif")
        );
         
        Edit_Data_btn.setDisable(true);
        Done_Data_btn.setDisable(true);
        Delete_Data_btn.setDisable(true);
        File_btn.setDisable(false);
        Take_btn.setDisable(true);
        CAM_btn.setDisable(false);
       
    }
    
    
// ******************************METHODS*************************************************************************************    
   
    
    private void clearFields(){
        searchpeople.requestFocus();
        text_area.clear();
        username_textbox.clear();
        password_textbox.clear();
        image = new Image(getClass().getResource("image/Minecraft_Zombie.png").toExternalForm());
        image_view.setImage(image);
        file = null;
        
        if(this.capture.isOpened()){
            try {
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            this.cameraActive = false;
            this.capture.release();
            //image = new Image(getClass().getResource("image/Minecraft_Zombie.png").toExternalForm());
            this.image_view.setImage(image);
            this.CAM_btn.setText("CAM");
            this.Take_btn.setDisable(true);
            this.File_btn.setDisable(false);
        }
        
        ID_label.setText(null);
        usernamelabel.setText(null);
        passwordlabel.setText(null);
        userimage.setImage(null);
        
        ADD_Data_btn.setDisable(false);
        Edit_Data_btn.setDisable(true);
        Done_Data_btn.setDisable(true);
        Delete_Data_btn.setDisable(true);
        
    }
    
    private void loadTable(){
         data.clear();
        try {
            String sql = "select * from users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new people(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        tablev.setItems(data);
    }

    private Image grabFrame() {
            Image imageToShow = null;
            frame = new Mat();
            
            if(capture.isOpened()){
                try {
                    capture.read(frame);
                    if(!frame.empty()){
                        imageToShow = Mat2Image(frame);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        return imageToShow;
    }
    
    private Image Mat2Image(Mat frame){
            MatOfByte buffer = new MatOfByte();
            Imgcodecs.imencode(".jpg", frame, buffer);
            return new Image(new ByteArrayInputStream(buffer.toArray()));
    }
    
    private void snapShot(){
        String imagepath = "C:\\Users\\franc\\Pictures\\Photos\\photo.jpg";
        Imgcodecs.imwrite(imagepath, frame);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FDCIS");
        alert.setHeaderText(null);
        alert.setContentText("Picture have been taken.");
        alert.showAndWait();
     }
    
    private void startCamera(){
        if(!this.cameraActive){
          this.capture.open(0);
            Take_btn.setDisable(false);
            File_btn.setDisable(true);
          if(capture.isOpened()){
              this.CAM_btn.setText("OFF");
              this.cameraActive = true;
              Runnable frameGrabber =() -> {
                  Image imageToShow = grabFrame();
                  image_view.setImage(imageToShow);
              };
              this.timer = Executors.newSingleThreadScheduledExecutor();
              this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
          }
          else {
              System.out.println("cannot use camera you should quit this fuck!");
          }
      }
      else {
          this.CAM_btn.setText("CAM");
          this.cameraActive = false;
          File_btn.setDisable(false);
          Take_btn.setDisable(true);
          try {
              this.timer.shutdown();
              this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
          } catch (InterruptedException e) {
              System.out.println(e);
          }
          this.capture.release();
          this.image_view.setImage(image = new Image(getClass().getResource("image/Minecraft_Zombie.png").toExternalForm()));
      }
    }
    
    private void chooseProfileImage(){
        Stage stage = new Stage();
        file = fileChooser.showOpenDialog(stage);
        if(file != null){
            try {
                
            imageInput = new Image(file.toURI().toString());
            image_view.setImage(imageInput);
            text_area.setText(file.getAbsolutePath());
           
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    private void mysqlAdd() throws IOException{
        try {
            String sql = "select * from users where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username_textbox.getText());
            rs = ps.executeQuery();
            if(rs.next()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("FDCIS");
           alert.setHeaderText(null);
           alert.setContentText("User already Exist.");
           alert.showAndWait();
           return;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
       if(username_textbox.getText().isEmpty() | password_textbox.getText().isEmpty() | file == null){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("FDCIS");
           alert.setHeaderText(null);
           alert.setContentText("Please fill the required fields.");
           alert.showAndWait();
       }
       else {
            try {
              String sql = "insert into users (username, password, image) values (?,?,?)";
              ps = conn.prepareStatement(sql);
              
              String username_textbox1 = username_textbox.getText().trim();
              String gam1 = username_textbox1.replaceAll("\\s","");
              String password_textbox1 = password_textbox.getText().trim();
              String gam2 = password_textbox1.replaceAll("\\s","");
              
              ps.setString(1, gam1);
              ps.setString(2, gam2);
              fileInputStream = new FileInputStream(file);
              ps.setBinaryStream(3, (InputStream)fileInputStream, (int)file.length());
              
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("FDCIS");
                  alert.setHeaderText(null);
                  alert.setContentText("user inserted.");
                  alert.showAndWait();
                  
              ps.execute();    
              ps.close();
              fileInputStream.close();
         
              clearFields();
              loadTable();
              file = null;
              
          } catch (SQLException e) {
             System.out.println(e);
          } catch (FileNotFoundException ex) {    
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
    
    private void mysqlEdit(){
        username_textbox.setText(usernamelabel.getText());
        password_textbox.setText(passwordlabel.getText());
        image_view.setImage(userimage.getImage());
        ADD_Data_btn.setDisable(true);
        Delete_Data_btn.setDisable(true);
        Done_Data_btn.setDisable(false);
    }
    
    private void mysqlDoneEdit() throws IOException {
        try {
            String sql = "select * from users where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username_textbox.getText());
            rs = ps.executeQuery();
            if(rs.next()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("FDCIS");
           alert.setHeaderText(null);
           alert.setContentText("User already Exist.");
           alert.showAndWait();
           return;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
       if(username_textbox.getText().isEmpty() | password_textbox.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("FDCIS");
          alert.setHeaderText(null);
          alert.setContentText("Please fill the required fields.");
          alert.showAndWait();
        
       }
       else {
            if(file == null){
        try {
        String sql = "update users set username=?, password=? where id='"+ID_label.getText()+"' ";
        ps = conn.prepareStatement(sql);
        
        String drug1 = username_textbox.getText().trim();
        String drug2 = password_textbox.getText().trim();
        String drug01 = drug1.replaceAll("\\s","");
        String drug02 = drug2.replaceAll("\\s","");
        
        ps.setString(1, drug01);
        ps.setString(2, drug02);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DCIS");
        alert.setHeaderText(null);
        alert.setContentText("user updated.");
        alert.showAndWait();
        
        ps.execute();
        ps.close();
        file = null;
        
        clearFields();
        loadTable();
        
        } catch (SQLException e) {
        System.err.println(e);
        }
        }
        else {
        try {
        String sql = "update users set username=?, password=?, image=? where id='"+ID_label.getText()+"'";
        ps = conn.prepareStatement(sql);
        
        String grim = username_textbox.getText().trim();
        String gram = password_textbox.getText().trim();
        String grim1 = grim.replaceAll("\\s","");
        String gram1 = gram.replaceAll("\\s","");
        
        ps.setString(1, grim1);
        ps.setString(2, gram1);
        fileInputStream = new FileInputStream(file);
        ps.setBinaryStream(3, (InputStream)fileInputStream, (int)file.length());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FDCIS");
        alert.setHeaderText(null);
        alert.setContentText("user updated.");
        alert.showAndWait();
        
        ps.execute();
        ps.close();
        fileInputStream.close();
        
        clearFields();
        loadTable();
        file = null;
        } catch (SQLException e) {
        System.out.println(e);
        } catch (FileNotFoundException ex) {
        Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       }
    }
    
    private void mysqlDelete(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FDCIS");
        alert.setHeaderText(null);
        alert.setContentText("are you sure you want to delete user?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if(action.get() == ButtonType.OK){
             try {
            String sql = "delete from users where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ID_label.getText());
            ps.execute();
            ps.close();
            
            clearFields();
            loadTable();
        } catch (SQLException e) {
            System.out.println(e);
        }   
        }
    }
    
    private void searchTableData(){
         FilteredList<people> filterData = new FilteredList<>(data, p -> true);
        searchpeople.textProperty().addListener((observable, newValue, oldValue) -> {
            filterData.setPredicate(people ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(people.getUsername().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(people.getPassword().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
       
        SortedList<people> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tablev.comparatorProperty());
        tablev.setItems(sortedData);
    }
    
    private void displayInformation() throws IOException{
        Edit_Data_btn.setDisable(false);
        Delete_Data_btn.setDisable(false);
         String sql = "select * from users where id=?";
         try {
             people pple =(people)tablev.getSelectionModel().getSelectedItem();
             ps = conn.prepareStatement(sql);
             ps.setInt(1, pple.getID());
             rs = ps.executeQuery();
             while (rs.next()) {
                 
                 ID_label.setText(rs.getString("id"));
                 usernamelabel.setText(rs.getString("username"));
                 passwordlabel.setText(rs.getString("password"));
                  
                is = rs.getBinaryStream("image"); 
                os = new FileOutputStream(new File("image.jpg"));
                     byte[] content = new byte[1024];
                     int size;
                     while ((size = is.read(content))!=-1){
                         os.write(content, 0, size);
                     }
                    
                    
                 image = new Image("file:image.jpg");
                 userimage.setImage(image);
             }
             ps.close();
             rs.close();
            
             
         } catch (SQLException e) {
             System.out.println(e);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void pressItDownBabe(){
        tablev.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.UP | event.getCode() == KeyCode.DOWN){
                try {
                    displayInformation();
                } catch (IOException ex) {
                    Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
