package com.apptesting.test.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.apptesting.test.R
import com.apptesting.test.adapter.HomeCatAdapter
import com.apptesting.test.databinding.FragmentHomeBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.viewmodel.HomeViewModel
import com.apptesting.test.viewmodel.MainViewModel
import kotlin.math.abs


class HomeFragment : BaseFragment(), Runnable {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var handler: Handler
    var reversed = false
    var scrollingPos = 0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        initView()
        initListener()

        binding.model = viewModel




        return binding.root
    }


    var scrolledByUser = false
    private fun initListener() {


        binding.rvFeatured.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    scrolledByUser = true;
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (scrolledByUser) {
                        handler.removeCallbacks(this@HomeFragment)
                        val itemPoition =
                            (binding.rvFeatured.layoutManager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()

                        scrollingPos = itemPoition
                        reversed = scrollingPos + 1 > viewModel.featureImagesAdapter.itemCount - 1

                        scrollToPos(true)
                    }
                    scrolledByUser = false

                }


            }
        })


        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

            mainViewModel.cordinated_expandeed.value =
                abs(verticalOffset) - appBarLayout.totalScrollRange <= -50
        }

        viewModel.homeCatAdapter.onItemClick = object : HomeCatAdapter.OnItemClick {
            override fun onClick(item: AllData.CategoriesItem) {
                if (item.id == 0) {
                    viewModel.homeImagesByCatAdapter.updateData(Global.allWallpapers!!)
                } else {

                    setWallpaperByCat(item)
                }
                binding.nestedScrollView.scrollTo(0, 0)

            }
        }

    }

    private fun setWallpaperByCat(item: AllData.CategoriesItem) {

        var list = Global.getWallpaperByCat(item)
        viewModel.homeImagesByCatAdapter.updateData(list)

    }

    private fun initView() {

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvFeatured)

        handler = Handler(Looper.getMainLooper())


        viewModel.featureImagesAdapter.updateData(Global.getFeatured())
        val dotlist: MutableList<String> = ArrayList()
        for (i in viewModel.featureImagesAdapter.mList.indices) {
            dotlist.add(" ")
        }
        viewModel.featureDotsAdapter.updateData(dotlist)


        viewModel.homeCatAdapter.updateData(
            Global.allCategories!!.toMutableList(),
            requireActivity().getString(R.string.all)
        )


        viewModel.homeImagesByCatAdapter.updateData(Global.allWallpapers!!)

        handler.postDelayed(Runnable {
            binding.rvDots.minimumWidth = binding.rvDots.width
        }, 2000)


    }

    override fun run() {
        if (reversed) {
            if (scrollingPos - 1 < 0) {
                Log.i("TAG", "run: 1")
                scrollingPos += 1
                reversed = false
            } else {
                Log.i("TAG", "run: 2")
                scrollingPos -= 1
            }
        } else {
            if (scrollingPos + 1 > viewModel.featureImagesAdapter.itemCount - 1) {
                scrollingPos -= 1
                reversed = true
                Log.i("TAG", "run: 3")
            } else {
                scrollingPos += 1
                reversed = false
                Log.i("TAG", "run: 4")
            }
        }

        scrollToPos(false)


    }

    private fun scrollToPos(fromUser: Boolean) {

        Log.i("TAG", "run: scrolling pos $scrollingPos")
        Log.i("TAG", "run: scrolling pos $fromUser")
        if (!fromUser) {
            binding.rvFeatured.smoothScrollToPosition(scrollingPos)

        }
        viewModel.featureDotsAdapter.scrollToPos(scrollingPos)
        binding.rvDots.scrollToPosition(scrollingPos)
        handler.postDelayed(this, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(this)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(this)

    }

    override fun onResume() {
        super.onResume()

        refreshFavList()
        if (viewModel.featureImagesAdapter.itemCount !== 0) {
            handler.postDelayed(this, 3000)
        }


    }


    private fun refreshFavList() {

        viewModel.homeImagesByCatAdapter.favList =
            Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                .toMutableList()
        viewModel.homeImagesByCatAdapter.notifyDataSetChanged()

        Log.i(" onnnn set home", ": ${viewModel.homeImagesByCatAdapter.favList}")


    }


}