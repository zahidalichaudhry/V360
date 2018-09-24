package com.itpvt.v360.Activities.Tour;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itpvt.v360.Activities.All_Art;
import com.itpvt.v360.Activities.All_Designers;
import com.itpvt.v360.Activities.All_models;
import com.itpvt.v360.Activities.All_photogs;
import com.itpvt.v360.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourHome extends Fragment {
    ImageView intro,model,photographers,designer;

    public TourHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tour_home, container, false);
        intro = (ImageView) view.findViewById(R.id.intro_home);
        model = (ImageView) view.findViewById(R.id.model_home);
        photographers = (ImageView) view.findViewById(R.id.photog_home);
        designer=(ImageView)view.findViewById(R.id.design_home);

        designer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity().getApplicationContext(), All_Designers.class);
                startActivity(it);
            }
        });

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), All_Art.class);
                startActivity(it);
            }
        });
        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), All_models.class);
                startActivity(it);
            }
        });
        photographers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), All_photogs.class);
                startActivity(it);
            }
        });
        return view;
    }
}
