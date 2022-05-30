package com.example.newmed.presentation.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.reposotiry.PatientViewModelFactory
import kotlinx.android.synthetic.main.fragment_info_patient.*

class InfoPatientFragment : Fragment() {

    private lateinit var binding: FragmentInfoPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory(
            ((requireActivity().application) as PatientApplication).repository
        )/*,
            ((requireActivity().application) as PatientApplication).deleteById
        )*/
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

        binding.saveButton.setOnClickListener { updatePatient() }
        binding.imgNumberCall.setOnClickListener { callPerson() }
        binding.imgNumber.setOnClickListener { callPatient() }
        checkPermission()
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CALL_PHONE),
                101
            )
        }
    }

    private fun getPatient() {
        patientViewModel.patient.observe(viewLifecycleOwner) {
            binding.tvData.setText(it.date)
            binding.switchActive.isChecked = it.active
            binding.etNameCall.editText?.setText(it.nameCall)
            binding.etNumberCall.editText?.setText(it.numberCall)
            binding.etAddress.editText?.setText(it.addressPatient)
            binding.etPrice.editText?.text =
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
            binding.etDistance.editText?.setText(it.distance.toString())
            binding.etHour.editText?.setText(it.time.toString())
            binding.etMin.editText?.setText(it.min.toString())
            binding.etNamePatient.editText?.setText(it.namePatient)
            binding.etNumberPatient.editText?.setText(it.numberPatient)
            binding.etAgePatient.editText?.setText(it.agePatient)
            binding.cbTraumaticBrain.isChecked = it.traumaticBrain
            binding.cbDiabetes.isChecked = it.diabetes
            binding.cbHypertension.isChecked = it.hypertension
            binding.cbIschemia.isChecked = it.ishemiya
            binding.cbArrhythmia.isChecked = it.arrhytmia
            binding.cbGemma.isChecked = it.gemma
            binding.cbCirrhosis.isChecked = it.cirrhosis
        }
    }

    private fun updatePatient() {
        val data = binding.tvData.text.toString()
        val active = binding.switchActive.isChecked
        val nameCall = binding.etNameCall.editText?.text.toString()
        val numberCall = binding.etNumberCall.editText?.text.toString()
        val addressPatient = binding.etAddress.editText?.text.toString()
        val namePatient = binding.etNamePatient.editText?.text.toString()
        val agePatient = binding.etAgePatient.editText?.text.toString()
        val numberPatient = binding.etNumberPatient.editText?.text.toString()
        val pricePatient = binding.etPrice.editText?.text.toString().toInt()
        val dayNight = binding.switchDayNight.isChecked
        val distance = binding.etDistance.editText?.text.toString().toInt()
        val time = binding.etHour.editText?.text.toString().toInt()
        val min = binding.etMin.editText?.text.toString().toInt()
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

    private fun callPatient() {
        val phoneNumber = binding.etNumberPatient.editText?.text.toString()
        if (phoneNumber.isNotEmpty()) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }
    }

    private fun callPerson() {
        val phoneNumber = binding.etNumberCall.editText?.text.toString()
        if (phoneNumber.isNotEmpty()) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }
}