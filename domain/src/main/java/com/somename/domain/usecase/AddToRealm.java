package com.somename.domain.usecase;

import com.somename.domain.DBRepository;
import com.somename.domain.model.Book;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class AddToRealm extends UseCase<Boolean> {

    private final DBRepository mDbRepository;

    private Book mBook;

    @Inject
    public AddToRealm(@Named("ui_thread") Scheduler executorThread,
                           @Named("ui_thread") Scheduler uiThread, DBRepository dbRepository) {
        super(executorThread, uiThread);
        mDbRepository = dbRepository;
    }

    public void addToRealm(Book book) {
        mBook = book;
    }

    @Override
    public Observable<Boolean> createObservableUseCase() {
        return mDbRepository.add(mBook);
    }
}
