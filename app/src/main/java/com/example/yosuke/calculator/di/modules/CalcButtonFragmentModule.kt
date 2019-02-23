package com.example.yosuke.calculator.di.modules

import com.example.yosuke.calculator.CalcButtonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalcButtonFragmentModule {

    @ContributesAndroidInjector(modules = [CalcModule::class])
    abstract fun contributeCalcButtonFragment(): CalcButtonFragment
}