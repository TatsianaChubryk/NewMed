package com.example.newmed.fragments

import android.graphics.drawable.AnimatedVectorDrawable
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.adapter.PriceListAdapter
import com.example.newmed.adapter.PriceListener
import com.example.newmed.databinding.FragmentPriceBinding
import com.example.newmed.viewmodel.PatientViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory
import com.example.newmed.viewmodel.PriceViewModel

class PriceFragment : Fragment() {

    private lateinit var binding: FragmentPriceBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    private val priceViewModel: PriceViewModel by viewModels()

    private lateinit var adapter: PriceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val tvExchange = binding.tvExchange
        priceViewModel.getExchange()
        priceViewModel.exchangeLiveData.observe(viewLifecycleOwner, {
            //Log.e("price", it.joinToString())
            binding.tvExchange.text = it.first().USD_out
        })

        adapter = PriceListAdapter(PriceListener {

            //что будет происходить по клику

        })

        binding.priceRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}