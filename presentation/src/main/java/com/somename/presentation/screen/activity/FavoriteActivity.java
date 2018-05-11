package com.somename.presentation.screen.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.somename.presentation.BooksApplication;
import com.somename.presentation.R;
import com.somename.presentation.general.LoadingDialog;
import com.somename.presentation.general.LoadingView;
import com.somename.presentation.screen.adapters.FavoriteRecyclerAdapter;
import com.somename.presentation.screen.presenter.FavoriteActivityPresenter;
import com.somename.presentation.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class FavoriteActivity extends AppCompatActivity implements FavoriteRecyclerAdapter.OnClickListener, FavoriteActivityPresenter.View{

    private RecyclerView mRecyclerView;

    private LoadingView mLoadingView;

    private FavoriteRecyclerAdapter mAdapter;

    @Inject
    FavoriteActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FavoriteRecyclerAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnClickListener(this);

        BooksApplication app = (BooksApplication) getApplication();
        app.getMainComponent().inject(this);

        mPresenter.setView(this);
        mPresenter.loadFromRealm();
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
        Toast.makeText(this,getString(R.string.connection_error), Toast.LENGTH_LONG).show();
        mLoadingView.hideLoading();
    }

    @Override
    public void showBooks(@NonNull List<BookViewModel> movieViewModels) {
        mAdapter.changeDataSet(movieViewModels);
    }

    @Override
    public void onClick(@NonNull int position) {
        mPresenter.deleteFromRealm(mAdapter.getItem(position));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
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
