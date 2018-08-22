package metrodata.mii.aplikasideveloper.Helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class HelpFunction extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HelpFunction.this;
    }

    //fungsi untuk intent
    public void simpleIntent(Class dest) {
        Intent move = new Intent(context, dest);
        startActivity(move);
    }

    //fungsi untuk toast
    public void simpleToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
