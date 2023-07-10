package HalamanUtama.HalamanLSM.HalamanHistory;

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


public class HistoryController implements Initializable {
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
    private TableColumn sgStatus;

    
        public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HalamanUtama/HalamanLSM/HalamanLSM.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bukaXML();
        sgNamaID.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("NamaIDMakanan"));
        sgNamaRestoran.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("NamaID"));
        sgNamaMakanan.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("NamaMakanan"));
        sgMasaKadaluwarsa.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("MasaKadaluwarsa"));
        sgJumlahStock.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("JumlahStock"));
        sgStatus.setCellValueFactory(new PropertyValueFactory<DataHistory, String>("Status"));
            for(int i = 0; i < stockMakanan.size(); i++){
            StockMakanan sm = (StockMakanan) stockMakanan.get(i);
            if (sm.getverifikasi() != 0){
                DataInput di = new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock());
                di.setStatus(sm.getverifikasiTranslate());
                data.add(di);
            }
        }

        History.setItems(data);
        
        
    }  
   
    
}

