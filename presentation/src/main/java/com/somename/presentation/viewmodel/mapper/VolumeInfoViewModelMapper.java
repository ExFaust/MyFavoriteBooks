package com.somename.presentation.viewmodel.mapper;

import com.somename.data.repository.datasource.mapper.Mapper;
import com.somename.domain.model.VolumeInfo;
import com.somename.presentation.viewmodel.VolumeInfoViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VolumeInfoViewModelMapper extends Mapper<VolumeInfo, VolumeInfoViewModel> {

    private ImageLinksViewModelMapper mImageLinksViewModelMapper;

    @Inject
    public VolumeInfoViewModelMapper(ImageLinksViewModelMapper imageLinksViewModelMapper) {
        mImageLinksViewModelMapper = imageLinksViewModelMapper;
    }


    @Override
    public VolumeInfoViewModel map(VolumeInfo value) {
        VolumeInfoViewModel volumeInfoViewModel = new VolumeInfoViewModel();
        volumeInfoViewModel.setAuthors(value.getAuthors());
        volumeInfoViewModel.setAverageRating(value.getAverageRating());
        volumeInfoViewModel.setCategories(value.getCategories());
        volumeInfoViewModel.setDescription(value.getDescription());
        volumeInfoViewModel.setImageLinksViewModel(mImageLinksViewModelMapper.map(value.getImageLinks()));
        volumeInfoViewModel.setPublishedDate(value.getPublishedDate());
        volumeInfoViewModel.setPublisher(value.getPublisher());
        volumeInfoViewModel.setTitle(value.getTitle());
        return volumeInfoViewModel;
    }

    @Override
    public VolumeInfo reverseMap(VolumeInfoViewModel value) {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setAuthors(value.getAuthors());
        volumeInfo.setAverageRating(value.getAverageRating());
        volumeInfo.setCategories(value.getCategories());
        volumeInfo.setDescription(value.getDescription());
        volumeInfo.setImageLinks(mImageLinksViewModelMapper.reverseMap(value.getImageLinksViewModel()));
        volumeInfo.setPublishedDate(value.getPublishedDate());
        volumeInfo.setPublisher(value.getPublisher());
        volumeInfo.setTitle(value.getTitle());
        return volumeInfo;
    }
}
