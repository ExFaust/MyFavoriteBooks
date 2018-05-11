package com.somename.data.repository;

import com.somename.data.repository.datasource.DataSource;
import com.somename.data.repository.datasource.MoviesAPIDataSourceFactory;
import com.somename.data.repository.datasource.mapper.BookEntityMapper;
import com.somename.data.repository.datasource.mapper.RootSearchEntityMapper;
import com.somename.domain.NetworkRepository;
import com.somename.domain.model.Book;
import com.somename.domain.model.RootSearch;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

@Singleton
public class MovieNetworkRepository implements NetworkRepository {

    private final DataSource mDataSource;
    private final RootSearchEntityMapper mRootSearchEntityMapper;

    @Inject
    MovieNetworkRepository(@NonNull MoviesAPIDataSourceFactory moviesAPIDataSourceFactory, @NonNull RootSearchEntityMapper rootSearchEntityMapper) {
        mDataSource = moviesAPIDataSourceFactory.createDataSource();
        mRootSearchEntityMapper = rootSearchEntityMapper;
    }

    @Override
    public Observable<RootSearch> searchBook(String bookName) {
        return mDataSource.bookEntityList(bookName).map(mRootSearchEntityMapper::reverseMap);
    }
}
