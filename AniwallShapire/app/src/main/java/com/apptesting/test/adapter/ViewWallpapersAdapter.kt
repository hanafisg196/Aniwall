package com.apptesting.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.apptesting.test.R
import com.apptesting.test.databinding.ItemViewWallpapersBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.utils.SessionManager

class ViewWallpapersAdapter() : CardSliderAdapter<ViewWallpapersAdapter.ItemHolder>() {

    var mList: List<AllData.WallpapersItem> = ArrayList()
    var favList: MutableList<Int> = arrayListOf()
    var lastSelected = 0
    var currantSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_wallpapers, parent, false)
        return ItemHolder(view)
    }


    override fun bindVH(holder: ItemHolder, position: Int) {
        holder.setModal(position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateData(list: List<AllData.WallpapersItem>) {
        mList = list
        notifyDataSetChanged()
    }


    inner class ItemHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemViewWallpapersBinding
        var sessionManager: SessionManager

        init {
            binding = DataBindingUtil.bind(itemView)!!
            sessionManager = SessionManager(itemView.context)

        }

        fun setModal(position: Int) {
            val item = mList[position]

            binding.model = item
            binding.isFav = false






            for (i: Int in favList) {
                if (i == item.id) {
                    binding.isFav = true
                    break
                }
            }

            binding.icFavNo.setOnClickListener(View.OnClickListener {


                if (!favList.contains(item.id)) {
                    favList.add(item.id!!)
                    sessionManager.saveStringValue(
                        Const.Key.favourites,
                        Global.listOfIntegerToString(favList)
                    )
                    notifyItemChanged(position)
                }

            })

            binding.icFavYes.setOnClickListener(View.OnClickListener {


                if (favList.contains(item.id)) {
                    favList.remove(item.id!!)
                    sessionManager.saveStringValue(
                        Const.Key.favourites,
                        Global.listOfIntegerToString(favList)
                    )
                    notifyItemChanged(position)
                }

            })


        }
    }
}