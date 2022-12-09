package cz.mendelu.water_balance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "day")
class Day(@ColumnInfo var date: String,@ColumnInfo var water: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}