package com.td.test.topfacts.repository.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private ServiceBuilder() {
    }

    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);
    private static Retrofit.Builder factServiceBuilder =
            new Retrofit.Builder().baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());
    private static Retrofit retrofit = factServiceBuilder.build();
    public static <T> T buildFactService(Class<T> serviceType) {
        return retrofit.create(serviceType);
    }
}
