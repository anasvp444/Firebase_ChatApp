package com.example.chatapp2.Fragments;

import com.example.chatapp2.Notifications.MyResponse;
import com.example.chatapp2.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA7XdHxmQ:APA91bHm4qc__QGbJNkirRIeu64VrYDLGqigeunKxq4Iiim3h_E9dkXel-VTOXBUkoNs1T0uYGfaKv-O_2fF0T5LIXatguRBqdX_9S08l0KG4KFB4npdWPG-hMn2p_yD9oxnRAleup5e"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
