package com.somename.domain;


import com.somename.domain.model.RootSearch;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface NetworkRepository {

    @NonNull
    Observable<RootSearch> searchBook(String bookName);

}
