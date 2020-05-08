package io.github.emusute1212.calculator.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.calculator.view.CalcButtonFragment

@Module
abstract class CalcButtonFragmentModule {

    @ContributesAndroidInjector(modules = [CalcModule::class])
    abstract fun contributeCalcButtonFragment(): CalcButtonFragment
}