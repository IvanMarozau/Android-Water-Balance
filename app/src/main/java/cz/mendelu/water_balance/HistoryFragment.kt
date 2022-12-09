package cz.mendelu.water_balance


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

import cz.mendelu.water_balance.databinding.RowDrinksListBinding
import cz.mendelu.water_balance.model.DefaultDrinks

import cz.mendelu.water_balance.model.Drink


class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>(HistoryViewModel::class) {

    private val drinksList: MutableList<Drink> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DrinksAdapter

    inner class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>() {

        inner class DrinkViewHolder(val binding: RowDrinksListBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
            return DrinkViewHolder(
                RowDrinksListBinding
                    .inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
            )
        }



        override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
            val drinks = drinksList.get(position)
            holder.binding.drinkAmount.text = drinks.amount.toString() + " ml"
            holder.binding.drinkTime.text = drinks.time
            holder.binding.drinkName.text= drinks.name
            if(drinks.name == "Water"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_water))
            }
            if(drinks.name == "Coffee"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_coffee))
            }
            if(drinks.name == "Tea"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_tea))
            }
            if(drinks.name == "Milk"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_milk))
            }
            if(drinks.name == "Juice"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_juice))
            }
            if(drinks.name == "Lemonade"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_lemonade))
            }
            if(drinks.name == "Beer"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_beer))
            }
            if(drinks.name == "Wine"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_wine))
            }
            if(drinks.name == "Strong alcohol"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_strong_alc))
            }
            if(drinks.name == "Energy drink"){
                holder.binding.drinkImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_energy))
            }
            holder.binding.root.setOnClickListener {

            }
            holder.binding.deleteBtn.setOnClickListener {
                viewModel.deleteDrink(drinks)
            }

            holder.binding.root.setOnClickListener {
                val action = HistoryFragmentDirections.actionHistoryFragmentToEditAmount()
                action.id = drinksList.get(holder.adapterPosition).id!!
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int = drinksList.size

    }

    inner class DrinksDiffUtils(private val oldList: MutableList<Drink>, private val newList: MutableList<Drink>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size


        override fun getNewListSize(): Int = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].amount == newList[newItemPosition].amount
        }

    }
    override val bindingInflater: (LayoutInflater) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = DrinksAdapter()
        binding.defaultDrinkList.layoutManager = layoutManager
        binding.defaultDrinkList.adapter = adapter

        viewModel
            .getAll()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Drink>> {
                override fun onChanged(t: MutableList<Drink>?) {
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