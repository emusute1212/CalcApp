package com.example.yosuke.calculator.di.modules

import com.example.yosuke.calculator.model.usecase.CalcUseCase
import com.example.yosuke.calculator.model.usecase.CalcUsecaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideCalcUseCase(): CalcUseCase = CalcUsecaseImpl()
}