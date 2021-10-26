package com.example.newmed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.databinding.ItemPriceBinding
import com.example.newmed.model.PatientModel

class PriceListAdapter (
    private val clickListener: PriceListener
) : ListAdapter<PatientModel, PriceListAdapter.PriceHolder>(PriceDiffCallback()) {

    class PriceHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //связываются данные и берутся откуда ввели, чтоб вставить куда надо
        fun bind(patientModel: PatientModel, clickListener: PriceListener) {
            binding.apply {
                //tvData.text = patientModel.date
                tvNameCall.text = patientModel.nameCall
                tvPrice.text = patientModel.pricePatient
                root.setOnClickListener {
                    clickListener.onClickPrice(patientModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceHolder {
        val binding = ItemPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceHolder(binding)
    }

    override fun onBindViewHolder(holder: PriceHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class PriceDiffCallback : DiffUtil.ItemCallback<PatientModel>() {
    override fun areItemsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem == newItem
    }
}

class PriceListener(val clickListener: (patientModel: PatientModel) -> Unit) {
    fun onClickPrice(patientModel: PatientModel) = clickListener(patientModel)
}