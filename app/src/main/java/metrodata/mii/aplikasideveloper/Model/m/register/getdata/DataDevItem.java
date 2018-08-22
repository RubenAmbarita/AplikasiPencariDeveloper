package metrodata.mii.aplikasideveloper.Model.m.register.getdata;


import com.google.gson.annotations.SerializedName;


public class DataDevItem{

	@SerializedName("Alamat")
	private String alamat;

	@SerializedName("id_daftar_pelamar")
	private String idDaftarPelamar;

	@SerializedName("Nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("nama_status_lamaran")
	private String namaStatusLamaran;

	@SerializedName("hrd_salary")
	private String hrdSalary;

	@SerializedName("nama_keahlian")
	private String namaKeahlian;

	@SerializedName("posisi")
	private String posisi;

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setIdDaftarPelamar(String idDaftarPelamar){
		this.idDaftarPelamar = idDaftarPelamar;
	}

	public String getIdDaftarPelamar(){
		return idDaftarPelamar;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNamaStatusLamaran(String namaStatusLamaran){
		this.namaStatusLamaran = namaStatusLamaran;
	}

	public String getNamaStatusLamaran(){
		return namaStatusLamaran;
	}

	public void setHrdSalary(String hrdSalary){
		this.hrdSalary = hrdSalary;
	}

	public String getHrdSalary(){
		return hrdSalary;
	}

	public void setNamaKeahlian(String namaKeahlian){
		this.namaKeahlian = namaKeahlian;
	}

	public String getNamaKeahlian(){
		return namaKeahlian;
	}

	public void setPosisi(String posisi){
		this.posisi = posisi;
	}

	public String getPosisi(){
		return posisi;
	}

	@Override
 	public String toString(){
		return 
			"DataDevItem{" + 
			"alamat = '" + alamat + '\'' + 
			",id_daftar_pelamar = '" + idDaftarPelamar + '\'' + 
			",nama = '" + nama + '\'' + 
			",foto = '" + foto + '\'' + 
			",nama_status_lamaran = '" + namaStatusLamaran + '\'' + 
			",hrd_salary = '" + hrdSalary + '\'' + 
			",nama_keahlian = '" + namaKeahlian + '\'' + 
			",posisi = '" + posisi + '\'' + 
			"}";
		}
}