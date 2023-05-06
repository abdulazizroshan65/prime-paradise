package com.example.prime_paradise;

import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


// Create a service interface
interface MyService {
    @GET("/")
    Call<ContactsContract.Contacts.Data> getData();
}
public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;

    private String URL = "http://192.168.8.101:5000/";
    RetrofitClient() {
//        Log.d("junda", "junda started ");

//        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        myApi = retrofit.create(Api.class);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();





        // Create a service instance
        MyService service = retrofit.create(MyService.class);

// Make a request and get the response body
        Response<ContactsContract.Contacts.Data> response = null;
        try {
            response = service.getData().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContactsContract.Contacts.Data data = response.body();


        Log.d("result", "Result retro: " + data);
    }



    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}

interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    <results>
    Call<List<results>> getsuperHeroes();
}

