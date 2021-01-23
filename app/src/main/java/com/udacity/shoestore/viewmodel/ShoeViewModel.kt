package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeLiveData = MutableLiveData<Shoe>()
    val shoeLiveData: LiveData<Shoe>
        get() = _shoeLiveData

    // initialize an "empty" Shoe object as placeholder for the layout
    init {
        _shoeLiveData.value = Shoe("", 0.0, "", "")
    }

}