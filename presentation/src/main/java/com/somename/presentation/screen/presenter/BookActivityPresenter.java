package com.somename.presentation.screen.presenter;



import com.somename.presentation.viewmodel.BookViewModel;
import javax.inject.Inject;

public class BookActivityPresenter extends Presenter<BookActivityPresenter.View> {

    @Inject
    public BookActivityPresenter() {
    }

    public void init(BookViewModel bookViewModel) {
        getView().showBook(bookViewModel);
    }

    public interface View extends Presenter.View {

        void showBook(BookViewModel bookViewModel);
    }
}
