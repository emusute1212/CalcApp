package com.example.yosuke.calculator

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yosuke.calculator.databinding.FragmentCalcButtonBinding
import com.example.yosuke.calculator.view.OperatorButtonAdapter
import com.example.yosuke.calculator.view.SpecialButtonAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_calc_button.view.*
import javax.inject.Inject


class CalcButtonFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel by lazy { ViewModelProviders.of(requireActivity(), viewModelFactory).get(CalcViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentCalcButtonBinding>(
            inflater,
            R.layout.fragment_calc_button,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapterのインスタンス作成
        val specialButtonAdapter = SpecialButtonAdapter(viewModel)
        val operatorButtonAdapter = OperatorButtonAdapter(viewModel)
        //adapterのセットアップ
        setupSpecialButtonAdapter(view.special_button_recycler_view, specialButtonAdapter)
        setupOperatorButtonAdapter(view.operator_button_recycler_view, operatorButtonAdapter)

        //C・ACボタンをセットするためのObserverを設定
        viewModel.number.observe(this, Observer {
            specialButtonAdapter.notifyDataSetChanged()
        })
    }

    private fun setupSpecialButtonAdapter(recyclerView: RecyclerView, adapter: SpecialButtonAdapter) {
        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false).also {
            recyclerView.layoutManager = it
            recyclerView.adapter = adapter
        }
    }

    private fun setupOperatorButtonAdapter(recyclerView: RecyclerView, adapter: OperatorButtonAdapter) {
        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false).also {
            recyclerView.layoutManager = it
            recyclerView.adapter = adapter
        }
    }

    companion object {
        val FRAGMENT_TAG = CalcButtonFragment::class.java.simpleName

        fun newInstance(): CalcButtonFragment {
            return CalcButtonFragment()
        }
    }
}