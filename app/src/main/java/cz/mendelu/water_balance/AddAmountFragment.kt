package cz.mendelu.water_balance

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentAddAmountBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AddAmountFragment : BaseFragment<FragmentAddAmountBinding, AddAmountViewModel>(
    AddAmountViewModel::class) {

    private val arguments: AddAmountFragmentArgs by navArgs()


    override val bindingInflater: (LayoutInflater) -> FragmentAddAmountBinding
        get() = FragmentAddAmountBinding::inflate

    override fun initViews() {

        binding.numberPicker.maxValue = 1000
        binding.numberPicker.minValue = 25

       viewModel.id = if (arguments.id != -1L) arguments.id else null

           lifecycleScope.launch {
               viewModel.defaultDrink=viewModel.findDefDrinkById()
               binding.amountName.text=viewModel.defaultDrink.name
       }

        binding.toDrink.setOnClickListener {
           viewModel.drink.amount= binding.numberPicker.value.toLong()
            viewModel.drink.name = viewModel.defaultDrink.name
            viewModel.drink.efficiency = viewModel.defaultDrink.efficiency
            viewModel.drink.caffeine = viewModel.defaultDrink.caffeine
            viewModel.drink.alcohol = viewModel.defaultDrink.alcohol
            viewModel.drink.water = ((viewModel.defaultDrink.efficiency.toDouble()/100)*viewModel.drink.amount).toLong()
            viewModel.drink.date = SimpleDateFormat("EEE dd MMM yyyy", Locale.getDefault()).format(Date())
            viewModel.drink.time =  SimpleDateFormat("HH:mm", Locale.getDefault()).format( Date());


            lifecycleScope.launch {
                viewModel.saveDrink()
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