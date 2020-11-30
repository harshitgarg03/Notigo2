package com.example.notigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;




import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    ArrayList<Listitem> listItems;

    public MyAdapter(Context context, ArrayList<Listitem> listItems) {
        this.listItems = listItems;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem listItem = listItems.get(position);

        holder.content.setText(listItem.getContent());
        holder.date.setText(listItem.getDate());
        holder.time.setText(listItem.getTime());
        //if any image use
        //Picasso.get().load(listItem.getProfilePic()).into(holder.profilePic)
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView date;
        TextView time;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            content= (TextView) itemView.findViewById(R.id.textviewcontent);
            date=(TextView) itemView.findViewById(R.id.textviewdate);
            time=(TextView) itemView.findViewById(R.id.textviewtime);

        }




    }
}