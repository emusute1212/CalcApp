package io.github.emusute1212.calculator.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerAppCompatActivity
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.ViewModelFactory
import io.github.emusute1212.calculator.databinding.ActivityMainBinding
import io.github.emusute1212.calculator.viewmodel.CalcViewModel
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: CalcViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
        init()
        val fragment = CalcButtonFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(
                binding.buttonArea.id, fragment,
                CalcButtonFragment.FRAGMENT_TAG
            )
            commit()
        }
    }

    private fun init() {
        binding.menuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.open_source_license -> {
                    startOpenSourceActivity()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun startOpenSourceActivity() {
        Intent(this, OssLicensesMenuActivity::class.java).also {
            startActivity(it)
        }
    }
}
