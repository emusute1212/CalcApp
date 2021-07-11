package io.github.emusute1212.calculator.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.databinding.ButtonViewBinding
import io.github.emusute1212.calculator.ext.setOnFeedbackClick
import io.github.emusute1212.calculator.model.entity.Operators
import io.github.emusute1212.calculator.viewmodel.CalcViewModel

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
        val binding =
            DataBindingUtil.bind<ButtonViewBinding>(
                root
            )
    }
}