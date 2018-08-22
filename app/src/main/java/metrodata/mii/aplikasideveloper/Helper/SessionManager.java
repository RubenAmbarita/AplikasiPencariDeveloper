package metrodata.mii.aplikasideveloper.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import metrodata.mii.aplikasideveloper.ActivityUI.HomeActivity;
import metrodata.mii.aplikasideveloper.ActivityUI.LoginActivity;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "developer";
    private static final String is_Login = "islogin";
    public static final String kunci_email = "keyemail";
    public static final String kunci_id = "id";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String email, String id) {
        editor.putBoolean(is_Login, true);
        editor.putString(kunci_email, email);
        editor.putString(kunci_id, id);
        editor.commit();
    }

    private boolean is_login() {
        return pref.getBoolean(is_Login, false);
    }

    public void checkLogin() {
        if (!this.is_login()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent i = new Intent(context, HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(kunci_email, pref.getString(kunci_email, null));
        user.put(kunci_id, pref.getString(kunci_id, null));
        return user;
    }
}
