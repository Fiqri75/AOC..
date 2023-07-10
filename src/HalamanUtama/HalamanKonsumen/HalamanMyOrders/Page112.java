package HalamanUtama.HalamanKonsumen.HalamanMyOrders;

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

import HalamanUtama.HalamanProdusen.Input.DataInput;
import Model.Konsumen;
import Model.Produsen;
import Model.StockMakanan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class Page112 implements Initializable {
    ArrayList<Konsumen> consumen = new ArrayList<>();
    Konsumen konsumenLogin;
    ArrayList<StockMakanan> stockMakanan = new ArrayList<>();
    XStream xST = new XStream(new StaxDriver());


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

    void bukaXMLKonsumen(){
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
                    konsumenLogin = consumen.get(i);
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
    private TableView History;

    @FXML
    private TextField buatID;

    @FXML
    private TextField buatRestoran;

    @FXML
    private TextField buatMakanan;

    @FXML
    private TextField buatKadaluwarsa;

    @FXML
    private TextField buatStock;

    @FXML
    private TableColumn sgNamaID;

    @FXML
    private TableColumn sgNamaRestoran;

    @FXML
    private TableColumn sgNamaMakanan;

    @FXML
    private TableColumn sgMasaKadaluwarsa;

    @FXML
    private TableColumn sgJumlahStock;

    @FXML
    private TableColumn sgTakeStatus;



        public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HalamanUtama/HalamanKonsumen/HalamanKonsumen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bukaXML();
        bukaXMLKonsumen();
        sgNamaID.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("NamaIDMakanan"));
        sgNamaRestoran.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("NamaID"));
        sgNamaMakanan.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("NamaMakanan"));
        sgMasaKadaluwarsa.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("MasaKadaluwarsa"));
        sgJumlahStock.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("JumlahStock"));
        sgTakeStatus.setCellValueFactory(new PropertyValueFactory<DataMyOrders, String>("TakeStatus"));
            for(int i = 0; i < stockMakanan.size(); i++){
            StockMakanan sm = (StockMakanan) stockMakanan.get(i);
            if (sm.getverifikasi() == 1 && (sm.getambil() == 1 || sm.getambil() == 2) && sm.getEmailKonsumen().equalsIgnoreCase(konsumenLogin.getEmail())) {
                DataInput di = new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock());
                di.setTakeStatus(sm.getAmbilTranslate());
                data.add(di);
            }
         }
        History.setItems(data);
        
        
    }  
   
    
}

