package cz.mendelu.water_balance.model

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person")
class Person(@ColumnInfo var height: Long,
             @ColumnInfo var weight: Long ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
    var dailyNormOfWater: Long? = null;
}