package com.itpvt.v360.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.itpvt.v360.Activities.Reg_Exib;
import com.itpvt.v360.Activities.Reg_marriage;
import com.itpvt.v360.Activities.Reg_modelshoot;
import com.itpvt.v360.Activities.Reg_party;
import com.itpvt.v360.R;


public class Events extends Fragment {
    ImageView marriage, party, model_shoot,exib;

    public Events() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_events, container, false);
        marriage = (ImageView) view.findViewById(R.id.e_marriage);
        party = (ImageView) view.findViewById(R.id.e_party);
        model_shoot = (ImageView) view.findViewById(R.id.e_model);
        exib=(ImageView)view.findViewById(R.id.e_exibition);

        marriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), Reg_marriage.class);
                startActivity(it);
            }
        });
        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), Reg_party.class);
                startActivity(it);
            }
        });
        model_shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), Reg_modelshoot.class);
                startActivity(it);
            }
        });
        exib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity().getApplicationContext(), Reg_Exib.class);
                startActivity(it);

                Toast.makeText(getActivity().getApplicationContext(),"Comming Soon", Toast.LENGTH_LONG);
            }
        });
        return view;
    }

}
