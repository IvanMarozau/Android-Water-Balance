package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData
import cz.mendelu.water_balance.model.DefaultDrinks

class DefaultDrinksRepositoryImpl (private val defaultDrinksDao: DefaultDrinksDao) : IDefaultDrinksLocalRepository {
    override fun getAll(): LiveData<MutableList<DefaultDrinks>> {
        return defaultDrinksDao.getAll()
    }

    override suspend fun findById(id: Long): DefaultDrinks {
        return defaultDrinksDao.findById(id)
    }

    override fun insert(defaultDrinks: DefaultDrinks): Long {
        return defaultDrinksDao.insert(defaultDrinks)
    }

    override fun update(defaultDrinks: DefaultDrinks) {
         defaultDrinksDao.update(defaultDrinks)
    }

    override fun delete(defaultDrinks: DefaultDrinks) {
        defaultDrinksDao.delete(defaultDrinks)
    }

}