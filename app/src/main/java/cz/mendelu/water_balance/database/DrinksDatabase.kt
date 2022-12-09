package cz.mendelu.water_balance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cz.mendelu.water_balance.model.DefaultDrinks
import cz.mendelu.water_balance.model.Drink
import cz.mendelu.water_balance.model.Person


@Database(entities = [Drink::class, DefaultDrinks::class, Person::class], version = 1, exportSchema = true)
abstract class DrinksDatabase: RoomDatabase() {
    abstract fun drinksDao(): DrinksDao
    abstract fun defaultDrinksDao(): DefaultDrinksDao
    abstract fun personDao(): PersonDao

    companion object {

        @Volatile private var INSTANCE: DrinksDatabase? = null

        fun getDatabase(context: Context): DrinksDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DrinksDatabase::class.java, "new")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Water", 100,0,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Tea", 85,50,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Coffee", 80,94,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Milk", 78,0,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Juice", 55,0,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Lemonade", 70,0,0.0))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Beer", -60,0,0.183))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Wine", -160,0,0.476))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Strong alcohol", -350,0,1.476))
                            getDatabase(context).defaultDrinksDao().insert(DefaultDrinks("Energy drink", 40,60,0.0))
                            getDatabase(context).personDao().insertPerson(Person(0,0))
                        }
                    }
                })
                .build()


    }




}



