package com.td.test.topfacts.uicomponents.facts;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.td.test.topfacts.R;
import com.td.test.topfacts.repository.database.model.FactsModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FactsListAdapter extends RecyclerView.Adapter<FactsListAdapter.FactsViewHolder> {

    private List<FactsModel> listFactModel;

    public FactsListAdapter(List<FactsModel> listFactsModel) {
        this.listFactModel = listFactsModel;
    }

    @NonNull
    @Override
    public FactsListAdapter.FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_facts_list,
                viewGroup, false);
        return new FactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactsListAdapter.FactsViewHolder factsViewHolder, int i) {
        FactsModel factsModel = listFactModel.get(i);
        factsViewHolder.tvHeader.setText(factsModel.getTitle());
        factsViewHolder.tvDescription.setText(factsModel.getDescription());
        String imageUrl = factsModel.getImageHref();
        if (imageUrl == null) {
            factsViewHolder.ivFactsImage.setVisibility(View.GONE);
        } else {
            Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.ic_image_offline)
                    .error(R.drawable.ic_image_offline)
                    .fit()
                    .into(factsViewHolder.ivFactsImage);
        }
    }

    @Override
    public int getItemCount() {
        return listFactModel.size();
    }

    public class FactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFactsHeader)
        TextView tvHeader;

        @BindView(R.id.tvFactDescription)
        TextView tvDescription;

        @BindView(R.id.ivFactsImage)
        AppCompatImageView ivFactsImage;

        public FactsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
