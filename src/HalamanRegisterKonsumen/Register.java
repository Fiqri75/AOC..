package HalamanRegisterKonsumen;

import Model.Konsumen;

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
    ArrayList<Konsumen> consumen = new ArrayList<>();
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
    private Label konsumen;

    @FXML
    private Button button1;

    @FXML
    private TextField social;

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

        for(int i = 0; i < consumen.size(); i++){
            Konsumen data = (Konsumen) consumen.get(i);

            if(data.getEmail().equals(email.getText())){
                notifikasi.setText("Email telah digunakan!");
                adaemail = true;
                break;
            }
        }

        if(!adaemail){
            consumen.add(new Konsumen(social.getText(), username.getText(), email.getText(), address.getText(), password.getText(), false));

            String xml = xST.toXML(consumen);
                        FileOutputStream output = null;
                        try{
                            output = new FileOutputStream("DaftarAkunKonsumen.xml");
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
    }    



}