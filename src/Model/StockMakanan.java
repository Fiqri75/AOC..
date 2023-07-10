package Model;

public class StockMakanan {
    private String IDMakanan;
    private String NamaRestoran;
    private String Makanan;
    private String Kadaluwarsa;
    private String EmailProdusen;
    private String EmailKonsumen;
    private String stock;
    private int ambil;
    private int verifikasi;

    public StockMakanan(String IDMakanan, String NamaRestoran, String Makanan, String Kadaluwarsa, String EmailProdusen, String EmailKonsumen, String stock, int verifikasi, int ambil){
        this.IDMakanan = IDMakanan;
        this.NamaRestoran = NamaRestoran;
        this.Makanan = Makanan;
        this.Kadaluwarsa = Kadaluwarsa;
        this.EmailProdusen = EmailProdusen;
        this.EmailKonsumen = EmailKonsumen;
        this.stock = stock;
        this.verifikasi = verifikasi;
        this.ambil = ambil;
    }


    public void setIDMakanan(String IDMakanan){
        this.IDMakanan = IDMakanan;
    }

    public void setNamaRestoran(String NamaRestoran){
        this.NamaRestoran = NamaRestoran;
    }

    public void setMakanan(String Makanan){
        this.Makanan = Makanan;
    }

    public void setKadaluwarsa(String Kadaluwarsa){
        this.Kadaluwarsa = Kadaluwarsa;
    }

    public void setEmailProdusen(String EmailProdusen){
        this.EmailProdusen = EmailProdusen;
    }

    public void setEmailKonsumen(String EmailKonsumen){
        this.EmailKonsumen = EmailKonsumen;
    }

    public void setstock(String stock){
        this.stock = stock;
    }

    public void setverifikasi(int verifikasi){
        this.verifikasi = verifikasi;
    }

     public void setambil(int ambil){
        this.ambil = ambil;
    }

    public String getIDMakanan(){
        return IDMakanan;
    }

    public String getNamaRestoran(){
        return NamaRestoran;
    }

    public String getMakanan(){
        return Makanan;
    }

    public String getKadaluwarsa(){
        return Kadaluwarsa;
    }

    public String getEmailProdusen(){
        return EmailProdusen;
    }

    public String getEmailKonsumen(){
        return EmailKonsumen;
    }

    public String getstock(){
        return stock;
    }

    public int getverifikasi(){
        return verifikasi;
    }

    
    public String getverifikasiTranslate(){
        if (verifikasi == 0) {
            return "Pending";
        } else if (verifikasi == 1){
            return "Approved";
        } else if (verifikasi == 2) {
            return "Rejected";
        }

        return "-";
    }

     public int getambil(){
        return ambil;
    }

       
    public String getAmbilTranslate(){
        if (ambil == 0) {
            return "Pending";
        } else if (ambil == 1){
            return "Pending Take";
        } else if (ambil == 2) {
            return "On Take";
        }

        return "-";
    }

}
