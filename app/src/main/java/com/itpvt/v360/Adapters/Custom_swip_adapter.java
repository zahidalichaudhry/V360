package com.itpvt.v360.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.itpvt.v360.PojoClasses.Album;
import com.itpvt.v360.R;


import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Itpvt on 11-Nov-17.
 */

public class Custom_swip_adapter extends PagerAdapter {

    ArrayList<Album> arrayList = new ArrayList<>();
    private Context ctx;
    private LayoutInflater layoutInflater;


    public Custom_swip_adapter(Context ctx, ArrayList<Album> arrayList) {
        this.ctx = ctx;
        this.arrayList = arrayList;
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((LinearLayout)object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View item_view= layoutInflater.inflate(R.layout.swip_images,container,false );
        ImageView imageView=(ImageView)  item_view.findViewById(R.id.image);
        PhotoViewAttacher photoAttacher;
        photoAttacher= new PhotoViewAttacher(imageView);
        photoAttacher.update();
        String image = arrayList.get(position).getUrl();

        Glide.with(ctx).load(image).into(imageView);
        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
