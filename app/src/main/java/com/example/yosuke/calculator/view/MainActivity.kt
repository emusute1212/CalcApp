package com.example.yosuke.calculator.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.View
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.ViewModelFactory
import com.example.yosuke.calculator.databinding.ActivityMainBinding
import com.example.yosuke.calculator.viewmodel.CalcViewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalcViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.viewModel = viewModel
            binding.setLifecycleOwner(this)
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
