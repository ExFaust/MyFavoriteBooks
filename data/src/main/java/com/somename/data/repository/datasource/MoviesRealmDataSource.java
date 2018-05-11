package com.somename.data.repository.datasource;

import com.somename.data.content.BookEntity;
import com.somename.data.database.LocalRealm;

import java.util.List;

import io.reactivex.Observable;

public class MoviesRealmDataSource implements RealmDataSource{

    private final LocalRealm mLocalRealm;

    public MoviesRealmDataSource(LocalRealm localRealm) {
        mLocalRealm = localRealm;
    }

    @Override
    public Observable<List<BookEntity>> remove(BookEntity bookEntity) {
        return mLocalRealm.remove(bookEntity);
    }

    @Override
    public Observable<Boolean> add(BookEntity bookEntity) {
        return mLocalRealm.add(bookEntity);
    }

    @Override
    public Observable<List<BookEntity>> load() {
        return mLocalRealm.load();
    }

    @Override
    public Observable<List<String>> isExist(List<BookEntity> bookEntities) {
        return mLocalRealm.isExist(bookEntities);
    }
}
