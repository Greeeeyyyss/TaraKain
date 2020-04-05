package com.tokoy.tosa.tarakain.di.components

import android.app.Application
import com.tokoy.tosa.tarakain.TKApplication
import com.tokoy.tosa.tarakain.di.modules.ActivityModule
import com.tokoy.tosa.tarakain.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(tkApplication: TKApplication)
}