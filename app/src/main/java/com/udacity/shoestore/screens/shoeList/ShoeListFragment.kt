package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ElementShoeBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.screens.SharedViewModel

class ShoeListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.sharedViewModel = sharedViewModel

        initMenu()
        initObservers()

        return binding.root
    }

    private fun initMenu() {

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_item_logout -> {
                        sharedViewModel.onLogoutStart()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun initObservers() {

        sharedViewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            val linearLayoutShoeList = binding.linearLayoutShoeList

            var index = 0
            while (index < shoeList.size) {
                val elementShoeBinding: ElementShoeBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.element_shoe,
                    linearLayoutShoeList,
                    false
                )

                elementShoeBinding.shoe = shoeList[index]
                linearLayoutShoeList.addView(elementShoeBinding.root)

                index++
            }
        }

        sharedViewModel.eventNavigateFromShoeListToShoeDetail.observe(viewLifecycleOwner) { event ->
            if (event) {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
                findNavController().navigate(action)
                sharedViewModel.onNavigateFromShoeListToShoeDetailComplete()
            }
        }

        sharedViewModel.eventLogout.observe(viewLifecycleOwner) { event ->
            if (event) {
                val action = ShoeListFragmentDirections.actionGlobalLoginFragment()
                findNavController().navigate(action)
                sharedViewModel.onLogoutComplete()
            }
        }

    }

}