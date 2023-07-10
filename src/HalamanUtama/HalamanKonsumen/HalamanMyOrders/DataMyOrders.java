package HalamanUtama.HalamanKonsumen.HalamanMyOrders;

public class DataMyOrders{
    String NamaRestoran;
    String NamaID;
    String NamaMakanan;
    String MasaKadaluwarsa;
    String JumlahStock;
   

    public DataMyOrders(String x, String y, String z, String i, String j){
        this.NamaRestoran = x;
        this.NamaID = y;
        this.NamaMakanan = z;
        this.MasaKadaluwarsa = i;
        this.JumlahStock = j;
       
    }

    public String getNamaRestoran(){
        return NamaRestoran;
    }

    public String getNamaID(){
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
