package io.github.emusute1212.calculator.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.calculator.view.MainActivity

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [CalcModule::class])
    abstract fun contributeMainActivity(): MainActivity
}