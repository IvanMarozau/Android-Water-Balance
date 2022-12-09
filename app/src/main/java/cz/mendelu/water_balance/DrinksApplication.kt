package cz.mendelu.water_balance

import android.app.Application
import cz.mendelu.water_balance.di.daoModule
import cz.mendelu.water_balance.di.databaseModule
import cz.mendelu.water_balance.di.repositoryModule
import cz.mendelu.water_balance.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class DrinksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
         startKoin{
            androidContext(applicationContext)
            modules(
                databaseModule,
                daoModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}