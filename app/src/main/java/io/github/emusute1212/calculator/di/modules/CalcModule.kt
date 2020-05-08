package io.github.emusute1212.calculator.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.emusute1212.calculator.di.ViewModelKey
import io.github.emusute1212.calculator.viewmodel.CalcViewModel

@Module
abstract class CalcModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalcViewModel::class)
    abstract fun provideCalcViewModel(viewModel: CalcViewModel): ViewModel

}