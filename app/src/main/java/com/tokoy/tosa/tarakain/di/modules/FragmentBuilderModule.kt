package com.tokoy.tosa.tarakain.di.modules

import com.tokoy.tosa.tarakain.ui.AddStoreFragment
import com.tokoy.tosa.tarakain.ui.EditStoreFragment
import com.tokoy.tosa.tarakain.ui.HeadsOrTailsFragment
import com.tokoy.tosa.tarakain.ui.SplashFragment
import com.tokoy.tosa.tarakain.ui.StoreListFragment
import com.tokoy.tosa.tarakain.ui.StoreOfTheDayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeAddStoreFragment(): AddStoreFragment

    @ContributesAndroidInjector
    abstract fun contributeEditStoreFragment(): EditStoreFragment

    @ContributesAndroidInjector
    abstract fun contributeHeadsOrTailsFragment(): HeadsOrTailsFragment

    @ContributesAndroidInjector
    abstract fun contributeStoreListFragment(): StoreListFragment

    @ContributesAndroidInjector
    abstract fun contributeStoreOfTheDayFragment(): StoreOfTheDayFragment

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment
}