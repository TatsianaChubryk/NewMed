package com.example.newmed.presentation.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.presentation.adapter.PatientListAdapter
import com.example.newmed.presentation.adapter.PatientListener
import com.example.newmed.databinding.FragmentPatientListBinding
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.reposotiry.PatientViewModelFactory
import com.example.newmed.presentation.interfaces.CallInterface
import com.example.newmed.presentation.interfaces.DeleteByIdInterface
import com.example.newmed.presentation.interfaces.OnBackInterface
import kotlinx.android.synthetic.main.item_patient.*

class PatientAllFragment : Fragment(), CallInterface, OnBackInterface {

    private lateinit var binding: FragmentPatientListBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory(
            ((requireActivity().application) as PatientApplication).repository
        )
        //((requireActivity().application) as PatientApplication).deleteById

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
            }
        }, object : CallInterface {
            override fun callInterface(id: Int) {
                patientViewModel.callPatient(id)
                callPatient()
            }

        })

        binding.patientRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
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

    private fun callPatient() {
        patientViewModel.getPatientById(arguments?.getInt("patientId") ?: 0)
        val phoneNumber = tvNumberCall.text.toString()
        if (phoneNumber.isNotEmpty()) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }
    }

    override fun callInterface(id: Int) {
       patientViewModel.callPatient(id)
        callPatient()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}