package com.apptesting.test.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.apptesting.test.R
import com.apptesting.test.activity.ViewWallpapersActivity
import com.apptesting.test.databinding.ItemHomeImageByCatBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.utils.SessionManager

class HomeImagesByCatAdapter() : RecyclerView.Adapter<HomeImagesByCatAdapter.ItemHolder>() {

    var mList: List<AllData.WallpapersItem> = ArrayList()
    var favList: MutableList<Int> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_image_by_cat, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(
        holder: ItemHolder, position: Int
    ) {
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
        var binding: ItemHomeImageByCatBinding
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


            binding.root.setOnClickListener(View.OnClickListener {

                var intent = Intent(itemView.context, ViewWallpapersActivity::class.java)

                intent.putExtra(Const.Key.dataList, Gson().toJson(mList))
                intent.putExtra(Const.Key.position, position)
                itemView.context.startActivity(intent)

            })


        }
    }
}