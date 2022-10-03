package savage;

import java.sql.Blob;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class people{

    private SimpleIntegerProperty id;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty image;
   
    
    
    public people(int varID, String varUsername, String varPassword, String varImage){
    
        this.id = new SimpleIntegerProperty(varID);
        this.username = new SimpleStringProperty(varUsername);
        this.password = new SimpleStringProperty(varPassword);
        this.image = new SimpleStringProperty(varImage);
    }

    
    public int getID(){
        return id.get();
    }
    
    public String getUsername(){
        return username.get();
    }
    
    public String getPassword(){
        return password.get();
    }
    
    public String getImage(){
        return image.get();
    }
    
    public void setID(int varID){
        id.set(varID);
    }
    
    public void setUsername(String varUsername){
        username.set(varUsername);
    }
    
    public void setPassword(String varPassword){
        password.set(varPassword);
    }
    
    public void setImage(String varImage){
       image.set(varImage);
    }
}