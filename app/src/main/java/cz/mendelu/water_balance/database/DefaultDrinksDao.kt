package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData
import androidx.room.*
import cz.mendelu.water_balance.model.DefaultDrinks

@Dao
interface DefaultDrinksDao {

    @Query("SELECT * FROM defaultDrinks")
    fun getAll(): LiveData<MutableList<DefaultDrinks>>
    @Query("SELECT * FROM defaultDrinks WHERE id=:id")
    suspend fun findById(id: Long): DefaultDrinks

    @Insert
    fun  insert(defaultDrinks: DefaultDrinks): Long

    @Update
    fun update(defaultDrinks: DefaultDrinks)

    @Delete
    fun delete(defaultDrinks: DefaultDrinks)


}