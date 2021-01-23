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

    fun addShoe(shoe: Shoe) {
        shoesList.add(shoe)
        _shoesListLiveData.value = shoesList
    }


}