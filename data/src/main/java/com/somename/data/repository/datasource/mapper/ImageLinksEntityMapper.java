package com.somename.data.repository.datasource.mapper;

import com.somename.data.content.ImageLinksEntity;
import com.somename.domain.model.ImageLinks;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageLinksEntityMapper extends Mapper<ImageLinks, ImageLinksEntity> {

    @Inject
    public ImageLinksEntityMapper() {
    }

    @Override
    public ImageLinksEntity map(ImageLinks value) {
        ImageLinksEntity imageLinksEntity = new ImageLinksEntity();
        imageLinksEntity.setSmallThumbnail(value.getSmallThumbnail());
        imageLinksEntity.setThumbnail(value.getThumbnail());
        return imageLinksEntity;
    }

    @Override
    public ImageLinks reverseMap(ImageLinksEntity value) {
        ImageLinks imageLinks = new ImageLinks();
        imageLinks.setSmallThumbnail(value.getSmallThumbnail());
        imageLinks.setThumbnail(value.getThumbnail());
        return imageLinks;
    }
}
