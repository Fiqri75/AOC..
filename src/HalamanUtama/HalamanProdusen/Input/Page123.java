package HalamanUtama.HalamanProdusen.Input;

import static javafx.collections.FXCollections.observableArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Model.Konsumen;
import Model.Produsen;
import Model.Restoran;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Page123 implements Initializable {
    ArrayList<StockMakanan> stockMakanan = new ArrayList<>();
    ArrayList<Restoran> daftarrestoran = new ArrayList<>();
    ArrayList<Produsen> producen = new ArrayList<>();
    Produsen producenLogin;
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
            daftarrestoran = (ArrayList<Restoran>) xST.fromXML(stringnya);
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
    
    ObservableList data = observableArrayList(
    // new DataInput (null, null, null, null)
);

    @FXML
    private Label labelnamarestoran;

    @FXML
    private TableView History;

    @FXML
    private TextField buatID;

    @FXML
    private TextField buatIDMakanan;

    @FXML
    private TextField buatMakanan;

    @FXML
    private TextField buatKadaluwarsa;

    @FXML
    private TextField buatStock;

    @FXML
    private TableColumn sgNamaIDMakanan;

    @FXML
    private TableColumn sgNamaMakanan;

    @FXML
    private TableColumn sgMasaKadaluwarsa;

    @FXML
    private TableColumn sgJumlahStock;

    @FXML
    private TableColumn sgStatus;

    @FXML
    private void tombolKebenaran(ActionEvent event) {
    String makanan =  buatMakanan.getText();
    String kadaluarsa =  buatKadaluwarsa.getText();
    String stock =  buatStock.getText();
    String IDMakanan = generateRandomString(4);

        StockMakanan stockMakan = new StockMakanan(
            IDMakanan.toString(), 
            producenLogin.getNama(), 
            makanan.toString(),
            kadaluarsa.toString(),
            producenLogin.getEmail(),
            "",
            stock.toString(),
            0,
            0
        );

        DataInput di = new DataInput(producenLogin.getNama(), IDMakanan, makanan, kadaluarsa, stock);
        di.setStatus(stockMakan.getverifikasiTranslate());
        data.add(di);
        buatMakanan.setText("");
        buatKadaluwarsa.setText("");
        buatStock.setText("");

        stockMakanan.add(stockMakan);

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

    }

        public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/HalamanUtama/HalamanProdusen/HalamanProdusen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static String generateRandomString(int length) {
        String characters = "0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bukaXML();
        bukaXMLProducen();
        sgNamaIDMakanan.setCellValueFactory(new PropertyValueFactory<DataInput, String>("NamaIDMakanan"));
        sgNamaMakanan.setCellValueFactory(new PropertyValueFactory<DataInput, String>("NamaMakanan"));
        sgMasaKadaluwarsa.setCellValueFactory(new PropertyValueFactory<DataInput, String>("MasaKadaluwarsa"));
        sgJumlahStock.setCellValueFactory(new PropertyValueFactory<DataInput, String>("JumlahStock"));
        sgStatus.setCellValueFactory(new PropertyValueFactory<DataInput, String>("Status"));
        labelnamarestoran.setText(producenLogin.getNama());
         for(int i = 0; i < stockMakanan.size(); i++){
            StockMakanan sm = (StockMakanan) stockMakanan.get(i);
            if (sm.getNamaRestoran() == producenLogin.getNama()) {     
                DataInput di = new DataInput(sm.getNamaRestoran(), sm.getIDMakanan(), sm.getMakanan(), sm.getKadaluwarsa(), sm.getstock());
                di.setStatus(sm.getverifikasiTranslate());
                data.add(di);
            }
         }
        History.setItems(data);
        
        
    }  
   
    
}

