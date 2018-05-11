package com.somename.presentation.viewmodel.mapper;

import com.somename.data.repository.datasource.mapper.Mapper;
import com.somename.domain.model.ImageLinks;
import com.somename.presentation.viewmodel.ImageLinksViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageLinksViewModelMapper extends Mapper<ImageLinks, ImageLinksViewModel> {

    @Inject
    public ImageLinksViewModelMapper() {
    }

    @Override
    public ImageLinksViewModel map(ImageLinks value) {
        ImageLinksViewModel linksViewModel = new ImageLinksViewModel();
        linksViewModel.setSmallThumbnail(value.getSmallThumbnail());
        linksViewModel.setThumbnail(value.getThumbnail());
        return linksViewModel;
    }

    @Override
    public ImageLinks reverseMap(ImageLinksViewModel value) {
        ImageLinks imageLinks = new ImageLinks();
        imageLinks.setSmallThumbnail(value.getSmallThumbnail());
        imageLinks.setThumbnail(value.getThumbnail());
        return imageLinks;
    }
}
