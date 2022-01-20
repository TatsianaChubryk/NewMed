package com.example.newmed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.SwipeToDelete
import com.example.newmed.adapter.PatientListAdapter
import com.example.newmed.adapter.PatientListener
import com.example.newmed.database.PatientEntity
import com.example.newmed.databinding.FragmentPatientListBinding
import com.example.newmed.viewmodel.PatientViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory
import kotlinx.android.synthetic.main.fragment_patient_list.*
import kotlinx.android.synthetic.main.fragment_patient_list.view.*
import kotlinx.android.synthetic.main.item_patient.view.*
import org.koin.android.ext.android.get

class PatientAllFragment : Fragment() {

    private lateinit var binding: FragmentPatientListBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).get())
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

            val args = Bundle()
            args.putInt("patientId", it.id)

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame, InfoPatientFragment.newInstance(patientId = it.id))
                ?.commit()
        })

        binding.patientRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.patientRecyclerView.apply {
            val swipeDelete = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    patientViewModel.deletePatientById(arguments?.getInt("patientId") ?: 0)
                }
            }

            val touchHelper = ItemTouchHelper(swipeDelete)
            touchHelper.attachToRecyclerView(binding.patientRecyclerView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }
}