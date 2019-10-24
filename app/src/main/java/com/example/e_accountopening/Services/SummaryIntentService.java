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
import com.example.e_accountopening.logs.RemoteLogCat;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryIntentService extends IntentService {
    public static final String VERISYS="verisys";
    public static final String COMPLIANCE="compliance";
    public static final String EKYC="ekyc";
    public boolean isRpa,isEkyc;
    private RemoteLogCat logCat;
    public SummaryIntentService() {
        super("SummaryIntentService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

        if(intent!=null){
            logCat=new RemoteLogCat();
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
                            .enqueue(new Callback<List<RefIdResponse>>() {
                                @Override
                                public void onResponse(Call<List<RefIdResponse>> call, Response<List<RefIdResponse>> response) {
                                    if(response.isSuccessful()){

                                      for(RefIdResponse response1:response.body()){
                                          Bundle bundle = new Bundle();
                                          if(response1.getRpaStatus()==1){
                                              bundle.putInt(VERISYS,response1.getVerisysStatus().intValue());
                                              bundle.putInt(COMPLIANCE,response1.getCompStatus().intValue());
                                              isRpa=true;
                                              receiver.send(200,bundle);

                                          }if(response1.getEkyc()==1){
                                              bundle.putInt(EKYC,response1.getEkycResponse().intValue());
                                              isEkyc=true;
                                              receiver.send(200,bundle);
                                          }
                                          if(isRpa && isEkyc){
                                              timer.cancel();
                                              timer.purge();
                                          }
                                      }
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<RefIdResponse>> call, Throwable t) {
                                    t.printStackTrace();
                                    timer.cancel();
                                    timer.purge();
                                    Toast.makeText(SummaryIntentService.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                    logCat.log("error",this.getClass().getSimpleName()+"\n"+t.getMessage());
                                }
                            });

                }
            },0,5000);

        }
    }


}
