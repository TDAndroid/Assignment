package com.td.test.topfacts.repository.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.td.test.topfacts.repository.database.model.FactsModel;

import java.util.List;

@Dao
public interface FactsModelDao {

    /**
     * Insert or replace table values
     *
     * @param facts
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertFacts(List<FactsModel> facts);

    @Query("SELECT * FROM facts_table")
    LiveData<List<FactsModel>> getFacts();
}
