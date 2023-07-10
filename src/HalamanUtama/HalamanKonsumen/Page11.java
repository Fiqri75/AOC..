package HalamanUtama.HalamanKonsumen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Model.Konsumen;

public class Page11 implements Initializable {

    ArrayList<Konsumen> consumen = new ArrayList<>();
    Konsumen consumenLogin;
    XStream xST = new XStream(new StaxDriver());

    void bukaXMLConsumen(){
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
               if (consumen.get(i).getpilih() == true) {
                    consumenLogin = consumen.get(i);
               }
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
    private Label logiName;

    @FXML
    private BorderPane rootPane;
    
    @FXML
    private AnchorPane utamaPane;

    @FXML
    private VBox leftPane;

    @FXML
    private Button restaurantButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button notifikasiButton;

    @FXML
    private void logout(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanLogin/HalamanLogin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void keOrder() {
        try {   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanKonsumen/HalamanMyOrders/Order.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }
}
    

    @FXML
    private void keRestaurant() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanKonsumen/HalamanRestaurant/Restaurant.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }

}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        bukaXMLConsumen();
        logiName.setText(consumenLogin.getUsername());
    }
    
}
