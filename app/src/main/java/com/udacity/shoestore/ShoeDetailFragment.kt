package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import com.udacity.shoestore.viewmodel.ShoeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var shoeViewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)

        // linking the shoeViewModel to the binding
        shoeViewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
        binding.shoeViewModel = shoeViewModel

        binding.cancelButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeDetailFragment_to_shoeListFragment))

        binding.saveButton.setOnClickListener { view ->
            if (binding.shoeSizeEdit.text.isEmpty()) {
                Toast.makeText(this.context, "Shoe size cannot be empty" ,Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addShoe(shoeViewModel.shoeLiveData.value!!)
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
            }
        }

        return binding.root
    }

}