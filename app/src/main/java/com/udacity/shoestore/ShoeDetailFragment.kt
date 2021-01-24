package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
    private val shoeDetailsViewModel: ShoeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        // Kotlinizing - using the scope function with
        with(binding){
            shoeViewModel = shoeDetailsViewModel

            cancelButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_shoeDetailFragment_to_shoeListFragment))

            saveButton.setOnClickListener { view ->
                if (shoeSizeEdit.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Shoe size cannot be empty" ,Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addShoe(shoeDetailsViewModel.shoeLiveData.value!!)
                    findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                }
            }
        }

        return binding.root
    }

}