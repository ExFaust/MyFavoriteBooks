package com.somename.data.repository.datasource.mapper;


import com.somename.data.content.BookEntity;
import com.somename.data.content.RootSearchEntity;
import com.somename.domain.model.Book;
import com.somename.domain.model.RootSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RootSearchEntityMapper extends Mapper<RootSearch, RootSearchEntity> {

    private VolumeInfoEntityMapper mVolumeInfoEntityMapper;

    @Inject
    public RootSearchEntityMapper(VolumeInfoEntityMapper volumeInfoEntityMapper) {
        mVolumeInfoEntityMapper=volumeInfoEntityMapper;
    }

    @Override
    public RootSearchEntity map(RootSearch rootSearch) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RootSearch reverseMap(RootSearchEntity rootSearchEntity) {
        RootSearch rootSearch = new RootSearch();
        List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : rootSearchEntity.getBooks()) {
            Book book = new Book();
            book.setId(bookEntity.getId());
            book.setVolumeInfo(mVolumeInfoEntityMapper.reverseMap(bookEntity.getVolumeInfoEntity()));
            books.add(book);
        }
        rootSearch.setBooks(books);
        return rootSearch;
    }
}
