// package HalamanRegisterProdusen;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;

// public class Register {

//     @FXML
//     private AnchorPane utamaPane;

//     @FXML
//     private Label produsen;

//     @FXML
//     private Label krodusen;

//     @FXML
//     private Button button1;

//     @FXML
//     private TextField restoran;

//     @FXML
//     private TextField username;

//     @FXML
//     private TextField email;

//     @FXML
//     private TextField address;

//     @FXML
//     private TextField password;

//     @FXML
//     private void register(ActionEvent event) throws Exception {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanLogin/HalamanLogin.fxml"));
//         Parent root = loader.load();
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     @FXML
//     private void kePilihan(ActionEvent event) throws Exception {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanPilihanRegister/HalamanPilihan.fxml"));
//         Parent root = loader.load();
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }


// }

package HalamanRegisterProdusen;

import Model.Produsen;
import Model.Restoran;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Register implements Initializable{
    ArrayList<Produsen> producen = new ArrayList<>();
    ArrayList<Restoran> restorans = new ArrayList<>();
    XStream xST = new XStream(new StaxDriver());

    void bukaXML(){
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

    
    void bukaXMLDaftarRestoran(){
        FileInputStream input = null;
        xST.addPermission(AnyTypePermission.ANY);
        xST.allowTypesByWildcard(new String[]{"Model.Restoran"});
        try {
            input = new FileInputStream("DaftarRestoran.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya = "";
            while ((isi = input.read()) != -1){
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            restorans = (ArrayList<Restoran>) xST.fromXML(stringnya);
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
    private Label notifikasi;

    @FXML
    private AnchorPane utamaPane;

    @FXML
    private ChoiceBox pilihrestoran;

    @FXML
    private Label produsen;

    @FXML
    private Button button1;

    @FXML
    private TextField restoran;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private PasswordField password;

    @FXML
    private void kePilihan(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanPilihanRegister/HalamanPilihan.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void register(ActionEvent event) throws Exception {
        boolean adaemail = false;

        for(int i = 0; i < producen.size(); i++){
            Produsen data = (Produsen) producen.get(i);

            if(data.getEmail().equals(email.getText())){
                notifikasi.setText("Email telah digunakan!");
                adaemail = true;
                break;
            }
        }

        if(!adaemail){
            producen.add(new Produsen(pilihrestoran.getValue().toString(), username.getText().toString(), email.getText().toString(), address.getText().toString(), password.getText().toString(), false));

            String xml = xST.toXML(producen);
                        FileOutputStream output = null;
                        try{
                            output = new FileOutputStream("DaftarAkunProdusen.xml");
                            byte[] bytes = xml.getBytes("UTF-8");
                            output.write(bytes);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanLogin/HalamanLogin.fxml"));
                            Parent root = loader.load();
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
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
        }        
           
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        bukaXMLDaftarRestoran();
        for (int i = 0; i < restorans.size(); i++) {
            pilihrestoran.getItems().add(i,restorans.get(i).getNama());
        }
        //pilihrestoran.getItems().addAll("J.CO Ambarukmo Plaza", "Dunkin Donuts Ramai Mall", "Roti O'Lempuyangan", "Rotiboy", "McDonald's Jakal", "Burger King Jogja City Mall");
       // pilihrestoran.setValue("J.CO Ambarukmo Plaza");
    }    



}