package metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop;


import com.google.gson.annotations.SerializedName;


public class DataDevItem{

	@SerializedName("value_hr_factor")
	private String valueHrFactor;

	@SerializedName("nama_hr_factor")
	private String namaHrFactor;

	@SerializedName("id")
	private String id;

	public void setValueHrFactor(String valueHrFactor){
		this.valueHrFactor = valueHrFactor;
	}

	public String getValueHrFactor(){
		return valueHrFactor;
	}

	public void setNamaHrFactor(String namaHrFactor){
		this.namaHrFactor = namaHrFactor;
	}

	public String getNamaHrFactor(){
		return namaHrFactor;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DataDevItem{" + 
			"value_hr_factor = '" + valueHrFactor + '\'' + 
			",nama_hr_factor = '" + namaHrFactor + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}