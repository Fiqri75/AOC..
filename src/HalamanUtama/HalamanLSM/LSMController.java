package HalamanUtama.HalamanLSM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LSMController implements Initializable {

    @FXML
    private AnchorPane utamaPane;

    @FXML
    private VBox leftPane;

    @FXML
    private Button verifikasi;

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
    private void keVerifikasi() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanLSM/HalamanVerifikasi/Verifikasi.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }
}

    @FXML
    private void keHistory() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanLSM/HalamanHistory/History.fxml"));
        Parent newContent = loader.load();
        utamaPane.getChildren().setAll(newContent);
    } catch (IOException e) {
        e.printStackTrace();
       
    }
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
    }
}

