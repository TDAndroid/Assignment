package com.td.test.topfacts.repository.network;

import com.td.test.topfacts.repository.network.responsemodel.FactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/s/2iodh4vg0eortkl/facts.js")
    Call<FactsResponse> getFacts();
}
