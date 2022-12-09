package cz.mendelu.water_balance.di

import cz.mendelu.water_balance.database.DefaultDrinksDao
import cz.mendelu.water_balance.database.DrinksDao
import cz.mendelu.water_balance.database.DrinksDatabase
import cz.mendelu.water_balance.database.PersonDao
import org.koin.dsl.module

val daoModule = module{
    single {
        provideDefaultDrinksDao(get())
    }
    single {
        provideDrinksDao(get())
    }
    single {
        providePersonDao(get())
    }
}

fun provideDefaultDrinksDao(database: DrinksDatabase): DefaultDrinksDao = database.defaultDrinksDao()
fun provideDrinksDao(database: DrinksDatabase): DrinksDao = database.drinksDao()
fun providePersonDao(database: DrinksDatabase): PersonDao = database.personDao()