package metrodata.mii.aplikasideveloper.ActivityUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Fragmen.HomeFragment;
import metrodata.mii.aplikasideveloper.Fragmen.ProfilFragment;
import metrodata.mii.aplikasideveloper.Helper.HelpFunction;
import metrodata.mii.aplikasideveloper.Helper.HelpValidation;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.Register.ResponseUser;
import metrodata.mii.aplikasideveloper.Model.m.register.editProfil.ResponseEdit;
import metrodata.mii.aplikasideveloper.Model.m.register.user.ResponseUserProfile;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends HelpFunction {

    @BindView(R.id.et_nama)
    EditText obj_nama;
    @BindView(R.id.et_email)
    EditText obj_email;
    @BindView(R.id.et_hp)
    EditText obj_no;
    @BindView(R.id.et_tgl)
    EditText obj_tgl;
    @BindView(R.id.et_pass)
    EditText obj_pass;
    @BindView(R.id.et_repass)
    EditText obj_repass;
    @BindView(R.id.bt_register)
    Button obj_register;
    @BindView(R.id.bt_edit)
    Button obj_edit;
    HelpValidation helpValidation;

    String getEmail, getEmail2, getFullname, getNo_hp, getTgl_lahir, fullname, email, no_hp, tgl_lahir, password, repassword;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        helpValidation = new HelpValidation(this);
        getEmail2 = getIntent().getStringExtra("EMAIL");
        if (getEmail2.equals("")) {
            obj_pass.setVisibility(View.VISIBLE);
            obj_repass.setVisibility(View.VISIBLE);
            obj_register.setVisibility(View.VISIBLE);
        } else {
            Intent terima = getIntent();
            getEmail = terima.getStringExtra("EMAIL");
            getFullname = terima.getStringExtra("NAMA");
            getNo_hp = terima.getStringExtra("HP");
            getTgl_lahir = terima.getStringExtra("TGL");
            id_user = terima.getStringExtra("ID");

            obj_pass.setVisibility(View.GONE);
            obj_repass.setVisibility(View.GONE);
            obj_edit.setVisibility(View.VISIBLE);
            obj_register.setVisibility(View.GONE);

            obj_nama.setText(getFullname);
            obj_email.setText(getEmail);
            obj_tgl.setText(getTgl_lahir);
            obj_no.setText(getNo_hp);
        }
    }

    @OnClick({R.id.bt_register, R.id.bt_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                register();
                break;

            case R.id.bt_edit:
                Edit();
                break;
        }
    }

    private void Edit() {

        fullname = obj_nama.getText().toString();
        email = obj_email.getText().toString();
        no_hp = obj_no.getText().toString();
        tgl_lahir = obj_tgl.getText().toString();

        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponseEdit> call = apiServices.requestEdit(id_user, fullname, no_hp, tgl_lahir);
        call.enqueue(new Callback<ResponseEdit>() {
            @Override
            public void onResponse(Call<ResponseEdit> call, Response<ResponseEdit> response) {
//                Log.e("Tag","hasil edit "+response.body().getKode());
                if (response.body().getKode() == 1) {
                    simpleToast("Data berhasil update!");
                    obj_nama.setText("");
                    obj_email.setText("");
                    obj_no.setText("");
                    obj_tgl.setText("");
                    finish();
                } else {
                    simpleToast("data gagal update!");
                }
            }

            @Override
            public void onFailure(Call<ResponseEdit> call, Throwable t) {
                simpleToast("Jaringan Error!" + t.getMessage());
            }
        });
    }


    private void register() {
        fullname = obj_nama.getText().toString();
        email = obj_email.getText().toString();
        no_hp = obj_no.getText().toString();
        tgl_lahir = obj_tgl.getText().toString();
        password = obj_pass.getText().toString();
        repassword = obj_repass.getText().toString();

        if (!helpValidation.isEmptyField(fullname)) {
            obj_nama.setError("Field Nama harus diisi!");
        } else if (!helpValidation.isEmptyField(email)) {
            obj_email.setError("Field Email harus diisi!");
        } else if (!helpValidation.isValidateEmail(email)) {
            obj_email.setError("Format email salah!");
        } else if (!helpValidation.isEmptyField(no_hp)) {
            obj_no.setError("Field NoHP harus diisi!");
        } else if (!helpValidation.isEmptyField(tgl_lahir)) {
            obj_tgl.setError("Field Tanggal harus diisi!");
        } else if (!helpValidation.isEmptyField(password)) {
            obj_pass.setError("Field Password harus diisi!");
        } else if (!helpValidation.isEmptyField(repassword)) {
            obj_repass.setError("Field Re-password harus diisi!");
        } else if (!helpValidation.isMatch(password, repassword)) {
            obj_repass.setError("Password harus sama!");
        } else {

            ApiServices api = InitRetrofit.getInstance();
            Call<ResponseUser> call = api.requestRegister(fullname, email, no_hp, tgl_lahir, password);
            call.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    boolean status = response.body().isStatus();
                    if (status == true) {
                      /*  String id = response.body().getEmail().get(0).getIdUser();
                        Log.e("tag","hasil id : "+id);


                        new SessionManager(getApplicationContext()).createSession(email);*/
                        simpleToast("Berhasil!" + response.body().getMsg());
                        finish();
                        simpleIntent(LoginActivity.class);
                    } else {
                        simpleToast("gagal login!" + response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    simpleToast("jaringan Error!");
                }
            });
        }
    }
}

