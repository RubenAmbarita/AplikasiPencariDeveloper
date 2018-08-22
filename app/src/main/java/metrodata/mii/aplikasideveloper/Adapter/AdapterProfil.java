package metrodata.mii.aplikasideveloper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metrodata.mii.aplikasideveloper.ActivityUI.DetailDeveloper;
import metrodata.mii.aplikasideveloper.Fragmen.HomeFragment;
import metrodata.mii.aplikasideveloper.Model.m.register.getdata.DataDevItem;
import metrodata.mii.aplikasideveloper.R;

public class AdapterProfil extends RecyclerView.Adapter<AdapterProfil.MyViewHolder> implements Filterable{

    Context context;
    List<DataDevItem> item;

    public AdapterProfil(Context context, List<DataDevItem> item) {
        this.context = context;
        this.item = item;

    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.developer_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( AdapterProfil.MyViewHolder holder, final int position) {
        holder.tvNama.setText(item.get(position).getNama());
        holder.tvPosisi.setText(item.get(position).getPosisi());
        Log.e("Data ","Adapter "+item.get(position).getPosisi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("Tag","test Adater "+item.get(position).getNama());
                Intent sendData = new Intent(context, DetailDeveloper.class);
                sendData.putExtra("NAMA",item.get(position).getNama());
                sendData.putExtra("POSISI",item.get(position).getPosisi());
                sendData.putExtra("ALAMAT",item.get(position).getAlamat());
                sendData.putExtra("STATUS",item.get(position).getNamaStatusLamaran());
                sendData.putExtra("KEAHLIAN",item.get(position).getNamaKeahlian());
                sendData.putExtra("HRD_SALARY",item.get(position).getHrdSalary());
                context.startActivity(sendData);
            }
        });


    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()){
                    Toast.makeText(context, "tes", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<DataDevItem> filterList = new ArrayList<>();
                    for (DataDevItem dataDevItem : item){
                        if(dataDevItem.getPosisi().toLowerCase().contains(charString)||
                                dataDevItem.getNama().toLowerCase().contains(charString)){
                            filterList.add(dataDevItem);
                        }
                    }
                    item=filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = item;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                item = (ArrayList<DataDevItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvPosisi;
        ImageView ivDeveloper;
        CardView cvList;

        public MyViewHolder(View itemView) {
            super(itemView);
            cvList = (CardView)itemView.findViewById(R.id.cv_list);
            ivDeveloper = (ImageView)itemView.findViewById(R.id.foto_fragmen);
            tvNama = (TextView) itemView.findViewById(R.id.tv_fraghome_nama);
            tvPosisi = (TextView) itemView.findViewById(R.id.tv_fraghome_posisi);
        }
    }
}
