package com.td.test.topfacts.uicomponents.facts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.td.test.topfacts.repository.FactsRepository;
import com.td.test.topfacts.repository.database.model.FactsModel;

import java.util.Iterator;
import java.util.List;

public class FactsViewModel extends AndroidViewModel {

    private FactsRepository factsRepository;
    private FactsListAdapter factsListAdapter;
    public FactsViewModel(@NonNull Application application) {
        super(application);
        factsRepository = FactsRepository.getInstance();
    }

    public FactsListAdapter getAdapter(List<FactsModel> listFactsModel) {
        if (factsListAdapter == null) {
            Iterator<FactsModel> iterator = listFactsModel.iterator();
            while (iterator.hasNext()) {
                FactsModel factsModel = iterator.next();
                String header = factsModel.getTitle();
                String description = factsModel.getDescription();
                if (header == null && description == null)
                    iterator.remove();
            }
            factsListAdapter = new FactsListAdapter(listFactsModel);
        }
        return  factsListAdapter;
    }

    public LiveData<List<FactsModel>> getFacts() {
        return factsRepository.getFacts();
    }
}
