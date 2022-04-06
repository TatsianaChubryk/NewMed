package com.example.newmed.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.newmed.MapsFragment
import com.example.newmed.R
import com.example.newmed.databinding.FragmentMenuBinding
import com.example.newmed.presentation.interfaces.DeleteByIdInterface

class MenuFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clipToOutline()

        binding?.imgAddPatient?.setOnClickListener(this)
        binding?.imgAllPatients?.setOnClickListener(this)
        binding?.imgMoney?.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        activity?.let {
            val fragment = when (v) {
                binding?.imgAddPatient -> AddPatientFragment()
                binding?.imgAllPatients -> PatientAllFragment()
                binding?.imgMoney -> PriceFragment()
                else -> null
            }

            fragment?.let { frag ->
                it.supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_out,
                        R.anim.slide_in,
                        R.anim.back_in,
                        R.anim.back_out
                    )
                    replace(R.id.frame, frag)
                    addToBackStack(frag::class.java.canonicalName)
                }
            }
        }
    }

    private fun clipToOutline() {
        binding?.container4?.clipToOutline = true
        binding?.imgAddPatient?.clipToOutline = true
        binding?.imgAllPatients?.clipToOutline = true
        binding?.imgMoney?.clipToOutline = true
    }
}