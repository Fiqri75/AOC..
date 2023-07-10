package Model;

public class Restoran {
    private String nama;
    private String id;
 
    public Restoran(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }
 
    public String getNama() {
        return nama;
    }
 
    public void setNama(String nama) {
        this.nama = nama;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
}

