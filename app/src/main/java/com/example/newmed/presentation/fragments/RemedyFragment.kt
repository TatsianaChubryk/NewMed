package com.example.newmed.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.presentation.adapter.RemedyListAdapter
import com.example.newmed.presentation.adapter.RemedyListener
import com.example.newmed.databinding.FragmentRemedyListBinding
import com.example.newmed.presentation.viewmodel.RemedyViewModel
import com.example.newmed.data.reposotiry.RemedyViewModelFactory
import org.koin.android.ext.android.get

class RemedyFragment : Fragment() {

    private lateinit var binding: FragmentRemedyListBinding

    private val remedyViewModel: RemedyViewModel by viewModels {
        RemedyViewModelFactory((activity?.application as PatientApplication).get())
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

        //отображение из БД
        binding.remedyRecyclerView.adapter = adapter
        remedyViewModel.allRemedy.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}