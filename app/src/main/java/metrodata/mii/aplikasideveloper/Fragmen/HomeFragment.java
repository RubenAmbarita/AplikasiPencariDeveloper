package metrodata.mii.aplikasideveloper.Fragmen;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metrodata.mii.aplikasideveloper.ActivityUI.HomeActivity;
import metrodata.mii.aplikasideveloper.Adapter.AdapterProfil;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Model.m.register.getdata.DataDevItem;
import metrodata.mii.aplikasideveloper.Model.m.register.getdata.ResponseGetData;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor

    }

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterProfil adapterProfil;
    List<DataDevItem> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvItem);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swlayout);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        items =new ArrayList<>();

        showData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
            }
        });

    }

    private void showData() {
        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponseGetData> call = apiServices.requestData();
        call.enqueue(new Callback<ResponseGetData>() {
            @Override
            public void onResponse(Call<ResponseGetData> call, Response<ResponseGetData> response) {
                if (response.isSuccessful() == true) {
                     items = response.body().getDataDev();
//                    Log.e("Tag","item :" + items);
                    adapterProfil = new AdapterProfil(getActivity(), items);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setAdapter(adapterProfil);
                } else {
                    Toast.makeText(getActivity(), "data tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetData> call, Throwable t) {
                Toast.makeText(getActivity(), "Jaringan error!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
         MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    adapterProfil.getFilter().filter(newText);
                    return true;
                }
            });
        }

}
