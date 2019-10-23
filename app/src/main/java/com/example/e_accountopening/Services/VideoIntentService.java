package com.example.e_accountopening.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.e_accountopening.Clients.ApiClient;
import com.example.e_accountopening.Fragments.FormFragment;
import com.example.e_accountopening.Helper.Utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class VideoIntentService extends IntentService {


    public VideoIntentService() {
        super("VideoIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        final File file=new File("/mnt/sdcard/"+ Utils.getPreferences(getApplicationContext(), FormFragment.CNIC)+".mp4");
        if(file.exists()){
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("video/*"), file));
            ApiClient.getInstance().uploadFile(filePart).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.wtf("VideoIntentService",response.body());
                    if(response.isSuccessful()){
                        file.delete();
                        Log.wtf("VideoIntentService",response.body());
                    }else{
                        Toast.makeText(VideoIntentService.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(VideoIntentService.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
