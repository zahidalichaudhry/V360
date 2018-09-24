package com.itpvt.v360.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itpvt.v360.Activities.Home_profile_photographers;
import com.itpvt.v360.Config;
import com.itpvt.v360.PojoClasses.Photog_album;
import com.itpvt.v360.R;

import java.util.ArrayList;

public class Photog_Recycler_Adapter extends RecyclerView.Adapter<Photog_Recycler_Adapter.MyViewHolder> {
    ArrayList<Photog_album> arrayList= new ArrayList<>();
    Activity activity;

    public Photog_Recycler_Adapter(ArrayList<Photog_album> arrayList, Context context)
    {
        this.arrayList=arrayList;
        activity=(Activity)context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.photog_card_item,parent,false);
        return new Photog_Recycler_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.name.setText(arrayList.get(position).getName());
        String path= Config.uploads+arrayList.get(position).getUsername();
        String image = arrayList.get(position).getUrl();
//        Glide.with(activity).load(arrayList.get(position).getUrl()).into(holder.Photog_Thumbnail);
        Glide.with(activity).load(image).into(holder.Photog_Thumbnail);

        holder.Photog_Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity,arrayList.get(position).getUsername(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,Home_profile_photographers.class);
                intent.putExtra("Username",arrayList.get(position).getUsername());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView Photog_Thumbnail;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            Photog_Thumbnail=(ImageView) itemView.findViewById(R.id.photog_thumbnail);
            name=(TextView)itemView.findViewById(R.id.tvphotogname);
        }
    }
}
