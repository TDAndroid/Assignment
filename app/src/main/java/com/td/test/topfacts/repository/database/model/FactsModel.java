package com.td.test.topfacts.repository.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.td.test.topfacts.util.AppConstants;

@Entity(tableName = AppConstants.APP_DB_FACTS_TABLE)
public class FactsModel {

    @PrimaryKey
    @NonNull
    private String title;
    private String description;
    private String imageHref;

    public String getTitle() {
        if (title == null)
            title = "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
