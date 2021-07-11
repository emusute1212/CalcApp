package io.github.emusute1212.calculator

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.emusute1212.calculator.di.DaggerApplicationComponent

class CalcApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        //Stethoの設定
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .app(this).build()
    }
}