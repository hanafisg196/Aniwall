package com.apptesting.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apptesting.test.R
import com.apptesting.test.adapter.CategoryAdapter
import com.apptesting.test.databinding.FragmentCategoryBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Global
import com.apptesting.test.viewmodel.CategoryViewModel

class CategoryFragment : BaseFragment() {

    lateinit var binding: FragmentCategoryBinding
    lateinit var viewModel: CategoryViewModel
    lateinit var allCat: List<AllData.CategoriesItem>
    lateinit var liveCat: List<AllData.CategoriesItem>
    lateinit var simpleCat: List<AllData.CategoriesItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        viewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        viewModel.categoryAdapter = CategoryAdapter(requireActivity())


        initView()
        initListeners()
        initObserver()


        binding.model = viewModel




        return binding.root
    }

    private fun initObserver() {
        viewModel.selected.observe(viewLifecycleOwner, Observer {
            when (it) {
                0 -> viewModel.categoryAdapter.updateData(allCat)
                1 -> viewModel.categoryAdapter.updateData(simpleCat)
                2 -> viewModel.categoryAdapter.updateData(liveCat)
            }
            binding.nestedScrollView.scrollTo(0, 0)


            binding.model = viewModel
        })
    }

    private fun initListeners() {


    }

    private fun initView() {


        allCat = Global.getAllCatWithWallpapers()
        simpleCat = Global.getSimpleCatWithWallpapers()
        liveCat = Global.getLiveCatWithWallpapers()


    }


}