package com.example.e_accountopening.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.os.Handler;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.e_accountopening.R;
import com.example.e_accountopening.Services.SummaryIntentService;

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
    ImageResultReceiver imageResultReceiver;
    LinearLayout linear_progress;
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

        linear_progress=rootView.findViewById(R.id.linear_progress);

        imageResultReceiver = new ImageResultReceiver(new Handler());


        Intent intent=new Intent(rootView.getContext(),SummaryIntentService.class);
        intent.putExtra("receiver", imageResultReceiver);
        getActivity().startService(intent);

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

    private class ImageResultReceiver extends ResultReceiver {

        public ImageResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
           if(resultCode==200){

               if(resultData.getInt(SummaryIntentService.VERISYS)==1){
                   isVerisys=true;
                   img_verisys.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_verisys.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_verisys.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
                               }
                           });
                       }
                   }).start();
               }if(resultData.getInt(SummaryIntentService.VERISYS)==2){
                   isVerisys=false;
                   img_verisys.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_verisys.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_verisys.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
                               }
                           });
                       }
                   }).start();
               }

               if(resultData.getInt(SummaryIntentService.COMPLIANCE)==1){
                   isCompliance=true;
                   img_compliance.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_compliance.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_compliance.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
                               }
                           });
                       }
                   }).start();
               }if(resultData.getInt(SummaryIntentService.COMPLIANCE)==2){
                   isCompliance=false;
                   img_compliance.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_compliance.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_compliance.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
                               }
                           });
                       }
                   }).start();
               }

               if(resultData.getInt(SummaryIntentService.EKYC)==1){
                   isEkyc=true;
                   img_ekyc.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_ekyc.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_ekyc.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
                               }
                           });
                       }
                   }).start();
               }if(resultData.getInt(SummaryIntentService.EKYC)==2){
                   isEkyc=false;
                   img_ekyc.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_ekyc.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_ekyc.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
                               }
                           });
                       }
                   }).start();
               }

               if(isVerisys && isCompliance && isEkyc){
                   img_account.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_account.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_account.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_tick_mark));
                               }
                           });
                       }
                   }).start();
                   linear_progress.setVisibility(View.GONE);
               }else{
                   img_account.setVisibility(View.VISIBLE);
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           img_account.post(new Runnable() {
                               @Override
                               public void run() {
                                   img_account.setImageDrawable(ContextCompat.getDrawable(rootView.getContext(),R.drawable.iconfinder_delete));
                               }
                           });
                       }
                   }).start();
                   linear_progress.setVisibility(View.GONE);
               }
           }
            super.onReceiveResult(resultCode, resultData);
        }

    }
}
