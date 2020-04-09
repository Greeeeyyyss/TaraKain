package com.tokoy.tosa.tarakain.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokoy.tosa.tarakain.di.ViewModelKey
import com.tokoy.tosa.tarakain.viewmodels.AddStoreViewModel
import com.tokoy.tosa.tarakain.viewmodels.EditStoreViewModel
import com.tokoy.tosa.tarakain.viewmodels.HeadsOrTailsViewModel
import com.tokoy.tosa.tarakain.viewmodels.StoreListViewModel
import com.tokoy.tosa.tarakain.viewmodels.StoreOfTheDayViewModel
import com.tokoy.tosa.tarakain.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddStoreViewModel::class)
    abstract fun bindAddStoreViewModel(addStoreViewModel: AddStoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditStoreViewModel::class)
    abstract fun bindEditStoreViewModel(editStoreViewModel: EditStoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeadsOrTailsViewModel::class)
    abstract fun bindHeadsOrTailViewModel(headsOrTailsViewModel: HeadsOrTailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoreListViewModel::class)
    abstract fun bindStoreListViewModel(storeListViewModel: StoreListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoreOfTheDayViewModel::class)
    abstract fun bindStoreOfTheDayViewModel(
        storeOfTheDayViewModel: StoreOfTheDayViewModel
    ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}