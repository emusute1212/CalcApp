package io.github.emusute1212.calculator.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.databinding.ButtonViewBinding
import io.github.emusute1212.calculator.ext.setOnFeedbackClick
import io.github.emusute1212.calculator.model.entity.Specials
import io.github.emusute1212.calculator.viewmodel.CalcViewModel

class SpecialButtonAdapter(
    private val viewModel: CalcViewModel
) : RecyclerView.Adapter<SpecialButtonAdapter.SpecialButtonViewHolder>() {

    override fun getItemCount() = Specials.values().size

    override fun onBindViewHolder(holder: SpecialButtonViewHolder, position: Int) {
        if (holder.binding == null) return
        val special = Specials.values()[position]
        holder.binding.viewModel = viewModel
        holder.binding.buttonEntity = special
        holder.binding.button.setOnFeedbackClick {
            viewModel.onClickSpecialButton(special)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_view, parent, false)

        return SpecialButtonViewHolder(view)
    }

    inner class SpecialButtonViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val binding =
            DataBindingUtil.bind<ButtonViewBinding>(
                root
            )
    }
}