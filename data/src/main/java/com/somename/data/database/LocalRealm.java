package com.somename.data.database;


import com.somename.data.content.BookEntity;

import java.util.List;

import io.reactivex.Observable;

public interface LocalRealm {

    Observable<List<BookEntity>> remove(BookEntity bookEntity);

    Observable<Boolean> add(BookEntity bookEntity);

    Observable<List<BookEntity>> load();

    Observable<List<String>> isExist(List<BookEntity> bookEntities);
}
