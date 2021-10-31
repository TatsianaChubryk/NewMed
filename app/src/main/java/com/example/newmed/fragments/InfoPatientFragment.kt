package com.example.newmed.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newmed.PatientApplication
import com.example.newmed.R
import com.example.newmed.adapter.PatientListAdapter
import com.example.newmed.adapter.PatientListener
import com.example.newmed.adapter.RemedyListAdapter
import com.example.newmed.adapter.RemedyListener
import com.example.newmed.database.PatientEntity
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.databinding.FragmentPatientListBinding
import com.example.newmed.viewmodel.PatientViewModel
import com.example.newmed.viewmodel.RemedyViewModel
import com.example.newmed.reposotiry.PatientViewModelFactory
import com.example.newmed.reposotiry.RemedyViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_patient.view.*
import kotlinx.android.synthetic.main.fragment_add_patient.view.tvData
import kotlinx.android.synthetic.main.fragment_info_patient.*
import kotlinx.android.synthetic.main.item_price.view.*

class InfoPatientFragment : Fragment() {

    private lateinit var binding: FragmentInfoPatientBinding

    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((activity?.application as PatientApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        patientViewModel.getPatientById(arguments?.getInt("patientId") ?: 0)

    }

    companion object {
        fun newInstance(patientId: Int): InfoPatientFragment {
            val args = Bundle()
            args.putInt("patientId", patientId)
            val fragment = InfoPatientFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPatient()

        binding.btnSaveInfoPatient.setOnClickListener {
            updatePatient()
        }
    }

    private fun getPatient() {
        patientViewModel.patient.observe(viewLifecycleOwner) {
            binding.tvData.text = it.date
            binding.tvPrice.text = it.pricePatient.toString()
            binding.tvNameCall.text = it.nameCall
            binding.tvNumberCall.text = it.numberCall
            binding.tvAddress.text = it.addressPatient
            binding.namePatient.text = it.namePatient
            binding.numberPatient.text =
                Editable.Factory.getInstance().newEditable(it.numberPatient)
            binding.tvAgePatient.text = it.agePatient

            if (it.alko) {
                binding.tvDiagnosis.text = "Алкогольная интоксикация"
            } else
                binding.tvDiagnosis.text = "Интоксикация психоактиными веществами"

            binding.cbTraumaticBrain.isChecked = it.traumaticBrain
            binding.cbDiabetes.isChecked = it.diabetes
            binding.cbHypertension.isChecked = it.hypertension
            binding.cbIschemia.isChecked = it.ishemiya
            binding.cbArrhythmia.isChecked = it.arrhytmia
            binding.cbGemma.isChecked = it.gemma
            binding.cbCirrhosis.isChecked = it.cirrhosis
            binding.etMagnia.text = Editable.Factory.getInstance().newEditable(it.magnia.toString())
            binding.etRingera.text = Editable.Factory.getInstance().newEditable(it.ringera.toString())
            binding.etGaloperidol.text = Editable.Factory.getInstance().newEditable(it.galoperidol.toString())
            binding.etDimedrol.text = Editable.Factory.getInstance().newEditable(it.dimedrol.toString())
            binding.etFenibut.text = Editable.Factory.getInstance().newEditable(it.fenibut.toString())
            binding.etTiamin.text = Editable.Factory.getInstance().newEditable(it.tiamin.toString())
            binding.etUnitiol.text = Editable.Factory.getInstance().newEditable(it.unitiol.toString())
            binding.etSonnat.text = Editable.Factory.getInstance().newEditable(it.sonnat.toString())
            binding.etKarbazipin.text = Editable.Factory.getInstance().newEditable(it.karbazipin.toString())
            binding.etNormogidron.text = Editable.Factory.getInstance().newEditable(it.normogidron.toString())
            binding.etAnaprilin.text = Editable.Factory.getInstance().newEditable(it.anaprilin.toString())
        }
    }

    private fun updatePatient() {
        val data = binding.tvData.text.toString()
        val nameCall = binding.tvNameCall.text.toString()
        val numberCall = binding.tvNumberCall.text.toString()
        val addressPatient = binding.tvAddress.text.toString()
        val namePatient = binding.namePatient.text.toString()
        val agePatient = binding.tvAgePatient.text.toString()
        val numberPatient = binding.numberPatient.text.toString()
        val pricePatient = binding.tvPrice.text.toString().toInt()
        val cbBrain = binding.cbTraumaticBrain.isChecked
        val cbDiabetes = binding.cbDiabetes.isChecked
        val cbHypertension = binding.cbHypertension.isChecked
        val cbIschemia = binding.cbIschemia.isChecked
        val cbArrhythmia = binding.cbArrhythmia.isChecked
        val cbGemma = binding.cbGemma.isChecked
        val cbCirrhosis = binding.cbCirrhosis.isChecked
        val magnia = binding.etMagnia.text.toString().toInt()
        val ringera = binding.etRingera.text.toString().toInt()
        val galoperidol = binding.etGaloperidol.text.toString().toInt()
        val dimedrol = binding.etDimedrol.text.toString().toInt()
        val fenibut = binding.etFenibut.text.toString().toInt()
        val tiamin = binding.etTiamin.text.toString().toInt()
        val unitiol = binding.etUnitiol.text.toString().toInt()
        val sonnat = binding.etSonnat.text.toString().toInt()
        val karbazipin = binding.etKarbazipin.text.toString().toInt()
        val normogidron = binding.etNormogidron.text.toString().toInt()
        val anaprilin = binding.etAnaprilin.text.toString().toInt()

        val update = PatientEntity(
            arguments?.getInt("patientId") ?: 0,
            data,
            nameCall,
            numberCall,
            addressPatient,
            namePatient,
            agePatient,
            numberPatient,
            pricePatient,
            alko = true,
            cbBrain,
            cbDiabetes,
            cbHypertension,
            cbIschemia,
            cbArrhythmia,
            cbGemma,
            cbCirrhosis,
            magnia,
            ringera,
            galoperidol,
            dimedrol,
            fenibut,
            tiamin,
            unitiol,
            sonnat,
            karbazipin,
            normogidron,
            anaprilin
        )
        patientViewModel.updatePatient(update)
        Toast.makeText(requireContext(), getString(R.string.update_info), Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
         binding
    }
}