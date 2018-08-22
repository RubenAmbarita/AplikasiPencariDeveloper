package metrodata.mii.aplikasideveloper.ActivityUI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Helper.HelpFunction;
import metrodata.mii.aplikasideveloper.Helper.HelpValidation;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.m.login.ResponseLogin;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends HelpFunction {

    HelpValidation helpValidation;
    String email, password;
    @BindView(R.id.et_sign_email)
    EditText et_email;
    @BindView(R.id.et_sign_pass)
    EditText et_pass;

    @OnClick({R.id.btn_signin, R.id.tv_forget})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin:
                login();
                break;
            case R.id.tv_forget:
                email = et_email.getText().toString();
                forget();
                break;
//            case R.id.tv_register:
//                register();
//                break;
        }
    }

//    private void register() {
//
//        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
//        register.putExtra("EMAIL", "");
//        startActivity(register);
//        finish();
//    }

    private void forget() {
        if (!helpValidation.isEmptyField(email)) {
            et_email.setError("Field Email must be Filled.");
        } else {
            Intent forget = new Intent(LoginActivity.this, RegisterActivity.class);
            forget.putExtra("email", email);
            startActivity(forget);
        }
    }

    private void login() {
        email = et_email.getText().toString();
        password = et_pass.getText().toString();


        if (!helpValidation.isEmptyField(email)) {
            et_email.setError("Field Email must be Filled.");
        } else if (!helpValidation.isValidateEmail(email)) {
            et_email.setError("Must be Email Format.");
        } else if (!helpValidation.isEmptyField(password)) {
            et_pass.setError("Field Password must be Filled.");
        } else {

            ApiServices api = InitRetrofit.getInstance();
            Call<ResponseLogin> call = api.requestLogin(email, password);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        //get id dari response
                        String id = response.body().getEmail().get(0).getIdUser();
                        Log.e("tag","hasil id : "+id);
                        simpleToast("Login Success.");
                        new SessionManager(getApplicationContext()).createSession(email,id);
                        simpleIntent(HomeActivity.class);
                        finish();
                    } else {
                        simpleToast("Login Failed.");
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    simpleToast("Network Error.");
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        helpValidation = new HelpValidation(this);
    }
}
