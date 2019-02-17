package com.example.yosuke.calculator

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yosuke.calculator.databinding.FragmentCalcButtonBinding

class CalcButtonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentCalcButtonBinding>(
            inflater,
            R.layout.fragment_calc_button,
            container,
            false
        )
        return binding.root
    }

    companion object {
        val FRAGMENT_TAG = CalcButtonFragment::class.java.simpleName

        fun newInstance(): CalcButtonFragment {
            return CalcButtonFragment()
        }
    }
}