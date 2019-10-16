package com.example.e_accountopening.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.e_accountopening.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment implements View.OnClickListener {
    View rootView;
    ImageView img_verisys;
    ImageView img_compliance;
    ImageView img_ekyc;
    ImageView img_account;
    private boolean isVerisys,isCompliance,isEkyc,isAccount;
    Button btn_ok;
    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_summary, container, false);

        img_verisys=rootView.findViewById(R.id.img_verisys);

        img_compliance=rootView.findViewById(R.id.img_compliance);

        img_ekyc=rootView.findViewById(R.id.img_ekyc);

        img_account=rootView.findViewById(R.id.img_account);

        btn_ok=rootView.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(this);
        isVerisys=true;
        isCompliance=false;
        isAccount=true;
        isEkyc=false;
        if(isVerisys){
            img_verisys.setVisibility(View.VISIBLE);
            img_verisys.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
        }else{
            img_verisys.setVisibility(View.VISIBLE);
            img_verisys.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
        }

        if(isCompliance){
            img_compliance.setVisibility(View.VISIBLE);
            img_compliance.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
        }else{
            img_compliance.setVisibility(View.VISIBLE);
            img_compliance.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
        }

        if(isEkyc){
            img_ekyc.setVisibility(View.VISIBLE);
            img_ekyc.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
        }else{
            img_ekyc.setVisibility(View.VISIBLE);
            img_ekyc.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
        }

        if(isAccount){
            img_account.setVisibility(View.VISIBLE);
            img_account.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
        }else{
            img_account.setVisibility(View.VISIBLE);
            img_account.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
        }
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_ok){
            replaceFragment(new FormFragment());
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft=this.getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fragment);
        ft.commit();
    }
}
