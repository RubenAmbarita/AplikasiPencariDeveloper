package metrodata.mii.aplikasideveloper.Model.m.register.m.login;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private int code;

    @SerializedName("email")
    private List<EmailItem> email;

    @SerializedName("status")
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setEmail(List<EmailItem> email) {
        this.email = email;
    }

    public List<EmailItem> getEmail() {
        return email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "ResponseLogin{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}