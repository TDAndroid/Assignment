package com.td.test.topfacts.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.td.test.topfacts.repository.database.model.FactsModel;
import com.td.test.topfacts.repository.network.API;
import com.td.test.topfacts.repository.network.ServiceBuilder;
import com.td.test.topfacts.repository.network.responsemodel.FactsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactsRepository {

    private FactsRepository() {
    }

    private static FactsRepository factsRepository;
    private static API api;

    public static FactsRepository getInstance() {
        if (factsRepository == null) {
            synchronized (FactsRepository.class) {
                if (factsRepository == null) {
                    factsRepository = new FactsRepository();
                    api = ServiceBuilder.buildFactService(API.class);
                }
            }
        }
        return factsRepository;
    }

    public LiveData<List<FactsModel>> getFacts() {
        final MediatorLiveData<List<FactsModel>> mlFactsModel = new MediatorLiveData<>();
        api.getFacts().enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {
                List<FactsModel> listFactsModel = response.body().getFactsModel();
                mlFactsModel.setValue(listFactsModel);
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {

            }
        });
        return mlFactsModel;
    }
}
