package com.example.yosuke.calculator.di.modules

import com.example.yosuke.calculator.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [CalcModule::class])
    abstract fun contributeMainActivity(): MainActivity
}