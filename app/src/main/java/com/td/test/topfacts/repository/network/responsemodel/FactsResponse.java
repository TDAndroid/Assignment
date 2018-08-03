package com.td.test.topfacts.repository.network.responsemodel;

import com.td.test.topfacts.repository.database.model.FactsModel;

import java.util.List;

public class FactsResponse {

    private String title;
    private List<FactsModel> factsModel;

    public FactsResponse(String title, List<FactsModel> factsModel) {
        this.title = title;
        this.factsModel = factsModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FactsModel> getFactsModel() {
        return factsModel;
    }

    public void setFactsModel(List<FactsModel> factsModel) {
        this.factsModel = factsModel;
    }

}
