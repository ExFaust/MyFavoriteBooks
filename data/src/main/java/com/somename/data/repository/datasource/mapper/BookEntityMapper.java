package com.somename.data.repository.datasource.mapper;

import com.somename.data.content.BookEntity;
import com.somename.domain.model.Book;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookEntityMapper extends Mapper<Book, BookEntity> {

    private VolumeInfoEntityMapper mVolumeInfoEntityMapper;

    @Inject
    public BookEntityMapper(VolumeInfoEntityMapper volumeInfoEntityMapper) {
        mVolumeInfoEntityMapper=volumeInfoEntityMapper;
    }

    @Override
    public BookEntity map(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setVolumeInfoEntity(mVolumeInfoEntityMapper.map(book.getVolumeInfo()));
        return bookEntity;
    }

    @Override
    public Book reverseMap(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setVolumeInfo(mVolumeInfoEntityMapper.reverseMap(bookEntity.getVolumeInfoEntity()));
        return book;
    }
}
