package com.example.yosuke.calculator.di.modules

import android.arch.lifecycle.ViewModel
import com.example.yosuke.calculator.CalcViewModel
import com.example.yosuke.calculator.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CalcModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalcViewModel::class)
    abstract fun provideCalcViewModel(viewModel: CalcViewModel): ViewModel

}