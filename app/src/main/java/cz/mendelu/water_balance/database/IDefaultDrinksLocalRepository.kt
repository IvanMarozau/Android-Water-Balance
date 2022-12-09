package cz.mendelu.water_balance.database

import androidx.lifecycle.LiveData

import cz.mendelu.water_balance.model.DefaultDrinks


interface IDefaultDrinksLocalRepository {

    fun getAll(): LiveData<MutableList<DefaultDrinks>>

    suspend fun findById(id: Long): DefaultDrinks


    fun  insert(defaultDrinks: DefaultDrinks): Long


    fun update(defaultDrinks: DefaultDrinks)

    fun delete(defaultDrinks: DefaultDrinks)

}