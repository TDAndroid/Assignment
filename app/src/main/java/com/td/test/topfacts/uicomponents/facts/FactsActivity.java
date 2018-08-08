package com.td.test.topfacts.uicomponents.facts;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.td.test.topfacts.R;
import com.td.test.topfacts.repository.database.model.FactsModel;
import com.td.test.topfacts.util.AppConstants;
import com.td.test.topfacts.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FactsActivity extends AppCompatActivity implements LifecycleOwner {

    private final String TAG = FactsActivity.class.getCanonicalName();
    @BindView(R.id.rvFactsList)
    RecyclerView recyclerView;
    @BindView(R.id.appTitle)
    TextView appTitle;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FactsListAdapter adapter;
    private FactsViewModel factsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        ButterKnife.bind(this);
        initUi();
    }

    /**
     * initialize UI
     * if internet connection is not available values fetch from db
     */
    private void initUi() {
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FactsActivity.this));
        factsViewModel = ViewModelProviders.of(FactsActivity.this).get(FactsViewModel.class);
        adapter = factsViewModel.getAdapter();
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnabled(false);
        if (!Util.isNetworkConnected(getApplicationContext())) {
            Toast.makeText(FactsActivity.this, R.string.app_global_network_message, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            setAppTitle();
        }
        factsViewModel.getFacts().observe(this, new Observer<List<FactsModel>>() {
            @Override
            public void onChanged(@Nullable List<FactsModel> factsModels) {
                adapter.updateAdapterItems(factsViewModel.getFilteredFacts(factsModels));
                refreshLayout.setEnabled(true);
                setAppTitle();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                factsViewModel.getFacts().observe(FactsActivity.this, new Observer<List<FactsModel>>() {
                    @Override
                    public void onChanged(@Nullable List<FactsModel> factsModels) {
                        adapter.updateAdapterItems(factsViewModel.getFilteredFacts(factsModels));
                        refreshLayout.setRefreshing(false);
                        setAppTitle();
                    }
                });
            }
        });
    }

    private void setAppTitle() {
        String title = getSharedPreferences(AppConstants.SH_APP, MODE_PRIVATE).getString(
                AppConstants.SH_APP_TITLE, getString(R.string.app_name));
        appTitle.setText(title);
    }
}
