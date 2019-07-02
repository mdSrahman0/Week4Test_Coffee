package com.example.week4test_coffee;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class RetrofitCoffee {

    public static final String BASE_URL = "https://demo6983184.mockable.io/coffees/";

    public Retrofit getRetrofitInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create()).build();
    } // end method

    public getCoffeeListService getService() {
        return getRetrofitInstance().create(getCoffeeListService.class);
    }

    public interface getCoffeeListService {
        @GET
        Call<Coffee> getCoffeeListResponse(@Url String s);
    }
}
