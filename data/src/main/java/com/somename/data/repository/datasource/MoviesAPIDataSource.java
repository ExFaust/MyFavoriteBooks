package com.somename.data.repository.datasource;

import com.somename.data.content.RootSearchEntity;
import com.somename.data.network.LocalApi;

import io.reactivex.Observable;

public class MoviesAPIDataSource implements DataSource {

    private final LocalApi mLocalApi;

    public MoviesAPIDataSource(LocalApi localApi) {
        mLocalApi = localApi;
    }

    @Override
    public Observable<RootSearchEntity> bookEntityList(String bookName) {
        return mLocalApi.booksEntityList(bookName);
    }
}
