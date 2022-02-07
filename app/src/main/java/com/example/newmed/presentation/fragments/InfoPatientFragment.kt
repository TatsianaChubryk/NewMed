package com.example.newmed.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.reposotiry.PatientViewModelFactory

class InfoPatientFragment : Fragment() {

    private lateinit var binding: FragmentInfoPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        patientViewModel.getPatientById(arguments?.getInt("patientId") ?: 0)
    }

    companion object {
        fun newInstance(patientId: Int): InfoPatientFragment {
            val args = Bundle()
            args.putInt("patientId", patientId)
            val fragment = InfoPatientFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPatient()
        isCheckedSwitch()

        binding.btnEditPatient.setOnClickListener { updatePatient() }
    }

    private fun getPatient() {
        patientViewModel.patient.observe(viewLifecycleOwner) {
            binding.tvData.text = it.date
            binding.switchActive.isChecked = it.active
            binding.tvNameCall.text = it.nameCall
            binding.tvNumberCall.text = it.numberCall
            binding.tvAddress.text = it.addressPatient
            binding.namePatient.text = Editable.Factory.getInstance().newEditable(it.namePatient)
            binding.numberPatient.text =
                Editable.Factory.getInstance().newEditable(it.numberPatient)
            binding.tvAgePatient.text = Editable.Factory.getInstance().newEditable(it.agePatient)

            if (it.alko) {
                binding.tvDiagnosis.text = "Синдром отмены от алкоголя"
            } else
                binding.tvDiagnosis.text = "Синдром отмены от наркотических средств"

            binding.cbTraumaticBrain.isChecked = it.traumaticBrain
            binding.cbDiabetes.isChecked = it.diabetes
            binding.cbHypertension.isChecked = it.hypertension
            binding.cbIschemia.isChecked = it.ishemiya
            binding.cbArrhythmia.isChecked = it.arrhytmia
            binding.cbGemma.isChecked = it.gemma
            binding.cbCirrhosis.isChecked = it.cirrhosis
            binding.etDistance.text =
                Editable.Factory.getInstance().newEditable(it.distance.toString())
            binding.etTime.text = Editable.Factory.getInstance().newEditable(it.time.toString())
            binding.etMin.text = Editable.Factory.getInstance().newEditable(it.min.toString())
            binding.etPrice.text =
                Editable.Factory.getInstance().newEditable(it.pricePatient.toString())
            if (it.dayNight) {
                binding.switchDayNight.isChecked = true
                binding.imgDay.isVisible = false
                binding.imgNight.isVisible = true
            } else {
                binding.switchDayNight.isChecked = false
                binding.imgDay.isVisible = true
                binding.imgNight.isVisible = false
            }
        }
    }

    private fun updatePatient() {
        val data = binding.tvData.text.toString()
        val active = binding.switchActive.isChecked
        val nameCall = binding.tvNameCall.text.toString()
        val numberCall = binding.tvNumberCall.text.toString()
        val addressPatient = binding.tvAddress.text.toString()
        val namePatient = binding.namePatient.text.toString()
        val agePatient = binding.tvAgePatient.text.toString()
        val numberPatient = binding.numberPatient.text.toString()
        val pricePatient = binding.etPrice.text.toString().toInt()
        val dayNight = binding.switchDayNight.isChecked
        val distance = binding.etDistance.text.toString().toInt()
        val time = binding.etTime.text.toString().toInt()
        val min = binding.etMin.text.toString().toInt()
        val cbBrain = binding.cbTraumaticBrain.isChecked
        val cbDiabetes = binding.cbDiabetes.isChecked
        val cbHypertension = binding.cbHypertension.isChecked
        val cbIschemia = binding.cbIschemia.isChecked
        val cbArrhythmia = binding.cbArrhythmia.isChecked
        val cbGemma = binding.cbGemma.isChecked
        val cbCirrhosis = binding.cbCirrhosis.isChecked

        val update = PatientEntity(
            arguments?.getInt("patientId") ?: 0,
            data,
            active,
            nameCall,
            numberCall,
            addressPatient,
            namePatient,
            agePatient,
            numberPatient,
            pricePatient,
            dayNight,
            alko = true,
            distance,
            time,
            min,
            cbBrain,
            cbDiabetes,
            cbHypertension,
            cbIschemia,
            cbArrhythmia,
            cbGemma,
            cbCirrhosis
        )
        patientViewModel.updatePatient(update)
        Toast.makeText(requireContext(), getString(R.string.update_info), Toast.LENGTH_SHORT).show()
    }

    private fun isCheckedSwitch() {

        binding.switchVyzov.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.line7.isVisible = true
            } else if (!isChecked) {
                binding.line7.isVisible = false
            }
        }

        binding.switchDayNight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.imgDay.isVisible = false
                binding.imgNight.isVisible = true
            } else if (!isChecked) {
                binding.imgDay.isVisible = true
                binding.imgNight.isVisible = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }
}