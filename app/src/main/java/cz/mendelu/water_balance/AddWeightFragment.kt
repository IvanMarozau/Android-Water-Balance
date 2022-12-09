package cz.mendelu.water_balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentAddWeightBinding
import kotlinx.coroutines.launch

class AddWeightFragment : BaseFragment<FragmentAddWeightBinding, AddWeightViewModel>(AddWeightViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> FragmentAddWeightBinding
        get() = FragmentAddWeightBinding::inflate

    override fun initViews() {
        binding.numberPickerWeight.minValue=30
        binding.numberPickerWeight.maxValue=299

        lifecycleScope.launch {
            viewModel.person=viewModel.findPersonById()
        }
        binding.chooseWeight.setOnClickListener {
            viewModel.person.weight = binding.numberPickerWeight.value.toLong()
            viewModel.person.dailyNormOfWater = (viewModel.person.weight * 32).toLong()
            lifecycleScope.launch {
                viewModel.updatePerson()
            }.invokeOnCompletion {
                finishCurrentFragment()
            }
        }
        binding.cancelWeight.setOnClickListener {
            finishCurrentFragment()
        }
    }

    override fun onActivityCreated() {

    }


}