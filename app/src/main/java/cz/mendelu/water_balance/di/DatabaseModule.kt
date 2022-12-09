package cz.mendelu.water_balance.di

import android.content.Context
import cz.mendelu.water_balance.database.DrinksDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single {
        provideDatabase(androidContext())
    }
}
fun provideDatabase(context: Context): DrinksDatabase = DrinksDatabase.getDatabase(context)