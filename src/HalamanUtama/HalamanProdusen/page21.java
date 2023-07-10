package HalamanUtama.HalamanProdusen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Model.Produsen;

public class page21 implements Initializable {

    ArrayList<Produsen> producen = new ArrayList<>();
    Produsen producenLogin;
    XStream xST = new XStream(new StaxDriver());

    void bukaXMLProducen(){
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
               if (producen.get(i).getpilih() == true) {
                    producenLogin = producen.get(i);
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
    private AnchorPane utamaPane;

    @FXML
    private VBox leftPane;

    @FXML
    private Label logiName;

    @FXML
    private Button input;

    @FXML
    private Button history;

    @FXML
    private Button order;

    @FXML
    private Button logout;

    @FXML
    private BorderPane rootPane;

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
    private void keInput() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanProdusen/Input/Input.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }
}


    @FXML
    private void keOrder() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanProdusen/HalamanOrder/Order.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        bukaXMLProducen();
        logiName.setText(producenLogin.getUsername());
    }
}

