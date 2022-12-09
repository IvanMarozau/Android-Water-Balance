package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData
import androidx.room.*
import cz.mendelu.water_balance.model.Day
import cz.mendelu.water_balance.model.Drink


@Dao
interface DrinksDao {

    @Query("SELECT * FROM drinks")
    fun getAll(): LiveData<MutableList<Drink>>
    @Query("SELECT * FROM drinks WHERE id=:id")
    suspend fun findById(id: Long): Drink

    @Insert
    fun  insertDrink(drink: Drink): Long

    @Update
    suspend fun update(drink: Drink)

    @Delete
    fun delete(drink: Drink)

    @Query("SELECT SUM(water) FROM drinks")
    fun sumAmount():Int
    @Query("SELECT water FROM drinks GROUP BY date ")
    fun sumWaterDay():Int
    @Query("SELECT SUM(water) water,date FROM drinks GROUP BY date")
    fun getAllTheDay(): LiveData<MutableList<Day>>
    @Query("SELECT SUM(water) FROM drinks WHERE date = :now ")
    fun sumProgressDay(now: String):Int
}