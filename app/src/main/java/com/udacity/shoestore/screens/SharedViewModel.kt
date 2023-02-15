package com.udacity.shoestore.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    // Login
    private val _eventLogin = MutableLiveData(false)
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    // Welcome
    private val _eventNavigateFromWelcomeToInstruction = MutableLiveData(false)
    val eventNavigateFromWelcomeToInstruction: LiveData<Boolean>
        get() = _eventNavigateFromWelcomeToInstruction

    // Instruction
    private val _eventNavigateFromInstructionToShoeList = MutableLiveData(false)
    val eventNavigateFromInstructionToShoeList: LiveData<Boolean>
        get() = _eventNavigateFromInstructionToShoeList

    // Shoe List
    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _eventNavigateFromShoeListToShoeDetail = MutableLiveData(false)
    val eventNavigateFromShoeListToShoeDetail: LiveData<Boolean>
        get() = _eventNavigateFromShoeListToShoeDetail

    private val _eventLogout = MutableLiveData(false)
    val eventLogout: LiveData<Boolean>
        get() = _eventLogout

    // Shoe Detail
    val currentShoe = Shoe("", 0.0, "", "")

    private val _eventAddShoe = MutableLiveData(false)
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe

    private val _eventCancelAddShoe = MutableLiveData(false)
    val eventCancelAddShoe: LiveData<Boolean>
        get() = _eventCancelAddShoe

    // Login
    fun onLoginStart() {
        _eventLogin.value = true
    }

    fun onLoginComplete() {
        _eventLogin.value = false
    }

    // Welcome
    fun onNavigateFromWelcomeToInstructionStart() {
        _eventNavigateFromWelcomeToInstruction.value = true
    }
    fun onNavigateFromWelcomeToInstructionComplete() {
        _eventNavigateFromWelcomeToInstruction.value = false
    }

    // Instruction
    fun onNavigateFromInstructionToShoeListStart() {
        _eventNavigateFromInstructionToShoeList.value = true
    }

    fun onNavigateFromInstructionToShoeListComplete() {
        _eventNavigateFromInstructionToShoeList.value = false
    }

    // Shoe List
    fun onNavigateFromShoeListToShoeDetailStart() {
        _eventNavigateFromShoeListToShoeDetail.value = true
    }

    fun onNavigateFromShoeListToShoeDetailComplete() {
        _eventNavigateFromShoeListToShoeDetail.value = false
    }

    fun onLogoutStart() {
        _eventLogout.value = true
    }

    fun onLogoutComplete() {
        _eventLogout.value = false
    }

    // Show Detail
    fun onAddShoeStart() {
        addShoe()
        _eventAddShoe.value = true
    }

    private fun addShoe() {

        _shoeList.value?.let { newList ->

            val newShoe = Shoe(
                currentShoe.name,
                currentShoe.size,
                currentShoe.company,
                currentShoe.description)

            newList.add(newShoe)
            _shoeList.value = newList

        }

    }

    fun onAddShoeComplete() {
        clearCurrentShoe()
        _eventAddShoe.value = false
    }


    fun onCancelAddShoeStart() {
        _eventCancelAddShoe.value = true
    }

    fun onCancelAddShoeComplete() {
        clearCurrentShoe()
        _eventCancelAddShoe.value = false
    }

    private fun clearCurrentShoe() {
        currentShoe.name = ""
        currentShoe.size = 0.0
        currentShoe.company = ""
        currentShoe.description = ""
    }

}