package com.somename.presentation.di;

import com.somename.presentation.screen.activity.BookActivity;
import com.somename.presentation.screen.activity.FavoriteActivity;
import com.somename.presentation.screen.activity.ScrollingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class) public interface MainComponent {

    void inject(ScrollingActivity scrollingActivity);

    void inject(FavoriteActivity favoriteActivity);

    void inject(BookActivity bookActivity);
}
