package com.td.test.topfacts.uicomponents.facts;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.td.test.topfacts.R;
import com.td.test.topfacts.repository.database.model.FactsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FactsActivity extends AppCompatActivity implements LifecycleOwner{

    @BindView(R.id.rvFactsList)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FactsViewModel factsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        factsViewModel = ViewModelProviders.of(this).get(FactsViewModel.class);
        factsViewModel.getFacts().observe(this, new Observer<List<FactsModel>>() {
            @Override
            public void onChanged(@Nullable List<FactsModel> factsModels) {
                Log.d("TAG", factsModels.size()+"");
                recyclerView.setAdapter(factsViewModel.getAdapter(factsModels));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
