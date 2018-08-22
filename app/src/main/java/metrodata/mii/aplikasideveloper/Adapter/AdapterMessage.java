package metrodata.mii.aplikasideveloper.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import metrodata.mii.aplikasideveloper.Helper.SessionManager;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.MessageItem;
import metrodata.mii.aplikasideveloper.R;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.MyHandle> {

    private static String User ;

    List<MessageItem> itemList;
    Context context;

    public AdapterMessage(List<MessageItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    @Override
    public MyHandle onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent,false);
        MyHandle handle = new MyHandle(view);
        return handle;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHandle holder, int position) {
   //     get id yang login
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.getUserDetails();
        User = user.get(SessionManager.kunci_id);



        if(User.equals(itemList.get(position).getMessageSender())){
            holder.pesan1.setVisibility(View.GONE);
            holder.tvNama1.setVisibility(View.GONE);
            holder.pesan2.setText(itemList.get(position).getMessageBody());
        }else if(!User.equals(itemList.get(position).getMessageSender())){
            holder.pesan1.setText(itemList.get(position).getMessageBody());
            holder.pesan2.setVisibility(View.GONE);
            holder.tvNama2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyHandle extends RecyclerView.ViewHolder {
        TextView pesan1,pesan2,tvNama1,tvNama2;

        public MyHandle(View itemView) {
            super(itemView);
            pesan1 = (TextView)itemView.findViewById(R.id.tv_body_left);
            pesan2 = (TextView)itemView.findViewById(R.id.tv_body_right);
            tvNama1 =(TextView) itemView.findViewById(R.id.tv_nama1);
            tvNama2 =(TextView) itemView.findViewById(R.id.tv_nama2);
        }
    }
}
