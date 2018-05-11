package com.somename.domain.usecase;


import com.somename.domain.NetworkRepository;
import com.somename.domain.model.RootSearch;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetBooksBySearch extends UseCase<RootSearch> {

    private final NetworkRepository mNetworkRepository;

    private String mBookName = "";

    @Inject
    public GetBooksBySearch(@Named("executor_thread") Scheduler executorThread,
                            @Named("ui_thread") Scheduler uiThread, NetworkRepository repository) {
        super(executorThread, uiThread);
        mNetworkRepository = repository;
    }

    @Override public Observable<RootSearch> createObservableUseCase() {
        return this.mNetworkRepository.searchBook(mBookName);
    }

    public void getMoviesByName(String movieName) {
        mBookName = movieName;
    }
}
