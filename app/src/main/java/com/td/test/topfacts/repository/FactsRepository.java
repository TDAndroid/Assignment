package com.td.test.topfacts.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.td.test.topfacts.repository.database.AppDatabase;
import com.td.test.topfacts.repository.database.model.FactsModel;
import com.td.test.topfacts.repository.network.API;
import com.td.test.topfacts.repository.network.ServiceBuilder;
import com.td.test.topfacts.repository.network.responsemodel.FactsResponse;
import com.td.test.topfacts.util.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactsRepository {

    private static AppDatabase appDatabase;
    private static SharedPreferences sharedPreferences;
    private static FactsRepository factsRepository;
    private static API api;
    private FactsRepository() {
    }

    public static FactsRepository getInstance(Application appContext) {
        if (factsRepository == null) {
            synchronized (FactsRepository.class) {
                if (factsRepository == null) {
                    factsRepository = new FactsRepository();
                    api = ServiceBuilder.buildFactService(API.class);
                    appDatabase = AppDatabase.getInstance(appContext);
                    sharedPreferences = appContext.getSharedPreferences(AppConstants.SH_APP, Context.MODE_PRIVATE);
                }
            }
        }
        return factsRepository;
    }

    /**
     * Get the facts from the network
     *
     * @return
     */
    public LiveData<List<FactsModel>> getFacts() {
        final MediatorLiveData<List<FactsModel>> mlFactsModel = new MediatorLiveData<>();
        api.getFacts().enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {
                if (response != null && response.body().getFactsModel() != null) {
                    List<FactsModel> listFactsModel = response.body().getFactsModel();
                    if (listFactsModel != null)
                        insertFacts(listFactsModel);
                    String title = response.body().getTitle();
                    sharedPreferences.edit().putString(AppConstants.SH_APP_TITLE, title).commit();
                }
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {

            }
        });
        mlFactsModel.addSource(appDatabase.getFactsModelDao().getFacts(), new Observer<List<FactsModel>>() {
            @Override
            public void onChanged(@Nullable List<FactsModel> factsModels) {
                if (factsModels != null)
                    mlFactsModel.setValue(factsModels);
            }
        });
        return mlFactsModel;
    }

    /**
     * Insert facts into room db
     *
     * @param facts
     */
    public void insertFacts(final List<FactsModel> facts) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long[] insertedFactsIds = appDatabase.getFactsModelDao().insertFacts(facts);
                if (insertedFactsIds == null || insertedFactsIds.length == 0) {
                    //Not able to insert facts into db.
                }
            }
        }).start();

    }
}
