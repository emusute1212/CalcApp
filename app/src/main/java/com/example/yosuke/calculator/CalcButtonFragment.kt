package com.example.yosuke.calculator

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yosuke.calculator.databinding.FragmentCalcButtonBinding
import com.example.yosuke.calculator.view.SpecialButtonAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_calc_button.view.*
import javax.inject.Inject


class CalcButtonFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel by lazy { ViewModelProviders.of(requireActivity(), viewModelFactory).get(CalcViewModel::class.java) }
    val adapter by lazy { SpecialButtonAdapter(viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentCalcButtonBinding>(
            inflater,
            R.layout.fragment_calc_button,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.root.special_button_recycler_view.apply {
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false).also {
                layoutManager = it
                adapter = this@CalcButtonFragment.adapter
            }
        }
        viewModel.number.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }

    companion object {
        val FRAGMENT_TAG = CalcButtonFragment::class.java.simpleName

        fun newInstance(): CalcButtonFragment {
            return CalcButtonFragment()
        }
    }
}