package cz.mendelu.water_balance

import androidx.lifecycle.ViewModel
import cz.mendelu.water_balance.database.IPersonLocalRepository
import cz.mendelu.water_balance.model.Person

class AddHeightViewModel(private val repository: IPersonLocalRepository) : ViewModel() {

    var person: Person = Person(0,0)

    suspend fun updatePerson(){
        repository.update(person)
    }
    suspend fun findPersonById():Person = repository.findById(1)
}