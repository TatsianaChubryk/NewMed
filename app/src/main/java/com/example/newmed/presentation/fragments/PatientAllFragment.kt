package com.example.newmed.presentation.fragments

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
import com.example.newmed.presentation.adapter.PatientListAdapter
import com.example.newmed.presentation.adapter.PatientListener
import com.example.newmed.databinding.FragmentPatientListBinding
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.reposotiry.PatientViewModelFactory
import com.example.newmed.presentation.SwipeToDelete
import com.example.newmed.presentation.interfaces.DeleteByIdInterface
import com.example.newmed.presentation.interfaces.PatientDeleteById

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

            val args = Bundle()
            args.putInt("patientId", it.id)

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame, InfoPatientFragment.newInstance(patientId = it.id))
                ?.commit()
        }, object : DeleteByIdInterface {
            override fun patientDeleteClick(id: Int) {
                patientViewModel.deletePatientById(id)
                Toast.makeText(requireContext(), "Запись удалена", Toast.LENGTH_LONG).show()
            }

        })

        binding.patientRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.patientRecyclerView.apply {
            val swipeDelete = object : SwipeToDelete(context) {
                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {

                }
            }

            val touchHelper = ItemTouchHelper(swipeDelete)
            touchHelper.attachToRecyclerView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }
}