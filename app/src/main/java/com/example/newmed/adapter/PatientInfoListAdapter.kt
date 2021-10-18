package com.example.newmed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.databinding.ItemInfoPatientBinding
import com.example.newmed.models.PatientModel

class PatientInfoListAdapter(
    private val clickListener: PatientInfoListener
) : ListAdapter<PatientModel, PatientInfoListAdapter.PatientInfoHolder>(PatientInfoDiffCallback()) {

    class PatientInfoHolder(private val binding: ItemInfoPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(patientModel: PatientModel, clickListener: PatientInfoListener) {
            binding.apply {
                tvNamePatient.text = patientModel.namePatient
                tvAgePatient.text = patientModel.agePatient
                tvPricePatient.text = patientModel.pricePatient
                root.setOnClickListener {
                    clickListener.onClickInfoPatient(patientModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientInfoHolder {
        val binding =
            ItemInfoPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientInfoHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientInfoHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class PatientInfoDiffCallback : DiffUtil.ItemCallback<PatientModel>() {
    override fun areItemsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem == newItem
    }
}

class PatientInfoListener(val clickListener: (patientModel: PatientModel) -> Unit) {
    fun onClickInfoPatient(patientModel: PatientModel) = clickListener(patientModel)
}