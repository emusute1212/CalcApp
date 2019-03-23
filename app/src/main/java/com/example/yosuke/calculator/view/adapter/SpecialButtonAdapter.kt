package com.example.yosuke.calculator.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yosuke.calculator.viewmodel.CalcViewModel
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.model.elements.Specials

class SpecialButtonAdapter(
    private val viewModel: CalcViewModel
) : RecyclerView.Adapter<SpecialButtonAdapter.SpecialButtonViewHolder>() {

    override fun getItemCount() = Specials.values().size

    override fun onBindViewHolder(holder: SpecialButtonViewHolder, position: Int) {
        if (holder.binding == null) return
        holder.binding.viewModel = viewModel
        holder.binding.buttonEntity = Specials.values()[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_view, parent, false)

        return SpecialButtonViewHolder(view)
    }

    inner class SpecialButtonViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val binding = DataBindingUtil.bind<com.example.yosuke.calculator.databinding.ButtonViewBinding>(root)
    }
}