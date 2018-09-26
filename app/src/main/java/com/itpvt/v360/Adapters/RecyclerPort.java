package com.itpvt.v360.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itpvt.v360.Activities.Full_Model_screen;
import com.itpvt.v360.PojoClasses.AlbumPort;
import com.itpvt.v360.R;


import java.util.ArrayList;

public class RecyclerPort extends RecyclerView.Adapter<RecyclerPort.MyViewHolder> {
    String userId;
    ArrayList<AlbumPort> arrayList= new ArrayList<>();
    Activity activity;
    public RecyclerPort(ArrayList<AlbumPort> arrayList, Context context, String url)
    {
        userId=url;
        this.arrayList=arrayList;
        activity=(Activity)context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_portfolio,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String path=arrayList.get(position).getUrl();
        Glide.with(activity).load(path).into(holder.Thumbnail);


        holder.Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show();
////                ImageViewPopUpHelper.enablePopUpOnClick(activity,holder.Thumbnail);
//                Intent intent = new Intent(activity,FullScreenImage.class);
//                holder.Thumbnail.buildDrawingCache();
//                Bitmap bitmap = holder.Thumbnail.getDrawingCache();
//                Bundle extras = new Bundle();
//                extras.putParcelable("imagebitmap", bitmap);
//                intent.putExtras(extras);
//                activity.startActivity(intent);
                Intent intent =new Intent(activity,Full_Model_screen.class);
                  intent.putExtra("portUsername",userId);
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
        ImageView Thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            Thumbnail=(ImageView) itemView.findViewById(R.id.thubnail);
        }
    }
}
