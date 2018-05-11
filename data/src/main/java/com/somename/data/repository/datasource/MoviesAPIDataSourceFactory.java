package com.somename.data.repository.datasource;

import android.support.annotation.NonNull;

import com.somename.data.network.LocalApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoviesAPIDataSourceFactory {

    private LocalApi mLocalAPI;

    @Inject
    public MoviesAPIDataSourceFactory(@NonNull LocalApi localApi) {
        mLocalAPI = localApi;
    }

    public DataSource createDataSource() {
        return new MoviesAPIDataSource(mLocalAPI);
    }
}
