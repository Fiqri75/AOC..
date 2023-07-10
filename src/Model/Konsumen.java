package Model;

public class Konsumen {
    private String Nama;
    private String Username;
    private String Email;
    private String Address;
    private String Password;
    private boolean pilih;

    public Konsumen(String Nama, String Username, String Email, String Address, String Password, boolean pilih){
    this.Nama = Nama;
    this.Username = Username;
    this.Email = Email;
    this.Address = Address;
    this.Password = Password;
    this.pilih = pilih;
    }

    public void setNama(String Nama){
        this.Nama = Nama;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setpilih(boolean pilih){
        this.pilih = pilih;
    }

    public String getNama(){
        return Nama;
    }

     public String getUsername(){
        return Username;
    }

    public String getEmail(){
        return Email;
    }

    public String getAddress(){
        return Address;
    }

    public String getPassword(){
        return Password;
    }

     public boolean getpilih(){
        return pilih;
    }
}
