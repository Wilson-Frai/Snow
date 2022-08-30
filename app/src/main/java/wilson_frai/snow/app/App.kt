package wilson_frai.snow.app

import android.app.Application
import wilson_frai.snow.di.appModule
import wilson_frai.snow.di.dataModule
import wilson_frai.snow.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App() : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}