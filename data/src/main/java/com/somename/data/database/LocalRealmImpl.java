package com.somename.data.database;


import com.somename.data.BuildConfig;
import com.somename.data.content.BookEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.RealmResults;

public class LocalRealmImpl implements LocalRealm{

    private final Realm mRealm;

    public LocalRealmImpl(@NonNull Realm realm) {
        mRealm = realm;
    }

    @Override
    public Observable<List<BookEntity>> remove(BookEntity bookEntity) {
        return Observable.create(emitter -> {
            removeFromRealm(bookEntity);
            emitter.onNext(loadFromRealm());
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Boolean> add(BookEntity bookEntity) {
        return Observable.create(emitter -> {
            addToRealm(bookEntity);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<BookEntity>> load() {
        return Observable.create(emitter -> {
            emitter.onNext(loadFromRealm());
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<String>> isExist(List<BookEntity> bookEntities) {
        return Observable.create(emitter -> {
            emitter.onNext(isExistInRealm(bookEntities));
            emitter.onComplete();
        });
    }

    private void removeFromRealm(BookEntity bookEntity) {
        mRealm.executeTransaction(realm1 -> {
            RealmResults<BookEntity> result = realm1.where(BookEntity.class).equalTo(BuildConfig.ID_FIELD, bookEntity.getId()).findAll();
            result.deleteAllFromRealm();
        });
    }

    private void addToRealm(BookEntity bookEntity) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(bookEntity);
        mRealm.commitTransaction();
    }

    public List<String> isExistInRealm(List<BookEntity> movieEntities) {
        List<String> ids = new ArrayList<>();
        for (BookEntity bookEntity : movieEntities)
            if (mRealm.where(BookEntity.class).contains(BuildConfig.ID_FIELD, bookEntity.getId()).count() != 0)
                ids.add(bookEntity.getId());
        return ids;
    }

    public List<BookEntity> loadFromRealm() {
        return mRealm.where(BookEntity.class).findAll();
    }
}
