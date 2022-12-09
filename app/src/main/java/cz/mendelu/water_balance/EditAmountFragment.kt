package cz.mendelu.water_balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentEditAmountBinding
import kotlinx.coroutines.launch


class EditAmountFragment : BaseFragment<FragmentEditAmountBinding,EditAmountViewModel>(EditAmountViewModel::class) {

    private val arguments: EditAmountFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater) -> FragmentEditAmountBinding
        get() = FragmentEditAmountBinding::inflate

    override fun initViews() {
        binding.numberPicker.maxValue = 1000
        binding.numberPicker.minValue = 25

        viewModel.id = if (arguments.id != -1L) arguments.id else null

        lifecycleScope.launch {
            viewModel.drink=viewModel.findDrinkById()
            binding.amountName.text=viewModel.drink.name
        }
        binding.toEdit.setOnClickListener {
            viewModel.drink.amount= binding.numberPicker.value.toLong()
            viewModel.drink.water = ((viewModel.drink.efficiency.toDouble()/100)* viewModel.drink.amount!!).toLong()
            lifecycleScope.launch {
                viewModel.updateDrink()
            }.invokeOnCompletion {
                finishCurrentFragment()
            }
        }
        binding.cancelAmount.setOnClickListener {
            finishCurrentFragment()
        }
    }

    override fun onActivityCreated() {

    }

}