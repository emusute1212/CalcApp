package com.example.yosuke.calculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.ViewModelFactory
import com.example.yosuke.calculator.databinding.ActivityMainBinding
import com.example.yosuke.calculator.viewmodel.CalcViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalcViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        ).also { binding ->
            binding.viewModel = viewModel
            binding.setLifecycleOwner(this)
        }
        val fragment = CalcButtonFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(button_area.id, fragment,
                CalcButtonFragment.FRAGMENT_TAG
            )
            commit()
        }
    }
}
