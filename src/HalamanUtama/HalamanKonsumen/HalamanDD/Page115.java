package HalamanUtama.HalamanKonsumen.HalamanDD;

import static javafx.collections.FXCollections.observableArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import HalamanUtama.HalamanLSM.HalamanVerifikasi.DataVerifikasi;
import HalamanUtama.HalamanProdusen.Input.DataInput;
import Model.Konsumen;
import Model.Produsen;
import Model.StockMakanan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Page115 implements Initializable{

    ArrayList<StockMakanan> stockMakanan = new ArrayList<>();
    XStream xST = new XStream(new StaxDriver());
    ArrayList<Konsumen> consumens = new ArrayList<>();
    Konsumen konsumenLogin;
    private String namaRestoran = "Dunkin Donuts Ramai Mall";

    void bukaXML(){
        FileInputStream input = null;
        xST.addPermission(AnyTypePermission.ANY);
        xST.allowTypesByWildcard(new String[]{"Model.StockMakanan"});
        try {
            input = new FileInputStream("StockMakanan.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya = "";
            while ((isi = input.read()) != -1){
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            stockMakanan = (ArrayList<StockMakanan>) xST.fromXML(stringnya);
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
            consumens = (ArrayList<Konsumen>) xST.fromXML(stringnya);
            for (int i = 0; i < consumens.size(); i++) {
               if (consumens.get(i).getpilih() == true) {
                    konsumenLogin = consumens.get(i);
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

     ObservableList data = observableArrayList(
    // new DataVerifikasi (null, null, null, null)
    );

    @FXML
    private AnchorPane utamaPane;

    @FXML
    private Label restaurant;

    @FXML
    private TableView History;

    @FXML
    private TextField ambil;

    @FXML
    private TableColumn sgNamaID;

    @FXML
    private TableColumn sgNamaMakanan;

    @FXML
    private TableColumn sgMasaKadaluwarsa;

    @FXML
    private TableColumn sgJumlahStock;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private void backRestaurant(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HalamanUtama/HalamanKonsumen/HalamanKonsumen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tombolAmbil(ActionEvent event) {
        for(int i = 0; i < stockMakanan.size(); i++){
            if (ambil.getText().equals(stockMakanan.get(i).getIDMakanan())) {
                stockMakanan.get(i).setambil(1);
                stockMakanan.get(i).setEmailKonsumen(konsumenLogin.getEmail());
                String xml = xST.toXML(stockMakanan);
                FileOutputStream output = null;
                try{
                    output = new FileOutputStream("StockMakanan.xml");
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
                break;
            } 
        }

            data.clear();
           for(int i = 0; i < stockMakanan.size(); i++){
                StockMakanan sm = (StockMakanan) stockMakanan.get(i);
                if (sm.getambil() == 0 && sm.getverifikasi() == 1 && namaRestoran.equalsIgnoreCase(sm.getNamaRestoran())) {
                    data.add(new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock()));
                }
            }
            History.setItems(data);
            History.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bukaXML();
        bukaXMLConsumen();
        sgNamaID.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("NamaIDMakanan"));
        sgNamaMakanan.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("NamaMakanan"));
        sgMasaKadaluwarsa.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("MasaKadaluwarsa"));
        sgJumlahStock.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("JumlahStock"));
            for(int i = 0; i < stockMakanan.size(); i++){
            StockMakanan sm = (StockMakanan) stockMakanan.get(i);
            if (sm.getverifikasi() == 1 && sm.getambil() == 0 && namaRestoran.equalsIgnoreCase(sm.getNamaRestoran())) {
                    data.add(new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock()));
               
            }
         }
        History.setItems(data);
        
}
}