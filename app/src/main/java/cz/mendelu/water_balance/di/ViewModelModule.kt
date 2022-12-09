package cz.mendelu.water_balance.di

import cz.mendelu.water_balance.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{

    viewModel {
        DefaultDrinksViewModel(get(),get(),get())
    }
    viewModel {
        AddAmountViewModel(get(),get())
    }
    viewModel{
        PersonViewModel(get())
    }
    viewModel{
        AddHeightViewModel(get())
    }
    viewModel{
        HistoryViewModel(get())
    }
    viewModel{
        AddWeightViewModel(get())
    }
    viewModel{
        EditAmountViewModel(get())
    }
    viewModel{
        StatisticsViewModel(get())
    }
}