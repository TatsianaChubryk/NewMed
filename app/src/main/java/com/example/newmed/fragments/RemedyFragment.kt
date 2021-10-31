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
import com.example.newmed.viewmodel.RemedyViewModel
import com.example.newmed.reposotiry.RemedyViewModelFactory
import kotlinx.android.synthetic.main.item_remedy.view.*

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

        //отображение из БД
        binding.remedyRecyclerView.adapter = adapter
        remedyViewModel.allRemedy.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}