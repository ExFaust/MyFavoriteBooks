package com.somename.presentation.screen.presenter;

import com.somename.domain.model.Book;
import com.somename.domain.usecase.LoadFromRealm;
import com.somename.domain.usecase.RemoveFromRealm;
import com.somename.presentation.viewmodel.BookViewModel;
import com.somename.presentation.viewmodel.mapper.BookViewModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


public class FavoriteActivityPresenter extends Presenter<FavoriteActivityPresenter.View> {

    private final LoadFromRealm mLoadFromRealm;
    private final RemoveFromRealm mRemoveFromRealm;
    private BookViewModelMapper mBookViewModelMapper;

    @Inject
    public FavoriteActivityPresenter(@NonNull LoadFromRealm loadFromRealm, @NonNull RemoveFromRealm removeFromRealm,
                                     @NonNull BookViewModelMapper bookViewModelMapper) {
        mLoadFromRealm = loadFromRealm;
        mRemoveFromRealm = removeFromRealm;
        mBookViewModelMapper = bookViewModelMapper;
    }

    public void loadFromRealm() {
        getView().showLoading();
        mLoadFromRealm.execute(new DisposableObserver<List<Book>>() {
            @Override
            public void onNext(List<Book> books) {
                getView().showBooks(mBookViewModelMapper.map(books));
            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
                getView().hideLoading();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void deleteFromRealm(BookViewModel bookViewModel){
        mRemoveFromRealm.removeFromRealm(mBookViewModelMapper.reverseMap(bookViewModel));
        mRemoveFromRealm.execute(new DisposableObserver<List<Book>>() {
            @Override
            public void onNext(List<Book> books) {
                getView().showBooks(mBookViewModelMapper.map(books));
            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
            }

            @Override
            public void onComplete(){
            }
        });
    }

    public void onResume() {

    }

    public void onDestroy() {
        mRemoveFromRealm.dispose();
        mLoadFromRealm.dispose();
    }

    public interface View extends Presenter.View {

        void showBooks(List<BookViewModel> bookViewModels);
    }
}
