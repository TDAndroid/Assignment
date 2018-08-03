package com.td.test.topfacts.repository.network;

import com.td.test.topfacts.repository.network.responsemodel.FactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/facts.json")
    Call<FactsResponse> getFacts();
 }
