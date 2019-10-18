package com.example.e_accountopening.Interfaces;

import com.example.e_accountopening.Models.request.CreateAccountBean;
import com.example.e_accountopening.Models.request.RefIdRequestBean;
import com.example.e_accountopening.Models.response.CreateAccountResponse;
import com.example.e_accountopening.Models.response.RefIdResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @POST("CreateAccount")
    Call<CreateAccountResponse> CreateAccount(@Body CreateAccountBean bean);

    @Multipart
    @POST("uploadFile")
    Call<String> uploadFile(@Part MultipartBody.Part filePart);

    @POST("refId")
    Call<List<RefIdResponse>> refId(@Body RefIdRequestBean bean);
}