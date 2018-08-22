package metrodata.mii.aplikasideveloper.Fragmen;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import metrodata.mii.aplikasideveloper.ActivityUI.RegisterActivity;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Helper.HelpFunction;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.user.ResponseUserProfile;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    Context context;
    SessionManager sessionManager;
    HelpFunction helpFunction;
    String userProfile;
    CircularImageView imageView;
    TextView nama, email, noHp, tgl;
    FloatingActionButton button;
    String obj_email, obj_nama, obj_noHP, obj_tgl, idUser;
    SwipeRefreshLayout swipeRefreshLayout;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_profil);
        imageView = view.findViewById(R.id.iv_profil);
        nama = view.findViewById(R.id.tv_nama);
        email = view.findViewById(R.id.tv_email);
        noHp = view.findViewById(R.id.tv_noHp);
        tgl = view.findViewById(R.id.tv_tgl);
        button = view.findViewById(R.id.fab);
        //getUser().get(0).getFullname()
        obj_email = email.getText().toString();
        obj_nama = nama.getText().toString();
        obj_noHP = noHp.getText().toString();
        obj_tgl = tgl.getText().toString();

        helpFunction = new HelpFunction();
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        userProfile = user.get(SessionManager.kunci_email);
        profil();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                profil();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(getActivity(), RegisterActivity.class);
                send.putExtra("NAMA", obj_nama);
                send.putExtra("EMAIL", obj_email);
                send.putExtra("HP", obj_noHP);
                send.putExtra("TGL", obj_tgl);
                send.putExtra("ID", idUser);

                getActivity().startActivity(send);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                getActivity().startActivityForResult(galery,0);
            }
        });


//        Log.e("Tag","get userprofile  :"+userProfile);

        return view;
    }

    private void profil() {
        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseUserProfile> call = api.requestProfil(userProfile);
        call.enqueue(new Callback<ResponseUserProfile>() {
            @Override
            public void onResponse(Call<ResponseUserProfile> call, Response<ResponseUserProfile> response) {
                if (response.body().isStatus() == true) {
//                    Log.e("Tag","Fulname :" +response.body().isStatus() );
                    //  Log.e("Tag","hasil user " +new Gson().toJson(response.body().getUser()));

                   swipeRefreshLayout.setRefreshing(false);
                    nama.setText(response.body().getUser().get(0).getFullname());
                    email.setText(response.body().getUser().get(0).getEmail());
                    noHp.setText(response.body().getUser().get(0).getNoHp());
                    tgl.setText(response.body().getUser().get(0).getTglLahir());

                    obj_email = response.body().getUser().get(0).getEmail();
                    obj_nama = response.body().getUser().get(0).getFullname();
                    obj_noHP = response.body().getUser().get(0).getNoHp();
                    obj_tgl = response.body().getUser().get(0).getTglLahir();
                    idUser = response.body().getUser().get(0).getIdUser();
                } else {
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserProfile> call, Throwable t) {
                Toast.makeText(getActivity(), "Jaringan anda error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
