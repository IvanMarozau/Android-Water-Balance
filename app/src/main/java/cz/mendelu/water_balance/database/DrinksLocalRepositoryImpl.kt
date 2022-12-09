package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData
import cz.mendelu.water_balance.model.Day
import cz.mendelu.water_balance.model.Drink

class DrinksLocalRepositoryImpl(private val drinksDao: DrinksDao) : IDrinksLocalRepository {
    override fun getAll(): LiveData<MutableList<Drink>> {
        return drinksDao.getAll()
    }

    override suspend fun findById(id: Long): Drink {
       return drinksDao.findById(id)
    }

    override fun insertDrink(drink: Drink): Long {
       return drinksDao.insertDrink(drink)
    }

    override suspend fun update(drink: Drink) {
         drinksDao.update(drink)
    }

    override fun delete(drink: Drink) {
         drinksDao.delete(drink)
    }

    override fun sumAmount(): Int {
        return drinksDao.sumAmount()
    }

    override fun sumWaterDay(): Int {
        return drinksDao.sumWaterDay()
    }

    override fun getAllTheDay(): LiveData<MutableList<Day>> {
        return drinksDao.getAllTheDay()
    }

    override fun sumProgressDay(now: String): Int {
        return drinksDao.sumProgressDay(now)
    }
}