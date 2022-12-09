package cz.mendelu.water_balance

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import cz.mendelu.water_balance.database.DrinksDatabase
import cz.mendelu.water_balance.databinding.ActivitySinglePageBinding
import cz.mendelu.water_balance.model.DefaultDrinks

class SinglePageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySinglePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySinglePageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_single_page)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.DefaultDrinksFragment, R.id.HistoryFragment, R.id.StatisticsFragment,R.id.PersonFragment
            )
        )


        navView.setupWithNavController(navController)
    }


}