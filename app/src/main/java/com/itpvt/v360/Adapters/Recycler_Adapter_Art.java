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
import com.itpvt.v360.Activities.Home_Profile_Art;
import com.itpvt.v360.Config;
import com.itpvt.v360.PojoClasses.Model_album;
import com.itpvt.v360.R;

import java.util.ArrayList;

/**
 * Created by Itpvt on 20-Nov-17.
 */

public class Recycler_Adapter_Art  extends RecyclerView.Adapter<Recycler_Adapter_Art.MyViewHolder>{

        ArrayList<Model_album> arrayList= new ArrayList<>();
        Activity activity;

public Recycler_Adapter_Art(ArrayList<Model_album> arrayList, Context context)
        {
        this.arrayList=arrayList;
        activity=(Activity)context;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_card_item,parent,false);
        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.name.setText(arrayList.get(position).getName());
        String path= Config.uploads+arrayList.get(position).getUsername();
        Glide.with(activity).load(arrayList.get(position).getUrl()).into(holder.Model_Thumbnail);

        holder.Model_Thumbnail.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
//                Toast.makeText(activity,arrayList.get(position).getUsername(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity,Home_Profile_Art.class);
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
    ImageView Model_Thumbnail;
    TextView name;
    public MyViewHolder(View itemView) {
        super(itemView);
        Model_Thumbnail=(ImageView) itemView.findViewById(R.id.thumbnail);
        name=(TextView)itemView.findViewById(R.id.tvmodelname);
    }
}
}
