package com.somename.presentation.screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.somename.presentation.BooksApplication;
import com.somename.presentation.R;
import com.somename.presentation.general.LoadingDialog;
import com.somename.presentation.general.LoadingView;
import com.somename.presentation.screen.adapters.BaseAdapter;
import com.somename.presentation.screen.adapters.RecyclerAdapter;
import com.somename.presentation.screen.presenter.ScrollingActivityPresenter;
import com.somename.presentation.viewmodel.BookViewModel;
import com.somename.presentation.viewmodel.RootSearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ScrollingActivity extends AppCompatActivity implements BaseAdapter.OnItemClickListener, RecyclerAdapter.OnClickListener, ScrollingActivityPresenter.View{

    private RecyclerView mRecyclerView;

    private LoadingView mLoadingView;

    private RecyclerAdapter mAdapter;

    @Inject
    ScrollingActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BooksApplication app = (BooksApplication) getApplication();
        app.getMainComponent().inject(this);

        mPresenter.setView(this);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RecyclerAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnClickListener(this);
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
        mLoadingView.hideLoading();
        Toast.makeText(this,getString(R.string.connection_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBooks(@NonNull RootSearchViewModel rootSearchViewModel) {
        mAdapter.changeDataSet(rootSearchViewModel.getBookViewModels());
        mPresenter.isExistInRealm(rootSearchViewModel.getBookViewModels());
    }

    @Override
    public void showFavoriteBooks(List<String> ids) {
        mAdapter.setIds(ids);
        mAdapter.refreshRecycler();
    }

    @Override
    public void onItemClick(@NonNull int position){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Intent intent = new Intent(this,BookActivity.class);
        intent.putExtra("book", gson.toJson(mAdapter.getItem(position)));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchview, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                mPresenter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(this,FavoriteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(@NonNull int position, ImageButton imageButton) {
        BookViewModel bookViewModel = mAdapter.getItem(position);
        if (!mAdapter.getIds().contains(bookViewModel.getId())) {
            mPresenter.addToRealm(mAdapter.getItem(position));
            imageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        this.mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
    }
}
