package HalamanLogin;

import Model.Produsen;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import Model.Konsumen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
    ArrayList<Konsumen> consumen = new ArrayList<>();
    ArrayList<Produsen> producen = new ArrayList<>();
    XStream xST = new XStream(new StaxDriver());

    void bukaXML(){
        FileInputStream input = null;
        xST.addPermission(AnyTypePermission.ANY);
        xST.allowTypesByWildcard(new String[]{"Model.Konsumen"});
        try {
            input = new FileInputStream("DaftarAkunKonsumen.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya = "";
            while ((isi = input.read()) != -1){
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            consumen = (ArrayList<Konsumen>) xST.fromXML(stringnya);
            for (int i = 0; i < consumen.size(); i++) {
                consumen.get(i).setpilih(false);
            }
        }
        catch (Exception e){
            System.err.println("test: " + e.getMessage());
        }
        finally {
            if (input != null){
                try{
                    input.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    void bukaXML2(){
        FileInputStream input = null;
        xST.addPermission(AnyTypePermission.ANY);
        xST.allowTypesByWildcard(new String[]{"Model.Produsen"});
        try {
            input = new FileInputStream("DaftarAkunProdusen.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya = "";
            while ((isi = input.read()) != -1){
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            producen = (ArrayList<Produsen>) xST.fromXML(stringnya);
            for (int i = 0; i < producen.size(); i++) {
                producen.get(i).setpilih(false);
            }
        }
        catch (Exception e){
            System.err.println("test: " + e.getMessage());
        }
        finally {
            if (input != null){
                try{
                    input.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox pilihan;

    @FXML
    private Button loginButton;

    @FXML
    private Button createButton;

    @FXML
    private Runnable nextPageAction;

    @FXML
    private void handleLogin(ActionEvent event) throws Exception {

        if(pilihan.getValue().equals("Producen")){
            for(int i = 0; i < producen.size(); i++){
            Produsen data = (Produsen) producen.get(i);

            if(data.getEmail().equals(emailField.getText()) && passwordField.getText().equals(data.getPassword())){
                data.setpilih(true);

                 String xml = xST.toXML(producen);
                        FileOutputStream output = null;
                        try{
                            output = new FileOutputStream("DaftarAkunProdusen.xml");
                            byte[] bytes = xml.getBytes("UTF-8");
                            output.write(bytes);
                        
                        }
                        catch (Exception e){
                            System.err.println("Perhatian: " + e.getMessage());
                        }
                        finally {
                            if (output != null){
                                try {
                                    output.close();
                                }
                                catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }

            System.out.println("Berhasil Masuk!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanProdusen/HalamanProdusen.fxml"));
            Parent root = loader.load();
        
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
        }
            
        } else if(pilihan.getValue().equals("Consumen")){
            for(int i = 0; i < consumen.size(); i++){
            Konsumen data = (Konsumen) consumen.get(i);

            if(data.getEmail().equals(emailField.getText()) && passwordField.getText().equals(data.getPassword())){
                data.setpilih(true);
                 String xml = xST.toXML(consumen);
                        FileOutputStream output = null;
                        try{
                            output = new FileOutputStream("DaftarAkunKonsumen.xml");
                            byte[] bytes = xml.getBytes("UTF-8");
                            output.write(bytes);
                        
                        }
                        catch (Exception e){
                            System.err.println("Perhatian: " + e.getMessage());
                        }
                        finally {
                            if (output != null){
                                try {
                                    output.close();
                                }
                                catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
            System.out.println("Berhasil Masuk!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanKonsumen/HalamanKonsumen.fxml"));
            Parent root = loader.load();
        
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
        }

        } else if((pilihan.getValue().equals("LSM"))){
            if(validateLogin(emailField.getText().toString(), passwordField.getText().toString())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanLSM/HalamanLSM.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }


       




        // String username = usernameField.getText();
        // String password = passwordField.getText();

        // if (validateLogin(username, password)) {
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanPilihan.fxml"));
        // Parent root = loader.load();
        
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        // System.out.println("username :" + username);
        // System.out.println("password :" + password);
        // } else {
        //     // Menampilkan pesan error jika login gagal

        // }
    }

    @FXML
    private boolean validateLogin(String username, String password) {
        if ((username.equals("admin")&& password.equals("123"))) {
            
            return true; // Contoh sederhana, selalu mengembalikan true
        } else {
            return false;
        }
    }

     @FXML
    private void create(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanPilihanRegister/HalamanPilihan.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        bukaXML2();
        pilihan.getItems().addAll("Producen", "Consumen", "LSM");
       // pilihan.setValue("Producen");
    }    

  
}
