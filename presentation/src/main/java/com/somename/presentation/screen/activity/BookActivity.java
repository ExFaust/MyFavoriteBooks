package com.somename.presentation.screen.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.somename.presentation.BooksApplication;
import com.somename.presentation.R;
import com.somename.presentation.general.LoadingDialog;
import com.somename.presentation.general.LoadingView;
import com.somename.presentation.screen.presenter.BookActivityPresenter;
import com.somename.presentation.viewmodel.BookViewModel;

import javax.inject.Inject;

public class BookActivity extends AppCompatActivity implements BookActivityPresenter.View {

    private LoadingView mLoadingView;

    private TextView mTextViewTitle;

    private TextView mTextViewAuthors;

    private TextView mTextViewRating;

    private TextView mTextViewPublisher;

    private TextView mTextViewDescription;

    private TextView mTextViewPublisherDate;

    private TextView mTextViewCategories;

    private ImageView mImageView;

    private ProgressBar mProgressBar;

    @Inject
    BookActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        mTextViewTitle = findViewById(R.id.item_title);
        mTextViewRating = findViewById(R.id.item_rating);
        mTextViewPublisher = findViewById(R.id.item_publisher);
        mTextViewDescription = findViewById(R.id.item_description);
        mTextViewAuthors = findViewById(R.id.item_authors);
        mTextViewPublisherDate = findViewById(R.id.item_publisher_date);
        mTextViewCategories = findViewById(R.id.item_categories);
        mImageView = findViewById(R.id.item_image);
        mProgressBar = findViewById(R.id.item_placeholder);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        BooksApplication app = (BooksApplication) getApplication();
        app.getMainComponent().inject(this);

        mPresenter.setView(this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BookViewModel bookViewModel = gson.fromJson(getIntent().getStringExtra("book"), BookViewModel.class);
        mPresenter.init(bookViewModel);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showError() {
        Toast.makeText(this,getString(R.string.connection_error),Toast.LENGTH_LONG).show();
        mLoadingView.hideLoading();
    }

    @Override
    public void showBook(@NonNull BookViewModel bookViewModel) {
        mTextViewTitle.setText(bookViewModel.getVolumeInfoViewModel().getTitle());
        mTextViewAuthors.setText(bookViewModel.getVolumeInfoViewModel().getAuthors());
        mTextViewPublisher.setText(bookViewModel.getVolumeInfoViewModel().getPublisher());
        mTextViewDescription.setText(bookViewModel.getVolumeInfoViewModel().getDescription());
        mTextViewRating.setText(bookViewModel.getVolumeInfoViewModel().getAverageRating());
        mTextViewPublisherDate.setText(bookViewModel.getVolumeInfoViewModel().getPublishedDate());
        mTextViewCategories.setText(bookViewModel.getVolumeInfoViewModel().getCategories());

        Glide
                .with(this)
                .load(bookViewModel.getVolumeInfoViewModel().getImageLinksViewModel().getThumbnail())
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
                .into(mImageView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, bookViewModel.getVolumeInfoViewModel().getTitle());
            sharingIntent.putExtra(Intent.EXTRA_TEXT, bookViewModel.getVolumeInfoViewModel().getTitle());
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //navigateUpTo(new Intent(this, ScrollingActivity.class));
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
