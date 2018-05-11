package com.somename.domain.usecase;


import com.somename.domain.DBRepository;
import com.somename.domain.model.Book;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class RemoveFromRealm extends UseCase<List<Book>> {

    private final DBRepository mDbRepository;

    private Book mBook;

    @Inject
    public RemoveFromRealm(@Named("ui_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, DBRepository dbRepository) {
        super(executorThread, uiThread);
        mDbRepository = dbRepository;
    }

    public void removeFromRealm(Book book) {
        mBook = book;
    }

    @Override public Observable<List<Book>> createObservableUseCase() {
        return mDbRepository.remove(mBook);
    }
}
