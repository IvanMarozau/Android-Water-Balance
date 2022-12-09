package cz.mendelu.water_balance

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.database.DrinksDatabase
//import cz.mendelu.water_balance.database.DrinksDatabase
import cz.mendelu.water_balance.databinding.FragmentDefaultDrinksBinding
import cz.mendelu.water_balance.databinding.RowDefaultDrinksListBinding
import cz.mendelu.water_balance.model.DefaultDrinks
import cz.mendelu.water_balance.model.Drink
import kotlinx.coroutines.launch


class DefaultDrinksFragment : BaseFragment<FragmentDefaultDrinksBinding, DefaultDrinksViewModel>(
    DefaultDrinksViewModel::class) {

    private val defaultDrinksList: MutableList<DefaultDrinks> = mutableListOf()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DefaultDrinksAdapter






    inner class DefaultDrinksAdapter : RecyclerView.Adapter<DefaultDrinksAdapter.DefaultDrinksViewHolder>() {

        inner class DefaultDrinksViewHolder(val binding: RowDefaultDrinksListBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultDrinksViewHolder {
            return DefaultDrinksViewHolder(
                RowDefaultDrinksListBinding
                    .inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
            )
        }



        override fun onBindViewHolder(holder: DefaultDrinksViewHolder, position: Int) {
            val dafaultDrinks = defaultDrinksList.get(position)
            holder.binding.name.text= dafaultDrinks.name
            if(dafaultDrinks.name == "Water"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_water))
            }
            if(dafaultDrinks.name == "Coffee"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_coffee))
            }
            if(dafaultDrinks.name == "Tea"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_tea))
            }
            if(dafaultDrinks.name == "Milk"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_milk))
            }
            if(dafaultDrinks.name == "Juice"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_juice))
            }
            if(dafaultDrinks.name == "Lemonade"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_lemonade))
            }
            if(dafaultDrinks.name == "Beer"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_beer))
            }
            if(dafaultDrinks.name == "Wine"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_wine))
            }
            if(dafaultDrinks.name == "Strong alcohol"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_strong_alc))
            }
            if(dafaultDrinks.name == "Energy drink"){
                holder.binding.infoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_energy))
            }
            holder.binding.root.setOnClickListener {
                val action = DefaultDrinksFragmentDirections.actionDefaultListToAddAmount()
                action.id = defaultDrinksList.get(holder.adapterPosition).id!!
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int = defaultDrinksList.size

    }
    inner class DefaultDrinksDiffUtils(private val oldList: MutableList<DefaultDrinks>, private val newList: MutableList<DefaultDrinks>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size


        override fun getNewListSize(): Int = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

    }

    override val bindingInflater: (LayoutInflater) -> FragmentDefaultDrinksBinding
        get() = FragmentDefaultDrinksBinding::inflate


    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = DefaultDrinksAdapter()
        binding.defaultDrinkList.layoutManager = layoutManager
        binding.defaultDrinkList.adapter = adapter
        lifecycleScope.launch {
            binding.progressBar.max = viewModel.findPersonById().dailyNormOfWater?.toInt() ?: 0
            binding.progressBar.progress = viewModel.progressBarAmount()
        }
        lifecycleScope.launch {
        binding.amountWater.text = viewModel.progressBarAmount().toString()+"ml"
        binding.normWater.text = viewModel.findPersonById().dailyNormOfWater.toString()+"ml"
        }
        lifecycleScope.launch {
            binding.progressShow.text = ((viewModel.progressBarAmount()
               .toDouble() / viewModel.findPersonById().dailyNormOfWater!!) * 100).toInt()
                .toString() + "%"
        }

        viewModel
            .getAll()
            .observe(viewLifecycleOwner, object : Observer<MutableList<DefaultDrinks>> {
                override fun onChanged(t: MutableList<DefaultDrinks>?) {
                    val callback = DefaultDrinksDiffUtils(defaultDrinksList, t!!)
                    val result = DiffUtil.calculateDiff(callback)
                    result.dispatchUpdatesTo(adapter)
                    defaultDrinksList.clear()
                    defaultDrinksList.addAll(t!!)
                }

            })

    }

    override fun onActivityCreated() {

    }


}

