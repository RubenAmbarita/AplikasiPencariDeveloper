package metrodata.mii.aplikasideveloper.ActivityUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import metrodata.mii.aplikasideveloper.Adapter.AdapterChat;
import metrodata.mii.aplikasideveloper.Adapter.AdapterMessage;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.getKontak.DataAdminItem;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.MessageItem;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.ResponsePesan;
import metrodata.mii.aplikasideveloper.Model.m.register.insertChat.ResponseInsert;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    RecyclerView rvPesan2;
    Button btnSend;
    EditText etMessage;
    RefreshLayout swipeMain;
    List<MessageItem> list;
    AdapterMessage adapterMessage;
    String id, nama, noHp,idUserSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        //enable button up
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setHomeButtonEnabled(true);


        rvPesan2 = (RecyclerView)findViewById(R.id.rv_pesan2);
        btnSend =(Button)findViewById(R.id.btn_send);
        etMessage = (EditText)findViewById(R.id.edt_message);
        swipeMain=(RefreshLayout)findViewById(R.id.swipe_main) ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        linearLayoutManager.setStackFromEnd(true);

        rvPesan2.setLayoutManager(linearLayoutManager);
     //   rvPesan2.setLayoutManager(new GridLayoutManager(this, 1));

        list =new ArrayList<>();

        getData();

        //get data id userSender
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        idUserSender = user.get(SessionManager.kunci_id);

        getMessage();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMessage();
            }
        });

        swipeMain.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMessage();
                refreshLayout.finishRefresh(2000);
            }
        });
        swipeMain.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getMessage();
                refreshLayout.finishLoadMore(2000);
            }
        });
    }

    private void getData() {


        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        nama = intent.getStringExtra("NAMA");
        noHp = intent.getStringExtra("NOHP");

        getSupportActionBar().setTitle(nama);
    }

    private void insertMessage() {
        String etBodyPesan = etMessage.getText().toString();
        if(etBodyPesan.isEmpty()){
            Toast.makeText(ChatActivity.this, "Data can't be empty.", Toast.LENGTH_SHORT).show();
        }else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Send Message");
            progressDialog.show();

            ApiServices apiServices = InitRetrofit.getInstance();
            Call<ResponseInsert> call = apiServices.insertMessage(etBodyPesan,idUserSender,id,"1");
            call.enqueue(new Callback<ResponseInsert>() {
                @Override
                public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                    if(response.body().isStatus()==true){
                        Toast.makeText(ChatActivity.this, "Sending Message", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        etMessage.setText("");
                    }else{
                        Toast.makeText(ChatActivity.this, "Failed sending message.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseInsert> call, Throwable t) {
                    Toast.makeText(ChatActivity.this, "Newtwork Error.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getMessage() {
        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponsePesan> call = apiServices.tampilPesan(idUserSender,id,id,idUserSender);
        call.enqueue(new Callback<ResponsePesan>() {
            @Override
            public void onResponse(Call<ResponsePesan> call, Response<ResponsePesan> response) {

                if(response.body().isStatus()==true){
                    Log.e("Tag","Hasil pesan :"+response.body().getMsg());
                    List<MessageItem> hasilPesan = response.body().getMessage();
                    AdapterMessage adapterMessage = new AdapterMessage(hasilPesan,ChatActivity.this);
                    rvPesan2.setAdapter(adapterMessage);
                }else{
                    Toast.makeText(ChatActivity.this, "Failed to put data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesan> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Network Error.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
            return super.onOptionsItemSelected(item);

    }
}
