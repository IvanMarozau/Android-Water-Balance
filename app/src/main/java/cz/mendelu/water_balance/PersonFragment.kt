package cz.mendelu.water_balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.mendelu.water_balance.architecture.BaseFragment
import cz.mendelu.water_balance.databinding.FragmentPersonBinding
import kotlinx.coroutines.launch


class PersonFragment : BaseFragment<FragmentPersonBinding, PersonViewModel>(
    PersonViewModel::class) {



    override val bindingInflater: (LayoutInflater) -> FragmentPersonBinding
        get() = FragmentPersonBinding::inflate

    override fun initViews() {

        binding.heightPerson.setOnClearClickListenner(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(PersonFragmentDirections.actionPersonToAddHeight())
            }

        })
        binding.weightPerson.setOnClearClickListenner(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(PersonFragmentDirections.actionPersonToAddWeight())
            }

        })

        lifecycleScope.launch {
            binding.heightPerson.value = viewModel.findPersonById().height.toString()+" cm"
            binding.weightPerson.value = viewModel.findPersonById().weight.toString()+" kg"
            binding.normOfWater.value = viewModel.findPersonById().dailyNormOfWater.toString() + " ml"
        }
        binding.version.text = BuildConfig.VERSION_NAME







    }

    override fun onActivityCreated() {

    }


}