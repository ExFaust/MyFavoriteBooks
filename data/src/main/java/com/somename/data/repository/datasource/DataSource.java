package com.somename.data.repository.datasource;


import com.somename.data.content.RootSearchEntity;

import io.reactivex.Observable;

public interface DataSource {

    Observable<RootSearchEntity> bookEntityList(final String movieName);
}
