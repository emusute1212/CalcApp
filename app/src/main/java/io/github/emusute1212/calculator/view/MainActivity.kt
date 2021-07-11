package io.github.emusute1212.calculator.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerAppCompatActivity
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.ViewModelFactory
import io.github.emusute1212.calculator.databinding.ActivityMainBinding
import io.github.emusute1212.calculator.viewmodel.CalcViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: CalcViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.root.init()
        }
        val fragment = CalcButtonFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(
                button_area.id, fragment,
                CalcButtonFragment.FRAGMENT_TAG
            )
            commit()
        }
    }

    private fun View.init() {
        menu_button.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.open_source_license -> {
                    startOpenSourceActivity()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun startOpenSourceActivity() {
        Intent(this, OssLicensesMenuActivity::class.java).also {
            startActivity(it)
        }
    }
}
