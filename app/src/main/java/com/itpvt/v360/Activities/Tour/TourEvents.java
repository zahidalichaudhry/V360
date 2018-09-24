package com.itpvt.v360.Activities.Tour;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.itpvt.v360.Activities.Home;
import com.itpvt.v360.R;

public class TourEvents extends Fragment {
    ImageView marriage, party, model_shoot,exb;

    public TourEvents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tour_events, container, false);
        marriage = (ImageView) view.findViewById(R.id.e_marriage);
        party = (ImageView) view.findViewById(R.id.e_party);
        model_shoot = (ImageView) view.findViewById(R.id.e_model);
        exb=(ImageView)view.findViewById(R.id.e_exibition);

        marriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Please Login to Continue", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Please Login to Continue", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        model_shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Please Login to Continue", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        exb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Please Login to Continue", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
