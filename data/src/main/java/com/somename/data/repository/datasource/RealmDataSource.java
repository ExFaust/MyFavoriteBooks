package com.somename.data.repository.datasource;

import com.somename.data.content.BookEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RealmDataSource {

    Observable<List<BookEntity>> remove(BookEntity bookEntity);

    Observable<Boolean> add(BookEntity movie);

    Observable<List<BookEntity>> load();

    Observable<List<String>> isExist(List<BookEntity> bookEntities);
}
