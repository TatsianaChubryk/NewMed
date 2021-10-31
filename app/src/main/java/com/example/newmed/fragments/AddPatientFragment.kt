package com.example.newmed.fragments

import android.app.Notification
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.database.PatientDao
import com.example.newmed.viewmodel.PatientViewModel
import com.example.newmed.databinding.FragmentAddPatientBinding
import com.example.newmed.reposotiry.PatientViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class AddPatientFragment : Fragment() {

    private lateinit var binding: FragmentAddPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val price = "185"
        val etPrice = binding.etPricePatient.editText
        etPrice?.text = Editable.Factory.getInstance().newEditable(price)

        dateFormat()
        isCheckedCheckBox()
        isCheckedSwitch()

        binding.btnAddPatient.setOnClickListener {

            patientViewModel.addPatient(
                binding.tvData.text.toString(),
                binding.etNameCall.editText?.text.toString(),
                binding.etNumberCall.editText?.text.toString(),
                binding.etAddressPatient.editText?.text.toString(),
                binding.etNamePatient.editText?.text.toString(),
                binding.etAgePatient.editText?.text.toString(),
                binding.etNumberPatient.editText?.text.toString(),
                binding.etPricePatient.editText?.text.toString().toInt(),
                binding.cbAlcohol.isChecked,
                binding.cbTraumaticBrain.isChecked,
                binding.cbDiabetes.isChecked,
                binding.cbHypertension.isChecked,
                binding.cbIschemia.isChecked,
                binding.cbArrhythmia.isChecked,
                binding.cbGemma.isChecked,
                binding.cbCirrhosis.isChecked,
                0, 0,0,
                0, 0,
                0,
                0,
                0,
                0,
                0,
                0
            )
            activity?.onBackPressed()
        }
    }

    private fun dateFormat() {
        val formatter = SimpleDateFormat("dd.MM.yy")
        val date = Calendar.getInstance().time
        val timeString = formatter.format(date)
        binding.tvData.setText(timeString).toString()
    }

    private fun isCheckedSwitch() {

        binding.switchDiseases.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.line3.isVisible = true
            } else if (!isChecked) {
                binding.line3.isVisible = false
            }
        }
    }

    private fun isCheckedCheckBox() {
        binding.cbAlcohol.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbDrug.isChecked = false
            }
        }

        binding.cbDrug.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbAlcohol.isChecked = false
            }
        }
    }
}