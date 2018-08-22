package metrodata.mii.aplikasideveloper.Model.m.register.getKontak;


import com.google.gson.annotations.SerializedName;


public class DataAdminItem{

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("email")
	private String email;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	@Override
 	public String toString(){
		return 
			"DataAdminItem{" + 
			"no_hp = '" + noHp + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",email = '" + email + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			"}";
		}
}