package com.example.newmed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.adapter.PatientListAdapter
import com.example.newmed.adapter.RemedyListAdapter
import com.example.newmed.adapter.RemedyListener
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.models.PatientViewModel
import com.example.newmed.models.RemedyViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory
import com.example.newmed.reposotiry.RemedyViewModelFactory

class InfoPatientFragment : Fragment() {

    private lateinit var binding: FragmentInfoPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    private val remedyViewModel: RemedyViewModel by viewModels {
        RemedyViewModelFactory((activity?.application as PatientApplication).repositoryRemedy)
    }

   // private lateinit var adapter: PatientListAdapter

    private lateinit var adapter: RemedyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        patientViewModel.getPatientById(arguments?.getInt("patientId") ?: 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoPatientBinding.inflate(inflater, container, false)
        return binding.root
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEdit.setOnClickListener {
            binding.etAd.isVisible = true
            binding.tvAd.isVisible = false
            binding.btnSave.isVisible = true
        }

        adapter = RemedyListAdapter(RemedyListener {

        })

        patientViewModel.patient.observe(viewLifecycleOwner) {
            binding.namePatient.text = it.namePatient
            binding.numberPatient.text = it.numberPatient
            binding.tvAgePatient.text = (it.agePatient + " лет")
        }

        //отображение из БД
        binding.remedyRecyclerView.adapter = adapter
        remedyViewModel.allRemedy.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}