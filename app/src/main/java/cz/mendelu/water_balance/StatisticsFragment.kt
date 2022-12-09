package cz.mendelu.water_balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentHistoryBinding
import cz.mendelu.water_balance.databinding.FragmentStatisticsBinding
import cz.mendelu.water_balance.databinding.RowDrinksListBinding
import cz.mendelu.water_balance.databinding.RowStatisticListBinding
import cz.mendelu.water_balance.model.Day
import cz.mendelu.water_balance.model.Drink


class StatisticsFragment : BaseFragment<FragmentStatisticsBinding,StatisticsViewModel>(StatisticsViewModel::class) {

    private val drinksList: MutableList<Day> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DrinksAdapter

    inner class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>() {

        inner class DrinkViewHolder(val binding: RowStatisticListBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
            return DrinkViewHolder(
                RowStatisticListBinding
                    .inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
            )
        }



        override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
            val drinks = drinksList.get(position)
            holder.binding.waterDay.text = drinks.water.toString() + " ml"
            holder.binding.date.text = drinks.date




        }

        override fun getItemCount(): Int = drinksList.size

    }

    inner class DrinksDiffUtils(private val oldList: MutableList<Day>, private val newList: MutableList<Day>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size


        override fun getNewListSize(): Int = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].date == newList[newItemPosition].date

        }

    }
    override val bindingInflater: (LayoutInflater) -> FragmentStatisticsBinding
        get() = FragmentStatisticsBinding::inflate

    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = DrinksAdapter()
        binding.defaultDrinkList.layoutManager = layoutManager
        binding.defaultDrinkList.adapter = adapter

        viewModel
            .getAllTheDay()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Day>> {
                override fun onChanged(t: MutableList<Day>?) {
                    val callback = DrinksDiffUtils(drinksList, t!!)
                    val result = DiffUtil.calculateDiff(callback)
                    result.dispatchUpdatesTo(adapter)
                    drinksList.clear()
                    drinksList.addAll(t!!)
                }

            })

    }

    override fun onActivityCreated() {

    }

}