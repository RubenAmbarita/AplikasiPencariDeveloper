package metrodata.mii.aplikasideveloper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import metrodata.mii.aplikasideveloper.ActivityUI.ChatActivity;
import metrodata.mii.aplikasideveloper.Model.m.register.getKontak.DataAdminItem;
import metrodata.mii.aplikasideveloper.R;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyHolder> {

    Context context;
    List<DataAdminItem> list;

    public AdapterChat(Context context, List<DataAdminItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterChat.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_people_chat,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChat.MyHolder holder, final int position) {
        holder.nama.setText(list.get(position).getFullname());
        holder.noHP.setText(list.get(position).getNoHp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("ID",list.get(position).getIdUser());
                intent.putExtra("NAMA",list.get(position).getFullname());
                intent.putExtra("NOHP",list.get(position).getNoHp());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nama,noHP;
        public MyHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView.findViewById(R.id.name);
            noHP = (TextView)itemView.findViewById(R.id.noHp);
        }
    }
}
