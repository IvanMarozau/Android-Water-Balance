package cz.mendelu.water_balance.database

import cz.mendelu.water_balance.model.Person

class PersonRepositoryImpl (private val personDao: PersonDao) : IPersonLocalRepository {
    override suspend fun findById(id: Long): Person {
        return personDao.findById(id)
    }

    override fun insertPerson(person: Person): Long {
        return personDao.insertPerson(person)
    }

    override fun update(person: Person) {
        personDao.update(person)
    }
}