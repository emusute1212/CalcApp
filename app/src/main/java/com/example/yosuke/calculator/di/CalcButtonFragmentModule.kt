package com.example.yosuke.calculator.di

import com.example.yosuke.calculator.CalcButtonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalcButtonFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCalcButtonFragment(): CalcButtonFragment
}