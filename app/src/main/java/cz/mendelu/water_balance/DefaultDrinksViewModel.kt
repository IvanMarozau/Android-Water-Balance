package cz.mendelu.water_balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IDefaultDrinksLocalRepository
import cz.mendelu.water_balance.database.IDrinksLocalRepository
import cz.mendelu.water_balance.database.IPersonLocalRepository
import cz.mendelu.water_balance.model.DefaultDrinks
import cz.mendelu.water_balance.model.Drink
import cz.mendelu.water_balance.model.Person
import java.text.SimpleDateFormat
import java.util.*

class DefaultDrinksViewModel (private val defRepository: IDefaultDrinksLocalRepository,
                              private val perRepository: IPersonLocalRepository,
                              private val drinkRepository: IDrinksLocalRepository) : ViewModel() {


    fun getAll(): LiveData<MutableList<DefaultDrinks>>{
        return defRepository.getAll()
    }
    suspend fun findPersonById(): Person = perRepository.findById(1)

     fun sumOfAmountWater():Int{
        return drinkRepository.sumAmount()
    }

    fun progressBarAmount():Int{
        return drinkRepository.sumProgressDay(
            SimpleDateFormat("EEE dd MMM yyyy", Locale.getDefault()).format(
                Date()
            ))
    }

}