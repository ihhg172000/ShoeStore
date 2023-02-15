package com.udacity.shoestore.screens.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.screens.SharedViewModel

class InstructionFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction,
            container,
            false
        )

        binding.sharedViewModel = sharedViewModel

        initObservers()

        return binding.root
    }

    private fun initObservers() {

        sharedViewModel.eventNavigateFromInstructionToShoeList.observe(viewLifecycleOwner) { event ->
            if (event) {
                val action = InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment()
                findNavController().navigate(action)
                sharedViewModel.onNavigateFromInstructionToShoeListComplete()
            }
        }

    }

}