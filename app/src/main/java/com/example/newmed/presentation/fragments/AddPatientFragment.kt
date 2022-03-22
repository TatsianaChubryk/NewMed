package com.example.newmed.presentation.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.data.reposotiry.PatientRepository
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.databinding.FragmentAddPatientBinding
import com.example.newmed.data.reposotiry.PatientViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class AddPatientFragment : Fragment() {

    private lateinit var binding: FragmentAddPatientBinding

   // private var binding: FragmentAddPatientBinding? = null


   /* private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }*/

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory(
            ((requireActivity().application) as PatientApplication).repository,
            ((requireActivity().application) as PatientApplication).deleteById
        )
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

        dateFormat()
        isCheckedCheckBox()
        isCheckedSwitch()
        insertNameAndPhone()
        insertPrise()

        binding.btnAddPatient.setOnClickListener { addPatient() }
    }

    private fun addPatient() {
        val insert = PatientEntity(
            arguments?.getInt("patientId") ?: 0,
            binding.tvData.text.toString(),
            binding.switchActive.isChecked,
            binding.etNameCall.editText?.text.toString(),
            binding.etNumberCall.editText?.text.toString(),
            binding.etAddressPatient.editText?.text.toString(),
            binding.etNamePatient.editText?.text.toString(),
            binding.etAgePatient.editText?.text.toString(),
            binding.etNumberPatient.editText?.text.toString(),
            binding.etPricePatient.editText?.text.toString().toInt(),
            binding.switchDayNight.isChecked,
            alko = true,
            0,
            0,
            0,
            binding.cbTraumaticBrain.isChecked,
            binding.cbDiabetes.isChecked,
            binding.cbHypertension.isChecked,
            binding.cbIschemia.isChecked,
            binding.cbArrhythmia.isChecked,
            binding.cbGemma.isChecked,
            binding.cbCirrhosis.isChecked
        )
        patientViewModel.addPatient(insert)
        Toast.makeText(requireContext(), getString(R.string.add_patient), Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }

    private fun insertPrise() {
        val priceDay = "165"
        val priceNight = "175"
        val etPrice = binding.etPricePatient.editText //обращаться к editText и менять его напрямую
        etPrice?.text = Editable.Factory.getInstance().newEditable(priceDay)

        binding.switchDayNight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.imgDay.isVisible = false
                binding.imgNight.isVisible = true
                etPrice?.text = Editable.Factory.getInstance().newEditable(priceNight)
            } else if (!isChecked) {
                binding.imgDay.isVisible = true
                binding.imgNight.isVisible = false
                etPrice?.text = Editable.Factory.getInstance().newEditable(priceDay)
            }
        }
    }

    private fun insertNameAndPhone() {
        binding.imgPatient.setOnClickListener {
            val nameCall = binding.etNameCall.editText?.text
            val numberCall = binding.etNumberCall.editText?.text
            val namePatient = binding.etNamePatient.editText
            val numberPatient = binding.etNumberPatient.editText
            namePatient?.text = Editable.Factory.getInstance().newEditable(nameCall.toString())
            numberPatient?.text = Editable.Factory.getInstance().newEditable(numberCall.toString())
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