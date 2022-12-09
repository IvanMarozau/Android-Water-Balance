package cz.mendelu.water_balance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.mendelu.water_balance.model.Drink
import cz.mendelu.water_balance.model.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM person WHERE id=:id")
    suspend fun findById(id: Long): Person

    @Insert
    fun  insertPerson(person: Person): Long

    @Update
    fun update(person: Person)

}