package com.example.newmed.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.newmed.databinding.FragmentAddPatientBinding
import java.text.DateFormat
import java.util.*

class AddPatientFragment : Fragment() {

    private lateinit var binding: FragmentAddPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timeString = DateFormat.getDateTimeInstance(3, 3).format(Date())
        binding.tvData.setText(timeString).toString()

        isCheckedCheckBox() //переключатель для чекбоксов
        isCheckedSwitch() //переключатель switch
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