package com.example.yosuke.calculator

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.yosuke.calculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val fragment = CalcButtonFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(button_area.id, fragment, CalcButtonFragment.FRAGMENT_TAG)
            commit()
        }
    }
}
