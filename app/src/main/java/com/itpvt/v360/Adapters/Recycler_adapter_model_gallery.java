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
import com.itpvt.v360.Activities.Full_Modle_Gallery;
import com.itpvt.v360.PojoClasses.Album;
import com.itpvt.v360.R;

import java.util.ArrayList;

/**
 * Created by Itpvt on 22-Nov-17.
 */

public class Recycler_adapter_model_gallery extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {//this is the recylerAdapter so you can load upload images in arry as many as you have on the server
    ArrayList<Album> arrayList = new ArrayList<>();
    Activity activity;
    boolean isImageFitToScreen;

    public Recycler_adapter_model_gallery(ArrayList<Album> arrayList, Context context) {
        this.arrayList = arrayList;//list of name and urls from jason array
        activity = (Activity) context;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);//this inflate the view on the on layout
        return new RecyclerAdapter.MyViewHolder(view);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            Thumbnail = (ImageView) itemView.findViewById(R.id.thubnail);
            //name=(TextView)itemView.findViewById(R.id.album_title);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {
        //holder.name.setText(arrayList.get(position).getName());
        final String path = arrayList.get(position).getUrl();//get the image path from url
        Glide.with(activity).load(path).into(holder.Thumbnail);//through glid libaray setting the image to image view with the given Url
        holder.Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show();//
//                ImageViewPopUpHelper.enablePopUpOnClick(activity,holder.Thumbnail);//
//                Intent intent = new Intent(activity,FullScreenImage.class);
//                holder.Thumbnail.buildDrawingCache();
//                Bitmap bitmap = holder.Thumbnail.getDrawingCache();
//                Bundle extras = new Bundle();
//                extras.putParcelable("imagebitmap", bitmap);
//                intent.putExtras(extras);
//                String add = String.valueOf(arrayList.get(position));//
//                int add1 = Integer.parseInt(add);//
//                intent.putExtra("position", add1);//
//                activity.startActivity(intent);



                Intent intent =new Intent(activity,Full_Modle_Gallery.class);
                activity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

