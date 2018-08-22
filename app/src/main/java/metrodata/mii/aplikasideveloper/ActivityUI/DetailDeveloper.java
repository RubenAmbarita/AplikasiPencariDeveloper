package metrodata.mii.aplikasideveloper.ActivityUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import metrodata.mii.aplikasideveloper.Api.ApiServices;
import metrodata.mii.aplikasideveloper.Api.InitRetrofit;
import metrodata.mii.aplikasideveloper.Helper.HelpValidation;
import metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop.DataDevItem;
import metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop.ResponseStatus;
import metrodata.mii.aplikasideveloper.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDeveloper extends AppCompatActivity {

    AlertDialog.Builder dialog;
    TextView gajiPokok1, gajiPokok2, status1, tunjangan, hasilCOGS;
    Spinner spinner1;
    EditText namaTunjangan, hargaTunjangan;
    Button btnPlus, btnHitung,btnQuotation,btnRequest;
    LayoutInflater inflater;
    View dialogView;
    List<DataDevItem> hasilData;
    int i;

    String valueSpinner;
    String finalNilaiGajiPokok, finalNamaTunjangan, finalHargaTunjangan, finalStatusLpatop, finalHasilHitung;

    String valueFactor, valueFactor2, namaFactor;

    @BindView(R.id.tv_namaDev)
    TextView nama;
    @BindView(R.id.tv_posisiDev)
    TextView posisi;
    @BindView(R.id.tv_statusDev)
    TextView status;
    @BindView(R.id.tv_alamatDev)
    TextView alamat;
    @BindView(R.id.tv_keahlianDev)
    TextView keahlian;

    Button btn_cogs;
    String namaTerima, posisiTerima, alamatTerima, statusTerima, keahlianTerima, hrdSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_developer);
        ButterKnife.bind(this);

        hasilData = new ArrayList<>();
        hasilCOGS = (TextView) findViewById(R.id.tv_cogs);
        btnQuotation = (Button)findViewById(R.id.btn_quotation);
        btnRequest = (Button)findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
        getData();

        btn_cogs = (Button) findViewById(R.id.btn_cogs);
        btn_cogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DialogForm();

//                Intent intent = new Intent(DetailDeveloper.this,CogsActivity.class);
//                intent.putExtra("hrd_salary",hrdSalary);
//                startActivity(intent);
            }
        });
        addData();
        kalKulasi();
    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(DetailDeveloper.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.activity_cogs, null);
        dialog.setView(dialogView);

        dialog.setIcon(R.drawable.logo_mii);
        dialog.setTitle(Html.fromHtml("<font color='#3F51B5'>Form COGS</font>"));

        gajiPokok1 = (TextView) dialogView.findViewById(R.id.tv_gaji);
        gajiPokok2 = (TextView) dialogView.findViewById(R.id.tv_gajiPokok);
        tunjangan = (TextView) dialogView.findViewById(R.id.tunjangan);
        status1 = (TextView) dialogView.findViewById(R.id.status);
        spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);
        namaTunjangan = (EditText) dialogView.findViewById(R.id.et_namaTunjangan);
        hargaTunjangan = (EditText) dialogView.findViewById(R.id.et_hargaTunjangan);

        btnPlus = (Button) dialogView.findViewById(R.id.btn_plus);
        btnHitung = (Button) dialogView.findViewById(R.id.btnHitung);

        final AlertDialog show = dialog.show();
        //set data
        gajiPokok2.setText(hrdSalary);
        //set spinner
        getNamaFactor();

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finalNilaiGajiPokok = gajiPokok2.getText().toString();
                finalHargaTunjangan = hargaTunjangan.getText().toString();
                finalNamaTunjangan = namaTunjangan.getText().toString();

                if (finalHargaTunjangan.isEmpty()){
                    hargaTunjangan.setError("Data tidak bisa kosong");
                    Toast.makeText(DetailDeveloper.this, "Data tidak bisa kosong", Toast.LENGTH_SHORT).show();
                }

                else {
                    final String a = valueFactor;
                    final String b = valueFactor2;

                    valueSpinner = (String) spinner1.getSelectedItem();
                    Log.e("Tag", "Hasil" + valueSpinner);
                    //if(hasilData.get(i).getNamaHrFactor().equals(valueSpinner)){

                        Double gajiPokok = Double.valueOf(finalNilaiGajiPokok);
                        Double factor = Double.valueOf(a);
                        Double factor2 = Double.valueOf(b);
                        Double tunjangan;
                        Double hasilJlh;
                        tunjangan = Double.valueOf(finalHargaTunjangan);
                        hasilJlh = gajiPokok + tunjangan;
                        Log.e("Hasil","Hasil :"+hasilJlh);
                        int hasilTambah;


                    if (hasilData.get(0).getNamaHrFactor().equals(valueSpinner) && finalHargaTunjangan != null) {
                           hasilTambah = (int) (factor * gajiPokok);
                        Log.e("Hasil","Hasil kali:"+hasilTambah);
                            finalStatusLpatop = valueSpinner;

                        int Hasil = (int) (hasilTambah+ hasilJlh);
                            Log.e("Tag", "Hasil :" + Hasil);
                            hasilCOGS.setText("Rp " + Hasil + " / Month");

                        } else if (hasilData.get(1).getNamaHrFactor().equals(valueSpinner) && finalHargaTunjangan != null) {
                            hasilTambah = (int) (factor2 * gajiPokok);
                        Log.e("Hasil","Hasil kali:"+hasilTambah);

                           int Hasil = (int) (hasilTambah+ hasilJlh);
                            Log.e("Tag", "Hasil :" + Hasil);
                           hasilCOGS.setText("Rp " + Hasil + " / Month");
                        finalStatusLpatop = valueSpinner;

                        }

                        kalKulasi();
                        show.dismiss();

                    }


                }






        });

    }

    private void kalKulasi() {

        final String gajiPokokFinal = finalNilaiGajiPokok;
        Log.e("Tag","gajipokofinal"+gajiPokokFinal);
        String statusLaptop = finalStatusLpatop;
        String namaTunjangan = finalNamaTunjangan;
        String hasilGoksFinal = hasilCOGS.getText().toString();
        String namaFinal = namaTerima;
        String posisiFinal = posisiTerima;
        final String data ="Gaji Pokok : " +gajiPokokFinal + "Status Laptop : " +statusLaptop + "Nama Tunjangan : " +namaTunjangan + " Hasil :" +hasilGoksFinal + "Nama : "+namaFinal + "Posisi :"+posisiFinal;

        btnQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gajiPokokFinal!=null){
                    Intent kirim = new Intent(Intent.ACTION_SEND);
                    kirim.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                    kirim.putExtra(Intent.EXTRA_SUBJECT,"");
                    kirim.putExtra(Intent.EXTRA_TEXT,data);
                    kirim.setType("message/rfc822");
                    startActivity(Intent.createChooser(kirim,"silahkan pilih email client"));
                }else{
                    Toast.makeText(DetailDeveloper.this, "Data Masih kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void addData() {
        Intent terima = getIntent();
        namaTerima = terima.getStringExtra("NAMA");
        posisiTerima = terima.getStringExtra("POSISI");
        alamatTerima = terima.getStringExtra("ALAMAT");
        statusTerima = terima.getStringExtra("STATUS");
        keahlianTerima = terima.getStringExtra("KEAHLIAN");
        hrdSalary = terima.getStringExtra("HRD_SALARY");

        nama.setText(namaTerima);
        posisi.setText(posisiTerima);
        status.setText(statusTerima);
        alamat.setText(alamatTerima);
        keahlian.setText(keahlianTerima);
    }


    private void getData() {
        ApiServices apiServices = InitRetrofit.getInstance();
        Call<ResponseStatus> call = apiServices.tampilStatus(namaFactor, valueFactor);
        call.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(Call<ResponseStatus> call, Response<ResponseStatus> response) {
                if (response.body().isStatus() == true) {
                    namaFactor = response.body().getDataDev().get(0).getNamaHrFactor();
                    //  namaFactor = response.body().getDataDev().get(1).getNamaHrFactor();
                    valueFactor = response.body().getDataDev().get(0).getValueHrFactor();
                    valueFactor2 = response.body().getDataDev().get(1).getValueHrFactor();
                    hasilData = response.body().getDataDev();
                } else {
                    Toast.makeText(DetailDeveloper.this, "Gagal Request Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {
                Toast.makeText(DetailDeveloper.this, "Gagal Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNamaFactor() {
        List<String> dataSpinner = new ArrayList<String>();
        for (i = 0; i < hasilData.size(); i++) {

            //dapat nilai namafactor
            dataSpinner.add(hasilData.get(i).getNamaHrFactor());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataSpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter);
        }
    }
}
