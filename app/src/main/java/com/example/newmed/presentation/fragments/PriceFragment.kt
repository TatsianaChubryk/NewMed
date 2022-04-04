package com.example.newmed.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newmed.PatientApplication
import com.example.newmed.presentation.adapter.PriceListAdapter
import com.example.newmed.presentation.adapter.PriceListener
import com.example.newmed.databinding.FragmentPriceBinding
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.reposotiry.PatientViewModelFactory
import com.example.newmed.presentation.SwipeToDelete

class PriceFragment : Fragment() {

    private lateinit var binding: FragmentPriceBinding

    /*private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }*/

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory(
            ((requireActivity().application) as PatientApplication).repository
        )
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
/*
        val mRecyclerView: RecyclerView? = null
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView)

        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
               override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   // deleteNote(mNotes.get(viewHolder.adapterPosition))
                }
            }*/
    }
}