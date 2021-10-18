package com.example.newmed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.adapter.PatientInfoListAdapter
import com.example.newmed.adapter.PatientInfoListener
import com.example.newmed.adapter.PatientListAdapter
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.models.PatientViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory

class InfoPatientFragment : Fragment() {

    private lateinit var binding: FragmentInfoPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    private lateinit var adapter: PatientInfoListAdapter

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

        adapter = PatientInfoListAdapter(PatientInfoListener {

            //что будет происходить по клику

        })

        binding.patientInfoRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}