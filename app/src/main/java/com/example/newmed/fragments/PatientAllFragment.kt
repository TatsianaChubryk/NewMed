package com.example.newmed.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.adapter.PatientListAdapter
import com.example.newmed.adapter.PatientListener
import com.example.newmed.databinding.FragmentPatientListBinding
import com.example.newmed.models.PatientViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory

class PatientAllFragment : Fragment() {

    private lateinit var binding: FragmentPatientListBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    private lateinit var adapter: PatientListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = PatientListAdapter(PatientListener {

            //передать переход на доп инфу о пациенте

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame, InfoPatientFragment())
                ?.commit()
        })

        binding.patientRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}