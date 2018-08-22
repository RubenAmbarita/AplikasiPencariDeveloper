package metrodata.mii.aplikasideveloper.Fragmen;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import metrodata.mii.aplikasideveloper.ActivityUI.ChatActivity;
import metrodata.mii.aplikasideveloper.Adapter.AdapterChat;
import metrodata.mii.aplikasideveloper.Adapter.AdapterMessage;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.getKontak.DataAdminItem;
import metrodata.mii.aplikasideveloper.Model.m.register.getKontak.ResponseKontak;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.MessageItem;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.ResponsePesan;
import metrodata.mii.aplikasideveloper.Model.m.register.insertChat.ResponseInsert;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {



    public ChatFragment() {
        // Required empty public constructor
    }

    RecyclerView rvPesan;
    Button btnSend;
    EditText etMessage;
    RefreshLayout swipeMain;
    List<DataAdminItem> list;
    AdapterChat adapterChat;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        rvPesan = (RecyclerView)view.findViewById(R.id.rv_pesan);
//        btnSend =(Button)view.findViewById(R.id.btn_send);
//        etMessage = (EditText)view.findViewById(R.id.edt_message);
        swipeMain=(RefreshLayout)view.findViewById(R.id.swipe_main) ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        //rvPesan.setLayoutManager(linearLayoutManager);
        rvPesan.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        list =new ArrayList<>();
        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        id = user.get(SessionManager.kunci_id);
        showData();

//        getMessage();
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertMessage();
//            }
//        });
//
//        swipeMain.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                getMessage();
//                refreshLayout.finishRefresh(2000);
//            }
//        });
//        swipeMain.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                getMessage();
//                refreshLayout.finishLoadMore(2000);
//            }
//        });
        return view;
    }

    private void showData() {
        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponseKontak> call = apiServices.getKontak(id);
        call.enqueue(new Callback<ResponseKontak>() {
            @Override
            public void onResponse(Call<ResponseKontak> call, Response<ResponseKontak> response) {
                if(response.isSuccessful()==true){
                    list = response.body().getDataAdmin();
                    adapterChat = new AdapterChat(getActivity(),list);
                    rvPesan.setAdapter(adapterChat);
                }else{
                    Toast.makeText(getActivity(), "failed to put data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKontak> call, Throwable t) {
                Toast.makeText(getActivity(), "Network  error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void insertMessage() {
//        String etBodyPesan = etMessage.getText().toString();
//        if(etBodyPesan.isEmpty()){
//            Toast.makeText(getActivity(), "Data can't be empty.", Toast.LENGTH_SHORT).show();
//        }else{
//            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Send Message");
//            progressDialog.show();
//
//            ApiServices apiServices = InitRetrofit.getInstance();
//            Call<ResponseInsert> call = apiServices.insertMessage(etBodyPesan,"2","1","2");
//            call.enqueue(new Callback<ResponseInsert>() {
//                @Override
//                public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
//                    if(response.body().isStatus()==true){
//                        Toast.makeText(getActivity(), "Sending Message", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        etMessage.setText("");
//                    }else{
//                        Toast.makeText(getActivity(), "Failed sending message.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseInsert> call, Throwable t) {
//                    Toast.makeText(getActivity(), "Newtwork Error.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//    }

//    private void getMessage() {
//        ApiServices apiServices = InitRetrofit.getInstance();
//        Call<ResponsePesan> call = apiServices.tampilPesan("2","1","1","2");
//        call.enqueue(new Callback<ResponsePesan>() {
//            @Override
//            public void onResponse(Call<ResponsePesan> call, Response<ResponsePesan> response) {
//
//                if(response.body().isStatus()==true){
//                    Log.e("Tag","Hasil pesan :"+response.body().getMsg());
//                    List<MessageItem> hasilPesan = response.body().getMessage();
//                    AdapterMessage adapterMessage = new AdapterMessage(hasilPesan,getActivity());
//                    rvPesan.setAdapter(adapterMessage);
//                }else{
//                    Toast.makeText(getActivity(), "Failed to put data.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePesan> call, Throwable t) {
//                Toast.makeText(getActivity(), "Network Error.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}
