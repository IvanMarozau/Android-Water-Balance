package cz.mendelu.water_balance

import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IPersonLocalRepository
import cz.mendelu.water_balance.model.Person

class PersonViewModel(private val repository: IPersonLocalRepository) : ViewModel() {

    suspend fun findPersonById(): Person = repository.findById(1)
}