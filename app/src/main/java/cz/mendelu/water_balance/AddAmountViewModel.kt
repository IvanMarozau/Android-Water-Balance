package cz.mendelu.water_balance

import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IDefaultDrinksLocalRepository
import cz.mendelu.water_balance.database.IDrinksLocalRepository
import cz.mendelu.water_balance.model.DefaultDrinks
import cz.mendelu.water_balance.model.Drink

class AddAmountViewModel (private val repository: IDrinksLocalRepository, private val defRepository: IDefaultDrinksLocalRepository) : ViewModel() {

    var id: Long? = null

    var defaultDrink: DefaultDrinks = DefaultDrinks("",0,0,0.0)
    var drink: Drink = Drink("",0,0,0,0,0.0)

    suspend fun saveDrink(){
        repository.insertDrink(drink)
    }

    suspend fun findDefDrinkById(): DefaultDrinks = defRepository.findById(id!!)
}