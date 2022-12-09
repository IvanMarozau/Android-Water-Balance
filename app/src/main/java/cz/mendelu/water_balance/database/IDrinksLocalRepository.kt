package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData
import cz.mendelu.water_balance.model.Day
import cz.mendelu.water_balance.model.Drink

interface IDrinksLocalRepository {
    fun getAll(): LiveData<MutableList<Drink>>

    suspend fun findById(id: Long): Drink

    fun  insertDrink(drink: Drink): Long

    suspend fun update(drink: Drink)

    fun delete(drink: Drink)

    fun sumAmount():Int
    fun sumWaterDay():Int
    fun getAllTheDay(): LiveData<MutableList<Day>>
    fun sumProgressDay(now: String):Int
}