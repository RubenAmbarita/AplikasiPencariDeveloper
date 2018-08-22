package metrodata.mii.aplikasideveloper.Helper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

public class HelpValidation {
    private Context context;

    //konstruktor untuk konteks
    public HelpValidation(Context context) {
        this.context = context;
    }

    //method validasi field kosong atau berisi
    public boolean isEmptyField(String yourField) {
        return !TextUtils.isEmpty(yourField);
    }

    //method untuk validasi email benar atau kosong
    public boolean isValidateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //method untuk validasi password dan repassword
    public boolean isMatch(String password, String retypePassword) {
        return password.equals(retypePassword);
    }
}
