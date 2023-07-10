package HalamanUtama.HalamanLSM.HalamanVerifikasi;

public class DataVerifikasi {
    String NamaID;
    String NamaRestoran;
    String NamaMakanan;
    String MasaKadaluwarsa;
    String JumlahStock;
    String Status;

    public DataVerifikasi (String x, String y, String z, String i, String j){
        this.NamaID = x;
        this.NamaRestoran = y;
        this.NamaMakanan = z;
        this.MasaKadaluwarsa = i;
        this.JumlahStock = j;
    }

    public String getNamaID(){
        return NamaID;
    }

    public String getNamaRestoran(){
        return NamaID;
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
}
