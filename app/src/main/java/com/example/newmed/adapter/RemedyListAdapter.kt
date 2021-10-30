package com.example.newmed.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.databinding.ItemRemedyBinding
import com.example.newmed.model.RemedyModel

class RemedyListAdapter(
    private val clickListener: RemedyListener
) : ListAdapter<RemedyModel, RemedyListAdapter.RemedyHolder>(RemedyDiffCallback()) {

    class RemedyHolder(private val binding: ItemRemedyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //связываются данные и берутся откуда ввели, чтоб вставить куда надо
        fun bind(remedyModel: RemedyModel, clickListener: RemedyListener) {
            binding.apply {
                tvRemedy.text = remedyModel.nameRemedy
                etRemedyAmount.text = Editable.Factory.getInstance().newEditable(remedyModel.amountRemedy.toString())
                root.setOnClickListener {
                    clickListener.onClickRemedy(remedyModel)
                }
            }
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedyHolder {
        val binding = ItemRemedyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RemedyHolder(binding)
    }

    override fun onBindViewHolder(holder: RemedyHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class RemedyDiffCallback : DiffUtil.ItemCallback<RemedyModel>() {
    override fun areItemsTheSame(oldItem: RemedyModel, newItem: RemedyModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RemedyModel, newItem: RemedyModel): Boolean {
        return oldItem == newItem
    }
}

class RemedyListener(val clickListener: (remedyModel: RemedyModel) -> Unit) {
    fun onClickRemedy(remedyModel: RemedyModel) = clickListener(remedyModel)
}