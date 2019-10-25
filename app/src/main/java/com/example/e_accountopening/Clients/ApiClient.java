package com.example.e_accountopening.Clients;

import android.util.Log;

import com.example.e_accountopening.Interfaces.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
 

    public static final String BASE_URL = "http://df2a6bbd.ngrok.io/bulk/";
//    public static final String BASE_URL = "http://172.28.28.35:8080/bulk/";
//      public static final String BASE_URL = "http://172.28.28.51:8080/api/";
//      public static final String BASE_URL = "http://192.168.1.7:8080/";
    private static Retrofit retrofit = null;


    public static ApiInterface getInstance() {

          if (retrofit==null) {
              Gson gson = new GsonBuilder()
                      .setLenient()
                      .create();
              retrofit = new Retrofit.Builder()
                      .baseUrl(BASE_URL)
                      .addConverterFactory(GsonConverterFactory.create(gson))
                      .client(getRequestHeader())
                      .build();
          }

        return retrofit.create(ApiInterface.class);
    }

    private static OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
         .addNetworkInterceptor(interceptor)
        .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // try the request
                Response response = chain.proceed(request);

                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 3) {

                    Log.d("intercept", "Request is not successful - " + tryCount);

                    tryCount++;

                    // retry the request
                    response = chain.proceed(request);
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

                // otherwise just pass the original response on
                return response;
            }
        })
        .connectTimeout(40,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build();
        return httpClient;

    }

}