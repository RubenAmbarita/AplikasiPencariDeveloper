package metrodata.mii.aplikasideveloper.Model.m.register.getKontak;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseKontak{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data_admin")
	private List<DataAdminItem> dataAdmin;

	@SerializedName("status")
	private boolean status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setDataAdmin(List<DataAdminItem> dataAdmin){
		this.dataAdmin = dataAdmin;
	}

	public List<DataAdminItem> getDataAdmin(){
		return dataAdmin;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKontak{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data_admin = '" + dataAdmin + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}