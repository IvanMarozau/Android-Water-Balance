package cz.mendelu.water_balance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*


@Entity(tableName = "drinks")
class Drink(
    @ColumnInfo var name: String,
    @ColumnInfo var amount: Long,
    @ColumnInfo var water: Long,
    @ColumnInfo var efficiency: Long,
    @ColumnInfo var caffeine: Long,
    @ColumnInfo var alcohol: Double,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name="date")
    var date: String? = null

    @ColumnInfo(name="time")
    var time: String? = null

}