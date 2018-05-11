package com.somename.presentation.viewmodel.mapper;

import com.somename.data.repository.datasource.mapper.Mapper;
import com.somename.domain.model.Book;
import com.somename.presentation.viewmodel.BookViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookViewModelMapper extends Mapper<Book, BookViewModel> {

    private VolumeInfoViewModelMapper mVolumeInfoViewModelMapper;

    @Inject
    public BookViewModelMapper(VolumeInfoViewModelMapper volumeInfoViewModelMapper) {
        mVolumeInfoViewModelMapper = volumeInfoViewModelMapper;
    }

    @Override
    public BookViewModel map(Book book) {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setId(book.getId());
        bookViewModel.setVolumeInfoViewModel(mVolumeInfoViewModelMapper.map(book.getVolumeInfo()));
        return bookViewModel;
    }

    @Override
    public Book reverseMap(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setId(bookViewModel.getId());
        book.setVolumeInfo(mVolumeInfoViewModelMapper.reverseMap(bookViewModel.getVolumeInfoViewModel()));
        return book;
    }
}
