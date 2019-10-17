package com.example.e_accountopening.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import com.example.e_accountopening.Clients.ApiClient;
import com.example.e_accountopening.Helper.Utils;
import com.example.e_accountopening.Models.request.RefIdRequestBean;
import com.example.e_accountopening.Models.response.RefIdResponse;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryIntentService extends IntentService {
    public static final String VERISYS="verisys";
    public static final String COMPLIANCE="compliance";
    public static final String EKYC="ekyc";


    public SummaryIntentService() {
        super("SummaryIntentService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

        if(intent!=null){
            final ResultReceiver receiver = intent.getParcelableExtra("receiver");
            final Timer timer=new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    /*Bundle bundle=new Bundle();
                    bundle.putInt(VERISYS,1);
                    bundle.putInt(COMPLIANCE,2);
                    bundle.putInt(EKYC,1);
                    receiver.send(200,bundle);*/
                    ApiClient.getInstance().refId(new RefIdRequestBean(Utils.getPreferences(SummaryIntentService.this,"RefId")))
                            .enqueue(new Callback<RefIdResponse>() {
                                @Override
                                public void onResponse(Call<RefIdResponse> call, Response<RefIdResponse> response) {
                                    if(response.isSuccessful()){

                                        Bundle bundle = new Bundle();
                                        if(response.body().getRpaStatus()==1){
                                            bundle.putInt(VERISYS,response.body().getVerisysStatus().intValue());
                                            bundle.putInt(COMPLIANCE,response.body().getCompStatus().intValue());
                                            receiver.send(200,bundle);

                                        }if(response.body().getEkyc()==1){
                                            bundle.putInt(EKYC,response.body().getEkycResponse().intValue());
                                            receiver.send(200,bundle);
                                        }
                                    }else{
                                        Toast.makeText(SummaryIntentService.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                    timer.cancel();
                                    timer.purge();

                                }

                                @Override
                                public void onFailure(Call<RefIdResponse> call, Throwable t) {
                                    t.printStackTrace();
                                    timer.cancel();
                                    timer.purge();
                                    Toast.makeText(SummaryIntentService.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            },0,2000);

        }
    }


}
