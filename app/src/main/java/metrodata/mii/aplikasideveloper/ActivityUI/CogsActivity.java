package metrodata.mii.aplikasideveloper.ActivityUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop.DataDevItem;
import metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop.ResponseStatus;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CogsActivity extends AppCompatActivity {

    private Spinner spinner;
    TextView gajiPokok,tunjangan;
    String namaFactor;
    Spinner spinner1;
    String valueFactor;
    String hrdSalary;
    List<DataDevItem> hasilData;
    EditText namaTunjangan,  hargaTunjangan;
    String valueVaktorKeDua;
    Button btnHitung;
    String valueSpinner;
    String finalNilaiGajiPokok, finalNamaTunjangan, finalHargaTunjangan, finalStatusLpatop, finalHasilHitung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cogs);

        hasilData = new ArrayList<>();
          getData();
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        tunjangan = (TextView) findViewById(R.id.tunjangan);
        namaTunjangan = (EditText) findViewById(R.id.et_namaTunjangan);
        hargaTunjangan = (EditText) findViewById(R.id.et_hargaTunjangan);
       // btnHitung = (Button) findViewById(R.id.btnHitung);
//        spinner1.setOnItemSelectedListener(new ItemSelectedListener());
//        spinner1.setAdapter(new ArrayAdapter<String>());
        gajiPokok = (TextView)findViewById(R.id.tv_gajiPokok);
        Intent getIntent = getIntent();
        hrdSalary = getIntent.getStringExtra("hrd_salary");
        gajiPokok.setText(hrdSalary);




        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getPerhitungan();

              /*  int gajiPokok = 50000;
                Double nilaiFaktor = 1.3;
                Double hargaTunjangan = 0.0;
                Double hasilPerhitngan = gajiPokok+ (nilaiFaktor * gajiPokok) + hargaTunjangan;
                Log.e("Hasil ",""+hasilPerhitngan);
*/


            }
        });
    }

    private void getData() {
        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponseStatus> call = apiServices.tampilStatus(namaFactor,valueFactor);
        call.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                if(response.body().isStatus()==true){
                    Log.e("Tag","hasil" + response.body().getDataDev());
                    namaFactor = response.body().getDataDev().get(0).getNamaHrFactor();
                  //  namaFactor = response.body().getDataDev().get(1).getNamaHrFactor();
                    valueFactor = response.body().getDataDev().get(0).getValueHrFactor();
                     valueVaktorKeDua = response.body().getDataDev().get(1).getValueHrFactor();
                    hasilData = response.body().getDataDev();
                    getNamaFactor();


                }else {
                    Toast.makeText(CogsActivity.this, "Gagal Request Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {
                Toast.makeText(CogsActivity.this, "Gagal Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNamaFactor() {
        List<String> dataSpinner =new ArrayList<String>();
        dataSpinner.add("Silahkan Pilih Item");
        for (int i = 0; i < hasilData.size(); i++){
            dataSpinner.add(hasilData.get(i).getNamaHrFactor());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dataSpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter);


        }
    }



    private void getPerhitungan() {
        finalNilaiGajiPokok = gajiPokok.getText().toString();
        finalNamaTunjangan = namaTunjangan.getText().toString();
        finalHargaTunjangan = hargaTunjangan.getText().toString();



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                valueSpinner = (String) spinner1.getSelectedItem();
                Toast.makeText(CogsActivity.this, "hasil Spinner " + valueSpinner, Toast.LENGTH_SHORT).show();
                String getNilaiFaktor = "0";


                if (position == 0) {

                    Toast.makeText(CogsActivity.this, "Silahkan pilih spinner", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    getNilaiFaktor = valueFactor;
                    finalStatusLpatop = valueSpinner;
                    Log.e("Hasil", "text " + finalNilaiGajiPokok + " " + getNilaiFaktor);

                } else {
                    finalStatusLpatop = valueSpinner;
                    getNilaiFaktor = valueVaktorKeDua;
                    Log.e("Hasil", "text " + finalNilaiGajiPokok + " " + getNilaiFaktor);
                }

                int gajiPokok = 9000;// Integer.valueOf(finalNilaiGajiPokok);
                Double nilaiFaktor = Double.valueOf(getNilaiFaktor);
                int hargaTunjangan = Integer.valueOf(finalHargaTunjangan);
                Double hasilPerhitngan;



                if (!finalHargaTunjangan.isEmpty()) {

                    hasilPerhitngan = (gajiPokok + (nilaiFaktor * gajiPokok)) + hargaTunjangan;
                    Log.e("Hasil Perhitungan ", " hasil :" + hasilPerhitngan);

                } else {
                    hargaTunjangan = 0;
                    hasilPerhitngan = gajiPokok + (nilaiFaktor * gajiPokok) + hargaTunjangan;

                    Log.e("Hasil Perhitungan ", " hasil :" + hasilPerhitngan);

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
