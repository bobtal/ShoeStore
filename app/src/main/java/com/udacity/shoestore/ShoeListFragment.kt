package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeLayoutBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

        setHasOptionsMenu(true)

        if (viewModel.shoesListLiveData.value == null) {
            val noShoesText = TextView(this.context)
            noShoesText.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            noShoesText.text = getString(R.string.no_shoes)
            binding.shoeList.addView(noShoesText)
        }

        viewModel.shoesListLiveData.observe(viewLifecycleOwner, Observer { newShoeList ->
            updateShoeList(newShoeList)
        })

        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment))

        return binding.root
    }

    private fun updateShoeList(newShoeList: List<Shoe>) {
        for (shoe: Shoe in newShoeList) {
            addView(shoe)
        }
    }

    private fun addView(shoe: Shoe) {
        // Using DataBinding to inflate each TextView with shoe information instead
        // of the commented code below, which also works. But this is way more awesome! :)
        // Thanks to the Anonymous Udacity reviewer for pointing this possibility!
        val shoeLayoutBinding : ShoeLayoutBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.shoe_layout, null, false)
        shoeLayoutBinding.shoe = shoe
        binding.shoeList.addView(shoeLayoutBinding.root)

//        val newShoeTextView = TextView(this.context)
//        newShoeTextView.layoutParams =
//            LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//        newShoeTextView.textSize = 32.0f
//        newShoeTextView.text = getString(R.string.shoe_listing_format, shoe.name, shoe.company, shoe.size, shoe.description)
//        binding.shoeList.addView(newShoeTextView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loginFragment ->
                return NavigationUI.onNavDestinationSelected(
                    item,
                    requireView().findNavController()
                )
        }
        return super.onOptionsItemSelected(item)
    }
}