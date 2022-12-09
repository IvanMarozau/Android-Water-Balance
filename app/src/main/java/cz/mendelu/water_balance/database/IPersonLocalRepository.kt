package cz.mendelu.water_balance.database


import cz.mendelu.water_balance.model.Person

interface IPersonLocalRepository {

    suspend fun findById(id: Long): Person


    fun  insertPerson(person: Person): Long


    fun update(person: Person)
}