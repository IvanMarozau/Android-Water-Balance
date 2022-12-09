package cz.mendelu.water_balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentAddHeightBinding
import kotlinx.coroutines.launch


class AddHeightFragment : BaseFragment<FragmentAddHeightBinding, AddHeightViewModel>(AddHeightViewModel::class) {



    override val bindingInflater: (LayoutInflater) -> FragmentAddHeightBinding
        get() = FragmentAddHeightBinding::inflate

    override fun initViews() {
        binding.numberPickerHeight.minValue=50
        binding.numberPickerHeight.maxValue=299

        lifecycleScope.launch {
            viewModel.person=viewModel.findPersonById()
        }
        binding.chooseHeight.setOnClickListener {
            viewModel.person.height = binding.numberPickerHeight.value.toLong()
            lifecycleScope.launch {
                viewModel.updatePerson()
            }.invokeOnCompletion {
                finishCurrentFragment()
            }
        }
        binding.cancelHeight.setOnClickListener {
            finishCurrentFragment()
        }
    }

    override fun onActivityCreated() {

    }


}