package com.example.e_accountopening.Services;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.e_accountopening.Activities.MainActivity;
import com.example.e_accountopening.Clients.ApiClient;
import com.example.e_accountopening.Fragments.FormFragment;
import com.example.e_accountopening.Helper.Utils;
import com.example.e_accountopening.Models.request.CreateAccountBean;
import com.example.e_accountopening.Models.response.CreateAccountResponse;
import com.example.e_accountopening.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class FormIntentService extends IntentService {
    private String cnic;
    private String issue_date;
    private String full_name;
    private String mother_name;
    private String dob;
    private String pob;


    public FormIntentService() {
        super("FormIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            final Bundle bundle=intent.getExtras();
            Log.wtf("Bundle",bundle.toString());
            cnic=bundle.getString(FormFragment.CNIC);
            issue_date=bundle.getString(FormFragment.ISSUE_DATE);
            full_name=bundle.getString(FormFragment.FULL_NAME);
            mother_name=bundle.getString(FormFragment.MOTHER_NAME);
            dob=bundle.getString(FormFragment.DOB);
            pob=bundle.getString(FormFragment.POB);
            CreateAccountBean bean=new CreateAccountBean(cnic,issue_date,full_name,mother_name,dob,pob);
            ApiClient.getInstance().CreateAccount(bean).enqueue(new Callback<CreateAccountResponse>() {
                @Override
                public void onResponse(Call<CreateAccountResponse> call, Response<CreateAccountResponse> response) {
                    if(response.isSuccessful()){
                        Log.wtf("Response",response.body().toString());
                        Utils.savePreferences(FormIntentService.this,"RefId", String.valueOf(response.body().getRefId().intValue()));
                    }else{
                        Toast.makeText(FormIntentService.this, ""+response.message(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<CreateAccountResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(FormIntentService.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
