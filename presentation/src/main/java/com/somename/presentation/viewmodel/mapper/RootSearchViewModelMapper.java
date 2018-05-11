package com.somename.presentation.viewmodel.mapper;


import com.somename.data.repository.datasource.mapper.Mapper;
import com.somename.data.repository.datasource.mapper.VolumeInfoEntityMapper;
import com.somename.domain.model.Book;
import com.somename.domain.model.RootSearch;
import com.somename.presentation.viewmodel.BookViewModel;
import com.somename.presentation.viewmodel.RootSearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RootSearchViewModelMapper extends Mapper<RootSearchViewModel,RootSearch> {

    private VolumeInfoViewModelMapper mVolumeInfoViewModelMapper;

    @Inject
    RootSearchViewModelMapper(VolumeInfoViewModelMapper volumeInfoViewModelMapper) {
        mVolumeInfoViewModelMapper=volumeInfoViewModelMapper;
    }


    @Override
    public RootSearch map(RootSearchViewModel value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RootSearchViewModel reverseMap(RootSearch rootSearch) {
        RootSearchViewModel rootSearchViewModel = new RootSearchViewModel();
        List<BookViewModel> bookViewModels = new ArrayList<>();
        for (Book book : rootSearch.getBooks()) {
            BookViewModel bookViewModel = new BookViewModel();
            bookViewModel.setId(book.getId());
            bookViewModel.setVolumeInfoViewModel(mVolumeInfoViewModelMapper.map(book.getVolumeInfo()));
            bookViewModels.add(bookViewModel);
        }
        rootSearchViewModel.setBookViewModels(bookViewModels);
        return rootSearchViewModel;
    }
}
