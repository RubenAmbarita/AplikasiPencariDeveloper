package metrodata.mii.aplikasideveloper.Model.m.register.Register;

import com.google.gson.annotations.SerializedName;

public class UserItem {

    @SerializedName("access")
    private String access;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("insert_date")
    private String insertDate;

    @SerializedName("last_active_date")
    private String lastActiveDate;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("tgl_lahir")
    private String tglLahir;

    @SerializedName("update_date")
    private String updateDate;

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setLastActiveDate(String lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public String getLastActiveDate() {
        return lastActiveDate;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return
                "UserItem{" +
                        "access = '" + access + '\'' +
                        ",no_hp = '" + noHp + '\'' +
                        ",insert_date = '" + insertDate + '\'' +
                        ",last_active_date = '" + lastActiveDate + '\'' +
                        ",id_user = '" + idUser + '\'' +
                        ",fullname = '" + fullname + '\'' +
                        ",email = '" + email + '\'' +
                        ",tgl_lahir = '" + tglLahir + '\'' +
                        ",update_date = '" + updateDate + '\'' +
                        "}";
    }
}