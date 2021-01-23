package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val shoesList = mutableListOf<Shoe>()

    private val _shoesListLiveData = MutableLiveData<List<Shoe>>()
    val shoesListLiveData: LiveData<List<Shoe>>
        get() = _shoesListLiveData

    private val _eventShoeAdded = MutableLiveData<Boolean>()
    val eventShoeAdded: LiveData<Boolean>
        get() = _eventShoeAdded

    fun addShoe(shoe: Shoe) {
        shoesList.add(shoe)
        _shoesListLiveData.value = shoesList
        _eventShoeAdded.value = true
    }

    fun viewAdded() {
        _eventShoeAdded.value = false
    }

    init {
        _eventShoeAdded.value = false
    }


}