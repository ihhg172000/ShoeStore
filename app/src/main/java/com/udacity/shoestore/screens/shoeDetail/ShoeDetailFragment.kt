package com.udacity.shoestore.screens.shoeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.screens.SharedViewModel

class ShoeDetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.sharedViewModel = sharedViewModel

        initObservers()

        return binding.root
    }

    private fun initObservers() {

        sharedViewModel.eventAddShoe.observe(viewLifecycleOwner) { event ->
            if (event) {
                findNavController().popBackStack()
                sharedViewModel.onAddShoeComplete()
            }
        }

        sharedViewModel.eventCancelAddShoe.observe(viewLifecycleOwner) { event ->
            if (event) {
                findNavController().popBackStack()
                sharedViewModel.onCancelAddShoeComplete()
            }
        }

    }

}