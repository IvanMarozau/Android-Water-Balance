package cz.mendelu.water_balance.di

import cz.mendelu.water_balance.database.*
import org.koin.dsl.module

val repositoryModule = module{
    single{
        provideLocalDefaultDrinksRepository(get())
    }
   single {
       provideLocalDrinksRepository(get())
   }
    single {
        providePersonRepository(get())
    }
}
fun provideLocalDefaultDrinksRepository(dao: DefaultDrinksDao): IDefaultDrinksLocalRepository = DefaultDrinksRepositoryImpl(dao)
fun provideLocalDrinksRepository(dao: DrinksDao): IDrinksLocalRepository = DrinksLocalRepositoryImpl(dao)
fun providePersonRepository(dao: PersonDao): IPersonLocalRepository = PersonRepositoryImpl(dao)