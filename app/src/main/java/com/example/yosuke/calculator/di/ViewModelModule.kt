package com.example.yosuke.calculator.di

import android.arch.lifecycle.ViewModelProvider
import com.example.yosuke.calculator.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}