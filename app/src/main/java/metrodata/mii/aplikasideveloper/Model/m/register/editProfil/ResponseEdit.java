package metrodata.mii.aplikasideveloper.Model.m.register.editProfil;

import com.google.gson.annotations.SerializedName;


public class ResponseEdit {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("kode")
    private int kode;

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public int getKode() {
        return kode;
    }

    @Override
    public String toString() {
        return
                "ResponseEdit{" +
                        "pesan = '" + pesan + '\'' +
                        ",kode = '" + kode + '\'' +
                        "}";
    }
}