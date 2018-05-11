package com.somename.data.repository.datasource.mapper;


import com.somename.data.content.VolumeInfoEntity;
import com.somename.domain.model.VolumeInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VolumeInfoEntityMapper extends Mapper<VolumeInfo, VolumeInfoEntity> {

    private ImageLinksEntityMapper mImageLinksEntityMapper;

    @Inject
    public VolumeInfoEntityMapper(ImageLinksEntityMapper imageLinksEntityMapper) {
        mImageLinksEntityMapper=imageLinksEntityMapper;
    }


    @Override
    public VolumeInfoEntity map(VolumeInfo value) {
        VolumeInfoEntity volumeInfoEntity = new VolumeInfoEntity();
        volumeInfoEntity.setAuthors(volumeInfoEntity.toArray(value.getAuthors()));
        volumeInfoEntity.setAverageRating(value.getAverageRating());
        volumeInfoEntity.setCategories(volumeInfoEntity.toArray(value.getCategories()));
        volumeInfoEntity.setDescription(value.getDescription());
        volumeInfoEntity.setImageLinksEntity(mImageLinksEntityMapper.map(value.getImageLinks()));
        volumeInfoEntity.setPublishedDate(value.getPublishedDate());
        volumeInfoEntity.setPublisher(value.getPublisher());
        volumeInfoEntity.setTitle(value.getTitle());
        return volumeInfoEntity;
    }

    @Override
    public VolumeInfo reverseMap(VolumeInfoEntity value) {
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setAuthors(value.toString(value.getAuthors()));
        volumeInfo.setAverageRating(value.getAverageRating());
        volumeInfo.setCategories(value.toString(value.getCategories()));
        volumeInfo.setDescription(value.getDescription());
        volumeInfo.setImageLinks(mImageLinksEntityMapper.reverseMap(value.getImageLinksEntity()));
        volumeInfo.setPublishedDate(value.getPublishedDate());
        volumeInfo.setPublisher(value.getPublisher());
        volumeInfo.setTitle(value.getTitle());
        return volumeInfo;
    }
}
