package com.somename.data.repository.datasource;


import com.somename.data.database.LocalRealmImpl;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class MoviesRealmDataSourceFactory {

    private LocalRealmImpl mLocalRealm;

    @Inject
    public MoviesRealmDataSourceFactory(@NonNull LocalRealmImpl localRealm) {
        mLocalRealm = localRealm;
    }

    public RealmDataSource createDataSource() {
        return new MoviesRealmDataSource(mLocalRealm);
    }
}
