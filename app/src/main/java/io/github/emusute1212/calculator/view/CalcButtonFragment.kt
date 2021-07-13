package io.github.emusute1212.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.ViewModelFactory
import io.github.emusute1212.calculator.databinding.FragmentCalcButtonBinding
import io.github.emusute1212.calculator.ext.autoCleared
import io.github.emusute1212.calculator.model.entity.Operators
import io.github.emusute1212.calculator.model.entity.Specials
import io.github.emusute1212.calculator.view.adapter.OperatorButtonAdapter
import io.github.emusute1212.calculator.view.adapter.SpecialButtonAdapter
import io.github.emusute1212.calculator.viewmodel.CalcViewModel
import javax.inject.Inject


class CalcButtonFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: CalcViewModel by activityViewModels {
        viewModelFactory
    }
    private var binding: FragmentCalcButtonBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
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
        setupSpecialButtonAdapter(binding.specialButtonRecyclerView, specialButtonAdapter)
        setupOperatorButtonAdapter(binding.operatorButtonRecyclerView, operatorButtonAdapter)

        //C・ACボタンをセットするためのObserverを設定
        viewModel.number.observe(viewLifecycleOwner) {
            specialButtonAdapter.notifyDataSetChanged()
        }
    }

    private fun setupSpecialButtonAdapter(
        recyclerView: RecyclerView,
        adapter: SpecialButtonAdapter
    ) {
        GridLayoutManager(
            requireContext(),
            Specials.values().size,
            LinearLayoutManager.VERTICAL,
            false
        ).also {
            recyclerView.layoutManager = it
            recyclerView.adapter = adapter
        }
    }

    private fun setupOperatorButtonAdapter(
        recyclerView: RecyclerView,
        adapter: OperatorButtonAdapter
    ) {
        GridLayoutManager(
            requireContext(),
            Operators.values().size,
            LinearLayoutManager.HORIZONTAL,
            false
        ).also {
            recyclerView.layoutManager = it
            recyclerView.adapter = adapter
        }
    }

    companion object {
        val FRAGMENT_TAG: String = CalcButtonFragment::class.java.simpleName

        fun newInstance(): CalcButtonFragment {
            return CalcButtonFragment()
        }
    }
}