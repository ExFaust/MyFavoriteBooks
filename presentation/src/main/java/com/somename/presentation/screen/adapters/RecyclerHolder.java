package com.somename.presentation.screen.adapters;


import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.somename.presentation.R;
import com.somename.presentation.viewmodel.BookViewModel;

public class RecyclerHolder extends RecyclerView.ViewHolder{

    private TextView mTitle;
    private TextView mAuthors;
    private TextView mRating;
    private ImageView mImage;
    private View mItemView;
    private ProgressBar mProgressBar;

    public RecyclerHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;
        mTitle = itemView.findViewById(R.id.item_title);
        mRating = itemView.findViewById(R.id.item_rating);
        mAuthors = itemView.findViewById(R.id.item_authors);
        mImage = itemView.findViewById(R.id.item_image);
        mProgressBar = itemView.findViewById(R.id.item_placeholder);
    }

    public void bind(@NonNull BookViewModel bookViewModel) {
        mTitle.setText(bookViewModel.getVolumeInfoViewModel().getTitle());
        mAuthors.setText(bookViewModel.getVolumeInfoViewModel().getAuthors());
        mRating.setText(bookViewModel.getVolumeInfoViewModel().getAverageRating());

        Glide
                .with(mItemView)
                .load( bookViewModel.getVolumeInfoViewModel().getImageLinksViewModel().getSmallThumbnail())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mImage);

    }
}
