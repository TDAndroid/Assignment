package com.td.test.topfacts.uicomponents.facts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class FactsListAdapter extends RecyclerView.Adapter<FactsListAdapter.FactsViewHolder> {


    @NonNull
    @Override
    public FactsListAdapter.FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FactsListAdapter.FactsViewHolder factsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FactsViewHolder extends RecyclerView.ViewHolder {
        public FactsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
