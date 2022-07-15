package com.example.dalisaapp.zUtilidad.service;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServicioGenerador {
    private static OkHttpClient okHttpClient;

    private ServicioGenerador() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return createService(serviceClass, baseUrl, (Interceptor) null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, Interceptor interceptor) {
        return createService(serviceClass, baseUrl, interceptor, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, Converter.Factory[] factories) {
        return createService(serviceClass, baseUrl, null, factories);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, Interceptor interceptor, Converter.Factory[] factories) {
        // In retrofit 1.9 Retrofit class is RestAdapter, baseUrl(String) is setEndpoint(String); addInterceptor(Interceptor) is setRequestInterceptor(RequestInterceptor)
        // OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request originalRequest = chain.request();
                Log.v("ley request: ",chain.request().toString());
                // Log.v("ley : ",chain.connection().toString());
                Headers headers = originalRequest.headers().newBuilder().add("authorization",Servidor.TOKEN).build();
                okhttp3.Request.Builder builder = originalRequest.newBuilder().headers(headers)   ;

                okhttp3.Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder();

        //check if contains default converter(JSON)
        boolean containsDefaultConverter = false;
        if (factories != null && factories.length > 0) {
            for (Converter.Factory factory : factories) {
                builder.addConverterFactory(factory); //The factory who takes care for serialization and deserialization of objects
                if (factory instanceof GsonConverterFactory)
                    containsDefaultConverter = true;
            }
        }

        //if it does not contain default converter, then add it
        if (!containsDefaultConverter)
            builder.addConverterFactory(GsonConverterFactory.create(gson));

        if (!TextUtils.isEmpty(baseUrl))
            builder.baseUrl(baseUrl);

        if (interceptor != null && !httpClient.interceptors().contains(interceptor))
            httpClient.addInterceptor(interceptor);
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
