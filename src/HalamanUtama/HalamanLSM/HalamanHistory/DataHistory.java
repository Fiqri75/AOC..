package HalamanUtama.HalamanLSM.HalamanHistory;

public class DataHistory {
    String NamaID;
    String NamaIDMakanan;
    String NamaMakanan;
    String MasaKadaluwarsa;
    String JumlahStock;
    String Status;
    
    public DataHistory (String x, String y, String z, String i, String j){
        this.NamaID = x;
        this.NamaIDMakanan = y;
        this.NamaMakanan = z;
        this.MasaKadaluwarsa = i;
        this.JumlahStock = j;
       
    }

    public String getNamaID(){
        return NamaID;
    }

    public String getNamaIDMakanan(){
        return NamaIDMakanan;
    }

    public String getNamaMakanan(){
        return NamaMakanan;
    }

    public String getMasaKadaluwarsa(){
        return MasaKadaluwarsa;
    }

    public String getJumlahStock(){
        return JumlahStock;
    }

    public String getStatus(){
        return Status;
    }

    
    public void setStatus(String status){
        this.Status = status;
    }
}
