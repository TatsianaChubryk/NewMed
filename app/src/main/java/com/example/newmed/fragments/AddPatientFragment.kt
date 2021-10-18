package com.example.newmed.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.models.PatientViewModel
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

        //дата
        val formatter = SimpleDateFormat("dd.MM.yy")
        val date = Calendar.getInstance().time
        val timeString = formatter.format(date)
            //DateFormat.getDateTimeInstance(3, 3).format(Date())
        binding.tvData.setText(timeString).toString()

        isCheckedCheckBox() //переключатель для чекбоксов
        isCheckedSwitch() //переключатель switch


        binding.btnAddPatient.setOnClickListener {

            patientViewModel.addPatient(
                binding.tvData.text.toString(),
                binding.etNameCall.editText?.text.toString(),
                binding.etNumberCall.editText?.text.toString(),
                binding.etAddressPatient.editText?.text.toString(),
                binding.etNamePatient.editText?.text.toString(),
                binding.etAgePatient.editText?.text.toString(),
                binding.etNumberPatient.editText?.text.toString(),
                binding.etPricePatient.editText?.text.toString()
            )
            //Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
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
        binding.cbWoman.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbMan.isChecked = false
            }
        }

        binding.cbMan.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbWoman.isChecked = false
            }
        }

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