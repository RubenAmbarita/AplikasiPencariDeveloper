package metrodata.mii.aplikasideveloper.ActivityUI;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toolbar;


import java.util.ArrayList;

import metrodata.mii.aplikasideveloper.Adapter.AdapterProfil;
import metrodata.mii.aplikasideveloper.Fragmen.ChatFragment;
import metrodata.mii.aplikasideveloper.Fragmen.FavoritFragment;
import metrodata.mii.aplikasideveloper.Fragmen.HomeFragment;
import metrodata.mii.aplikasideveloper.Fragmen.NotifikasiFragment;
import metrodata.mii.aplikasideveloper.Fragmen.ProfilFragment;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.getdata.DataDevItem;
import metrodata.mii.aplikasideveloper.R;

public class HomeActivity extends AppCompatActivity {

   AdapterProfil adapterProfil;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        finish();
    }

    //private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //load fragment home secara otomatis
        loadFragment(new HomeFragment());

        //untuk set klik pada navigasi
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_favorit:
                        fragment = new FavoritFragment();
                        break;

                    case R.id.navigation_notifikasi:
                        fragment = new NotifikasiFragment();
                        break;

                    case R.id.navigation_chat:
                        fragment = new ChatFragment();
                        break;

                    case R.id.navigation_profil:
                        fragment = new ProfilFragment();
                        break;
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//    private void search(SearchView searchView) {
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapterProfil.getFilter().filter(newText);
//                return true;
//            }
//        });
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Logout");
        alert.setMessage("Are you sure to logout?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new SessionManager(getApplicationContext()).logout();
                finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setCancelable(false);
        alert.show();
    }

}
