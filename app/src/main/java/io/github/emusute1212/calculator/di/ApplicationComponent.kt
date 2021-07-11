package io.github.emusute1212.calculator.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.github.emusute1212.calculator.CalcApplication
import io.github.emusute1212.calculator.di.modules.CalcButtonFragmentModule
import io.github.emusute1212.calculator.di.modules.MainActivityModule
import io.github.emusute1212.calculator.di.modules.UseCaseModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        CalcButtonFragmentModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CalcApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: CalcApplication): Builder

        fun build(): ApplicationComponent
    }
}