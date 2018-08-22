package metrodata.mii.aplikasideveloper.ActivityUI;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.R;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //mendelay activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new SessionManager(getApplicationContext()).checkLogin();
                finish();
            }
        }, 2000);
    }
}
