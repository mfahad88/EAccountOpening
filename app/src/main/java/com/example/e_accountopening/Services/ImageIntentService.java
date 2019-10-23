package com.example.e_accountopening.Services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.e_accountopening.Clients.ApiClient;
import com.example.e_accountopening.Fragments.FormFragment;
import com.example.e_accountopening.Helper.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageIntentService extends IntentService {
    public ImageIntentService() {
        super("ImageIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            Bundle bundle=intent.getExtras();
            byte[] byteArray=bundle.getByteArray("image");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            final File file=new File("/mnt/sdcard/"+ Utils.getPreferences(getApplicationContext(), FormFragment.CNIC)+".jpg");
            try {
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(byteArray);
                fo.flush();
                fo.close();

                if(file.exists()){
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                    ApiClient.getInstance().uploadFile(filePart).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.wtf("ImageIntentService",response.body());
                            if(response.isSuccessful()){
                                file.delete();
                            }else{
                                Toast.makeText(ImageIntentService.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(ImageIntentService.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
