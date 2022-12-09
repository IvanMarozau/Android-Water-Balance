package cz.mendelu.water_balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IDrinksLocalRepository
import cz.mendelu.water_balance.model.Day
import cz.mendelu.water_balance.model.DefaultDrinks
import cz.mendelu.water_balance.model.Drink

class StatisticsViewModel(private val repository: IDrinksLocalRepository) : ViewModel() {

    fun getAllTheDay(): LiveData<MutableList<Day>> {
        return repository.getAllTheDay()
    }

}