package com.somename.presentation.screen.presenter;

import android.support.annotation.NonNull;

import com.somename.domain.model.RootSearch;
import com.somename.domain.usecase.AddToRealm;
import com.somename.domain.usecase.CheckExistInRealm;
import com.somename.domain.usecase.GetBooksBySearch;
import com.somename.presentation.viewmodel.BookViewModel;
import com.somename.presentation.viewmodel.RootSearchViewModel;
import com.somename.presentation.viewmodel.mapper.BookViewModelMapper;
import com.somename.presentation.viewmodel.mapper.RootSearchViewModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class ScrollingActivityPresenter extends Presenter<ScrollingActivityPresenter.View> {

    private final GetBooksBySearch mGetBooksBySearch;
    private final AddToRealm mAddToRealm;
    private final CheckExistInRealm mCheckExistInRealm;
    private String mMovieName = "";
    private RootSearchViewModelMapper mRootSearchViewModelMapper;
    private BookViewModelMapper mBookViewModelMapper;

    @Inject
    public ScrollingActivityPresenter(@NonNull GetBooksBySearch getBooksBySearch, @NonNull RootSearchViewModelMapper rootSearchViewModelMapper,
                                      @NonNull AddToRealm addToRealm, @NonNull CheckExistInRealm checkExistInRealm, @NonNull BookViewModelMapper bookViewModelMapper) {
        mGetBooksBySearch = getBooksBySearch;
        mRootSearchViewModelMapper = rootSearchViewModelMapper;
        mCheckExistInRealm = checkExistInRealm;
        mAddToRealm = addToRealm;
        mBookViewModelMapper = bookViewModelMapper;
    }

    public void search(String movieName) {
        getView().showLoading();
        mMovieName = movieName;
        mGetBooksBySearch.getMoviesByName(mMovieName);
        mGetBooksBySearch.execute(new DisposableObserver<RootSearch>() {

            @Override
            public void onNext(RootSearch rootSearch) {
                RootSearchViewModel rootSearchViewModel = mRootSearchViewModelMapper.reverseMap(rootSearch);
                getView().showBooks(rootSearchViewModel);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                getView().showError();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void addToRealm(BookViewModel bookViewModel) {
        mAddToRealm.addToRealm(mBookViewModelMapper.reverseMap(bookViewModel));
        mAddToRealm.execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void isExistInRealm(List<BookViewModel> bookViewModels) {
        mCheckExistInRealm.checkExistInRealm(mBookViewModelMapper.reverseMap(bookViewModels));
        mCheckExistInRealm.execute(new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                getView().showFavoriteBooks(strings);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void onResume() {
        if (!mMovieName.equals(""))
            search(mMovieName);
    }

    public void onDestroy() {
        mGetBooksBySearch.dispose();
    }

    public interface View extends Presenter.View {

        void showBooks(RootSearchViewModel rootSearchViewModel);

        void showFavoriteBooks(List<String> ids);
    }
}
