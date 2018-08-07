package com.td.test.topfacts.uicomponents.facts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.td.test.topfacts.repository.FactsRepository;
import com.td.test.topfacts.repository.database.model.FactsModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactsViewModel extends AndroidViewModel {

    private FactsRepository factsRepository;
    private FactsListAdapter factsListAdapter;
    public FactsViewModel(@NonNull Application application) {
        super(application);
        factsRepository = FactsRepository.getInstance(application);
    }

    public FactsListAdapter getAdapter() {
        if (factsListAdapter == null) {
            factsListAdapter = new FactsListAdapter(new ArrayList<FactsModel>());
        }
        return  factsListAdapter;
    }

    public List<FactsModel> getFilteredFacts(List<FactsModel> listFactsModel) {
        if (listFactsModel == null)
            return new ArrayList<>();
        Iterator<FactsModel> iterator = listFactsModel.iterator();
        while (iterator.hasNext()) {
            FactsModel factsModel = iterator.next();
            String header = factsModel.getTitle();
            String description = factsModel.getDescription();
            if (header.equals("") && description == null)
                iterator.remove();
        }
        return listFactsModel;
    }
    public LiveData<List<FactsModel>> getFacts() {
        return factsRepository.getFacts();
    }
}
