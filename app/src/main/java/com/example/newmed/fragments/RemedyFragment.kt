package com.example.newmed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.adapter.RemedyListAdapter
import com.example.newmed.adapter.RemedyListener
import com.example.newmed.databinding.FragmentRemedyListBinding
import com.example.newmed.models.RemedyViewModel
import com.example.newmed.reposotiry.RemedyViewModelFactory

class RemedyFragment : Fragment() {

    private lateinit var binding: FragmentRemedyListBinding

    private val remedyViewModel: RemedyViewModel by viewModels {
        RemedyViewModelFactory((activity?.application as PatientApplication).repositoryRemedy)
    }

    private lateinit var adapter: RemedyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemedyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RemedyListAdapter(RemedyListener {

        })

                //сохранение в БД
            binding.btnAddRemedy.setOnClickListener {
                remedyViewModel.addRemedy(
                    binding.etRemedy.text.toString(),
                    binding.etAmountRemedy.text.toString().toInt(),
                )
            }

        //отображение из БД
        binding.remedyRecyclerView.adapter = adapter
        remedyViewModel.allRemedy.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

/*
        //появление кнопки созранить
        binding.btnAddMedications.setOnClickListener {
            binding.btnSaveMedications.isVisible = true

             //дописать возможность редактирования кол-ва
        }


        //скрытие кнопки сохранить после ее нажатия
        binding.btnSaveMedications.setOnClickListener {
            binding.btnSaveMedications.isVisible = false

            //дописать сохранение внесенных изменений в кол-во*/
    }
}