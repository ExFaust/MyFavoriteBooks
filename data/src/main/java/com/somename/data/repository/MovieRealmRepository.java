package com.somename.data.repository;


import android.support.annotation.NonNull;

import com.somename.data.repository.datasource.MoviesRealmDataSourceFactory;
import com.somename.data.repository.datasource.RealmDataSource;
import com.somename.data.repository.datasource.mapper.BookEntityMapper;
import com.somename.domain.DBRepository;
import com.somename.domain.model.Book;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRealmRepository implements DBRepository {

    private final RealmDataSource mDataSource;
    private final BookEntityMapper mBookEntityMapper;

    @Inject
    MovieRealmRepository(@NonNull MoviesRealmDataSourceFactory moviesRealmDataSourceFactory, @NonNull BookEntityMapper bookEntityMapper) {
        mDataSource = moviesRealmDataSourceFactory.createDataSource();
        mBookEntityMapper = bookEntityMapper;
    }

    @Override
    public Observable<List<Book>> remove(Book book) {
        return mDataSource.remove(mBookEntityMapper.map(book)).map(mBookEntityMapper::reverseMap);
    }

    @Override
    public Observable<Boolean> add(Book book) {
        return mDataSource.add(mBookEntityMapper.map(book));
    }

    @Override
    public Observable<List<Book>> load() {
        return mDataSource.load().map(mBookEntityMapper::reverseMap);
    }

    @Override
    public Observable<List<String>> isExist(List<Book> books) {
        return mDataSource.isExist(mBookEntityMapper.map(books));
    }
}
