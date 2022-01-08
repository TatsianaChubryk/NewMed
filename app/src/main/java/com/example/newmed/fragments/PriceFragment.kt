package com.example.newmed.fragments

import android.os.Bundle
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
import kotlinx.android.synthetic.main.item_price.*
import kotlinx.android.synthetic.main.item_price.view.*

class PriceFragment : Fragment() {

    private lateinit var binding: FragmentPriceBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

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


        adapter = PriceListAdapter(PriceListener {})

        binding.priceRecyclerView.adapter = adapter

        patientViewModel.allPatient.observe(viewLifecycleOwner) {
           adapter.submitList(it)
        }

        //очищаем полностью БД
        binding.btnClear.setOnClickListener {
            patientViewModel.clearDB()
        }
    }
}