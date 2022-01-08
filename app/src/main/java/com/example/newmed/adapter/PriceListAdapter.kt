package com.example.newmed.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.R
import com.example.newmed.databinding.ItemPriceBinding
import com.example.newmed.model.PatientModel
import kotlinx.android.synthetic.main.item_price.view.*

class PriceListAdapter (
    private val clickListener: PriceListener
) : ListAdapter<PatientModel, PriceListAdapter.PriceHolder>(PriceDiffCallback()) {

    class PriceHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {

       //связываются данные и берутся откуда ввели, чтоб вставить куда надо
        fun bind(patientModel: PatientModel, clickListener: PriceListener) {
           val price = patientModel.pricePatient
            binding.apply {
                tvData.text = patientModel.date

                if (patientModel.active) {
                    tvActive.text = "актив"
                    tvPrice.setTextColor(Color.rgb(129,212,250))
                } else
                    tvActive.text = ""
                tvNameCall.text = patientModel.nameCall
                tvPrice.text = price.toString()
                if (patientModel.dayNight) {
                    imgDay.setImageResource(R.drawable.ic_night)
                } else imgDay.setImageResource(R.drawable.ic_sun)
                root.setOnClickListener {
                    clickListener.onClickPrice(patientModel)
                }
                tvDistance.text = patientModel.distance.toString()
                tvTime.text = patientModel.time.toString()
                tvMin.text = patientModel.min.toString()
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