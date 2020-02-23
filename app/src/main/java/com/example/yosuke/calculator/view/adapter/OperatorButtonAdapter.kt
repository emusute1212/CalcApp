package com.example.yosuke.calculator.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.ext.setOnFeedbackClick
import com.example.yosuke.calculator.model.entity.Operators
import com.example.yosuke.calculator.viewmodel.CalcViewModel

class OperatorButtonAdapter(
    private val viewModel: CalcViewModel
) : RecyclerView.Adapter<OperatorButtonAdapter.OperatorButtonViewHolder>() {

    override fun getItemCount() = Operators.values().size

    override fun onBindViewHolder(holder: OperatorButtonViewHolder, position: Int) {
        if (holder.binding == null) return
        val operator = Operators.values()[position]
        holder.binding.viewModel = viewModel
        holder.binding.buttonEntity = operator
        holder.binding.button.setOnFeedbackClick {
            viewModel.onClickOperatorButton(operator)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperatorButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_view, parent, false)

        return OperatorButtonViewHolder(view)
    }

    inner class OperatorButtonViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val binding = DataBindingUtil.bind<com.example.yosuke.calculator.databinding.ButtonViewBinding>(root)
    }
}