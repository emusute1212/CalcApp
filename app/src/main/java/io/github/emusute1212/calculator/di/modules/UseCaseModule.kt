package io.github.emusute1212.calculator.di.modules

import dagger.Module
import dagger.Provides
import io.github.emusute1212.calculator.model.usecase.CalcUseCase
import io.github.emusute1212.calculator.model.usecase.CalcUseCaseImpl

@Module
class UseCaseModule {
    @Provides
    fun provideCalcUseCase(): CalcUseCase = CalcUseCaseImpl()
}