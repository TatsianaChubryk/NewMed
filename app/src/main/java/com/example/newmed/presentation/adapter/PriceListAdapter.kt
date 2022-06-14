package com.example.newmed.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.R
import com.example.newmed.databinding.ItemPriceBinding
import com.example.newmed.domain.model.PatientModel
import kotlinx.android.synthetic.main.item_price.view.*
import org.koin.core.parameter.parametersOf

class PriceListAdapter(
    private val clickListener: PriceListener
) : ListAdapter<PatientModel, PriceListAdapter.PriceHolder>(PriceDiffCallback()) {

   private var patientList  = emptyList<PatientModel>()

    class PriceHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //связываются данные и берутся откуда ввели, чтоб вставить куда надо
        fun bind(patientModel: PatientModel, clickListener: PriceListener) {
            binding.apply {
                tvNum.text = (position + 1).toString()
                tvData.text = patientModel.date
                if (patientModel.active) {
                    tvActive.text = "актив"
                    itemView.cardView.setBackgroundColor(Color.argb(60, 129, 212, 250))
                } else
                    tvActive.text = ""

                tvNameCall.text = patientModel.nameCall
                tvPrice.text = patientModel.pricePatient.toString()

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

    fun setData(trans:List<PatientModel>){
        this.patientList = trans
        notifyDataSetChanged()
    }

    fun totalSum():Int{
        var length = patientList.size - 1
        var sum = 0
        while (length != -1) {
            sum += patientList[length].pricePatient
            length -= 1
        }
        return sum
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