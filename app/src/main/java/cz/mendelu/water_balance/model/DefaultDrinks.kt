package cz.mendelu.water_balance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "defaultDrinks")
class DefaultDrinks(@ColumnInfo var name: String,
                    @ColumnInfo var efficiency: Long,
                    @ColumnInfo var caffeine: Long,
                    @ColumnInfo var alcohol: Double) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

}