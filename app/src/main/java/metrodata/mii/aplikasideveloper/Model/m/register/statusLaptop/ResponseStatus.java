package metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseStatus{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data_dev")
	private List<DataDevItem> dataDev;

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

	public void setDataDev(List<DataDevItem> dataDev){
		this.dataDev = dataDev;
	}

	public List<DataDevItem> getDataDev(){
		return dataDev;
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
			"ResponseStatus{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data_dev = '" + dataDev + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}