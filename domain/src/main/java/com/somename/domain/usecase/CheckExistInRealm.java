package com.somename.domain.usecase;

import com.somename.domain.DBRepository;
import com.somename.domain.model.Book;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class CheckExistInRealm extends UseCase<List<String>> {

    private final DBRepository mDbRepository;

    private List<Book> mBooks;

    @Inject
    public CheckExistInRealm(@Named("ui_thread") Scheduler executorThread,
                             @Named("ui_thread") Scheduler uiThread, DBRepository dbRepository) {
        super(executorThread, uiThread);
        mDbRepository = dbRepository;
    }

    public void checkExistInRealm(List<Book> books) {
        mBooks = books;
    }

    @Override
    public Observable<List<String>> createObservableUseCase() {
        return mDbRepository.isExist(mBooks);
    }
}
