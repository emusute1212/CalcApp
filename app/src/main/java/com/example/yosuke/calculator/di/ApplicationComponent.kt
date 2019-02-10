package com.example.yosuke.calculator.di

import com.example.yosuke.calculator.CalcApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
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