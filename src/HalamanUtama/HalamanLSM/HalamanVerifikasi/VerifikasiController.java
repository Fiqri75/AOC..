package HalamanUtama.HalamanLSM.HalamanVerifikasi;

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


public class VerifikasiController implements Initializable {
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
    private void tombolKebenaran(ActionEvent event) {
        for(int i = 0; i < stockMakanan.size(); i++){
            if (buatID.getText().equals(stockMakanan.get(i).getIDMakanan())) {
                stockMakanan.get(i).setverifikasi(1);
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
                if (sm.getverifikasi() == 0) {
                    data.add(new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock()));
                }
            }
            History.setItems(data);
            History.refresh();
    }

    @FXML
    private void tombolKehapusan(ActionEvent event) {
        for(int i = 0; i < stockMakanan.size(); i++){
            if (buatID.getText().equals(stockMakanan.get(i).getIDMakanan())) {
                stockMakanan.get(i).setverifikasi(2);
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
                if (sm.getverifikasi() == 0) {
                    data.add(new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock()));
                }
            }
            History.setItems(data);
            History.refresh();
    }

        public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HalamanUtama/HalamanLSM/HalamanLSM.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bukaXML();
        sgNamaID.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("NamaIDMakanan"));
        sgNamaRestoran.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("NamaID"));
        sgNamaMakanan.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("NamaMakanan"));
        sgMasaKadaluwarsa.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("MasaKadaluwarsa"));
        sgJumlahStock.setCellValueFactory(new PropertyValueFactory<DataVerifikasi, String>("JumlahStock"));
            for(int i = 0; i < stockMakanan.size(); i++){
            StockMakanan sm = (StockMakanan) stockMakanan.get(i);
            if (sm.getverifikasi() == 0) {
                data.add(new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock()));
            }
         }
        History.setItems(data);
        
        
    }  
   
    
}

