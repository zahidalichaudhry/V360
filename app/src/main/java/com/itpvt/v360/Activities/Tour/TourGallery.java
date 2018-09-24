package com.itpvt.v360.Activities.Tour;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.itpvt.v360.Activities.Exibition_gallery;
import com.itpvt.v360.Activities.Model_Shoot_Gallery;
import com.itpvt.v360.Activities.Party_gallery;
import com.itpvt.v360.Activities.wedding_shoot_gallery;
import com.itpvt.v360.Config;
import com.itpvt.v360.MySingleton2;
import com.itpvt.v360.PojoClasses.Album;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TourGallery extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //
//    SliderLayout sliderLayout ;
    ImageView model_shoot,wedding_shoot,e_party,e_exibition;
//
//    HashMap<String, String> HashMapForURL ;
//
//    HashMap<String, Integer> HashMapForLocalRes ;
//  static String path0;
//    static String path1;
//   static String path2;
//     static String path3;
//   static String path4;

    private ProgressDialog loading;

    static ArrayList<Album> arrayList=new ArrayList<>();
////    static ArrayList<Album> arrayList2=new ArrayList<>();
//    RecyclerView recyclerView;//initialize the recyclerView
//    RecyclerAdapter adapter;//initialize the recycleradapter to infilate
//    RecyclerView.LayoutManager layoutManager;//this manage the view how you want to infilate

//    private GridView gridView;
//    private GridViewAdapter gridAdapter;


    public TourGallery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tour_gallery, container, false);
        //        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
//        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
//        layoutManager=new GridLayoutManager(getActivity(),3);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
        model_shoot=(ImageView)view.findViewById(R.id.e_model);
        wedding_shoot=(ImageView)view.findViewById(R.id.e_marriage);
        e_party=(ImageView)view.findViewById(R.id.e_party);
        e_exibition=(ImageView)view.findViewById(R.id.e_exibition);
        e_exibition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Exibition_gallery.class);
                startActivity(intent);
            }
        });
        e_party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Party_gallery.class);
                startActivity(intent);
            }
        });
        wedding_shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),wedding_shoot_gallery.class);
                startActivity(intent);
            }
        });
        model_shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Model_Shoot_Gallery.class);
                startActivity(intent);
            }
        });

//        loading = ProgressDialog.show(getActivity(),"Fetching...","Please wait...",false,false);
//        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, Config.serv_url, null,//this is the function through you get the name and url from jsnon arry form php file
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        int count=0;
//                        while (count<response.length())
//                        {
//                            try {
//                                JSONObject jsonObject=response.getJSONObject(count);
//                                arrayList.add(new Album(jsonObject.getString("name"),jsonObject.getString("url")));//getthing the name and url from file and storing into the arryalist
//                                count++ ;
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
////                        path0= arrayList.get(0).getUrl().toString();
////                        path1= arrayList.get(1).getUrl().toString();
////                        path2= arrayList.get(2).getUrl().toString();
////                        path3= arrayList.get(3).getUrl().toString();
////                        path4= arrayList.get(4).getUrl().toString();
////                        AddImagesUrlOnline();
////                        adapter=new RecyclerAdapter(arrayList,getActivity());//now we send the name urls to adapter
////                        recyclerView.setAdapter(adapter);//we set that adapter to the recycerView
//                        loading.dismiss();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
//            }
//        });
//        MySingleton2.getmInstance(getActivity()).addToRequestQue(jsonArrayRequest);//using the vollay to request the jason array


        return view;
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Toast.makeText(getActivity().getApplicationContext(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
//
//       private void AddImagesUrlOnline()
//
//       {
//
////        HashMapForURL = new HashMap<String, String>();
////
////           HashMapForURL.put("CupCake", path0);
////           HashMapForURL.put("Donut", path1);
////           HashMapForURL.put("Eclair", path2);
////           HashMapForURL.put("Froyo", path3);
////           HashMapForURL.put("GingerBread", path4);
//           callSlider();
//    }
//
//    private void callSlider() {
//
//        for(String name : HashMapForURL.keySet()){
//
//            TextSliderView textSliderView = new TextSliderView(getActivity().getApplicationContext());
//
//            textSliderView
//                    .description(name)
//                    .image(HashMapForURL.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//
//            textSliderView.bundle(new Bundle());
//
//            textSliderView.getBundle()
//                    .putString("extra",name);
//
//            sliderLayout.addSlider(textSliderView);
//        }
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//        sliderLayout.setDuration(5000);
//    }
//
//
//    public void AddImageUrlFormLocalRes(){
//
//        HashMapForLocalRes = new HashMap<String, Integer>();
//
//        HashMapForLocalRes.put("Love Birds", R.drawable.camera);
//        HashMapForLocalRes.put("Sunset", R.drawable.scenery);
//        HashMapForLocalRes.put("Portrait", R.drawable.portrait);
//        HashMapForLocalRes.put("Bridal Shoot", R.drawable.bridal);
//        HashMapForLocalRes.put("San Francisco", R.drawable.san);
//
//    }

}
