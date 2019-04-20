package com.example.yosuke.calculator.di

import com.example.yosuke.calculator.CalcApplication
import com.example.yosuke.calculator.di.modules.CalcButtonFragmentModule
import com.example.yosuke.calculator.di.modules.MainActivityModule
import com.example.yosuke.calculator.di.modules.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
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