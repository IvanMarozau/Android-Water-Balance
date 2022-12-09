package cz.mendelu.water_balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IDrinksLocalRepository
import cz.mendelu.water_balance.model.Drink

class HistoryViewModel (private val repository: IDrinksLocalRepository) : ViewModel() {

    fun getAll(): LiveData<MutableList<Drink>> {
        return repository.getAll()
    }

    fun deleteDrink(drink: Drink){
        repository.delete(drink)
    }

}