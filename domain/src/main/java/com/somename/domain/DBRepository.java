package com.somename.domain;

import com.somename.domain.model.Book;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface DBRepository {

    @NonNull
    Observable<List<Book>> remove(Book book);

    @NonNull
    Observable<Boolean> add(Book book);

    @NonNull
    Observable<List<Book>> load();

    @NonNull
    Observable<List<String>> isExist(List<Book> books);

}
