package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.screens.SharedViewModel

class WelcomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        binding.sharedVieModel = sharedViewModel

        initObservers()

        return binding.root
    }

    private fun initObservers() {

        sharedViewModel.eventNavigateFromWelcomeToInstruction.observe(viewLifecycleOwner) { event ->
            if (event) {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment()
                findNavController().navigate(action)
                sharedViewModel.onNavigateFromWelcomeToInstructionComplete()
            }
        }

    }

}