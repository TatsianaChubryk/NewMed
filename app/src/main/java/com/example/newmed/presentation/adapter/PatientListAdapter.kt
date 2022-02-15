package com.example.newmed.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.domain.model.PatientModel
import com.example.newmed.databinding.ItemPatientBinding
import com.example.newmed.presentation.SwipeToDelete

class PatientListAdapter(
    private val clickListener: PatientListener, private val deleteInterface: DeleteByIdInterface
) : ListAdapter<PatientModel, PatientListAdapter.PatientHolder>(PatientDiffCallback()) {

    class PatientHolder(private val binding: ItemPatientBinding, private val deleteInterface: DeleteByIdInterface) :
        RecyclerView.ViewHolder(binding.root) {

        //связываются данные и берутся откуда ввели, чтоб вставить куда надо
        fun bind(patientModel: PatientModel, clickListener: PatientListener) {
            binding.apply {
                tvData.text = patientModel.date
                tvNameCall.text = patientModel.nameCall
                tvNumberCall.text = patientModel.numberCall
                tvAddress.text = patientModel.addressPatient
                root.setOnClickListener {
                    clickListener.onClickPatient(patientModel)
                }

                binding.btnDel.setOnClickListener {
                    deleteInterface.patientDeleteClick(patientModel.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val binding = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientHolder(binding, deleteInterface)
    }

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class PatientDiffCallback : DiffUtil.ItemCallback<PatientModel>() {
    override fun areItemsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PatientModel, newItem: PatientModel): Boolean {
        return oldItem == newItem
    }
}

class PatientListener(val clickListener: (patientModel: PatientModel) -> Unit) {
    fun onClickPatient(patientModel: PatientModel) = clickListener(patientModel)
}