package cz.mendelu.water_balance

import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IDrinksLocalRepository
import cz.mendelu.water_balance.model.Drink

class EditAmountViewModel(private val repository: IDrinksLocalRepository) : ViewModel() {
    var id: Long? = null
    var drink: Drink = Drink("",0,0,0,0,0.0)

    suspend fun findDrinkById(): Drink = repository.findById(id!!)
    suspend fun updateDrink(){
        repository.update(drink)
    }
}