package com.apptesting.test.utils

import android.text.TextUtils
import com.apptesting.test.model.AllData
import java.text.DecimalFormat

object Global {


    var allWallpapers: List<AllData.WallpapersItem>? = null
        get() {
            return if (field != null) {
                field
            } else {
                ArrayList()
            }
        }
    var allCategories: List<AllData.CategoriesItem>? = null
        get() {
            return if (field != null) {
                field
            } else {
                ArrayList()
            }
        }


    fun getFeatured(): List<AllData.WallpapersItem> {
        val featuredWallpapers: MutableList<AllData.WallpapersItem> = ArrayList()

        for (i in allWallpapers!!.indices) {
            if (allWallpapers!![i].isFeatured == 1) {
                featuredWallpapers.add(allWallpapers!![i])
            }
        }

        return featuredWallpapers
    }


    fun getWallpaperByCat(categoriesItem: AllData.CategoriesItem): List<AllData.WallpapersItem> {

        val wallpapers: MutableList<AllData.WallpapersItem> = ArrayList()

        for (i in allWallpapers!!.indices) {


            if (allWallpapers!![i].categoryId == categoriesItem.id) {

                wallpapers.add(allWallpapers!![i])
            }

        }

        return wallpapers
    }


    fun getAllCatWithWallpapers(): List<AllData.CategoriesItem> {

        val category: MutableList<AllData.CategoriesItem> = ArrayList()


        for (j in allCategories!!.indices) {
            var walls = ArrayList<AllData.WallpapersItem>()

            for (i in allWallpapers!!.indices) {


                if (allWallpapers!![i].categoryId == allCategories!![j].id) {
                    walls.add(allWallpapers!![i])

                }
            }

            var obj = allCategories!![j]
            obj.wallpapers = walls
            category.add(obj)
        }

        return category
    }


    fun getSimpleCatWithWallpapers(): List<AllData.CategoriesItem> {

        val category: MutableList<AllData.CategoriesItem> = ArrayList()


        for (j in allCategories!!.indices) {

            if (allCategories!![j].type == 0) {
                var walls = ArrayList<AllData.WallpapersItem>()

                for (i in allWallpapers!!.indices) {


                    if (allWallpapers!![i].categoryId == allCategories!![j].id) {
                        walls.add(allWallpapers!![i])

                    }
                }


                var obj = allCategories!![j]
                obj.wallpapers = walls
                category.add(obj)

            }
        }
        return category
    }

    fun getLiveCatWithWallpapers(): List<AllData.CategoriesItem> {

        val category: MutableList<AllData.CategoriesItem> = ArrayList()


        for (j in allCategories!!.indices) {

            if (allCategories!![j].type == 1) {
                var walls = ArrayList<AllData.WallpapersItem>()

                for (i in allWallpapers!!.indices) {


                    if (allWallpapers!![i].categoryId == allCategories!![j].id) {
                        walls.add(allWallpapers!![i])

                    }
                }


                var obj = allCategories!![j]
                obj.wallpapers = walls
                category.add(obj)

            }
        }
        return category
    }

    fun getFavWallpaper(list: List<Int>): List<AllData.WallpapersItem> {
        val wallpapers: MutableList<AllData.WallpapersItem> = arrayListOf()


        for (i in list.indices) {

            var item: AllData.WallpapersItem? =
                allWallpapers!!.first { wallpapersItem -> wallpapersItem.id == list[i] }
            if (item != null) {
                wallpapers.add(item)

            }

        }

        return wallpapers


    }


    @JvmStatic
    fun prettyCount(number: Number): String {
        return try {
            val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
            val numValue = number.toLong()
            val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
            val base = value / 3
            if (value >= 3 && base < suffix.size) {
                val value2 = numValue / Math.pow(10.0, (base * 3).toDouble())
                if (value2.toString().contains(".")) {
                    val num =
                        value2.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[value2.toString().split("\\.".toRegex())
                            .dropLastWhile { it.isEmpty() }
                            .toTypedArray().size - 1]
                    if (num.contains("0")) {
                        DecimalFormat("#0").format(value2) + suffix[base]
                    } else {
                        DecimalFormat("#0.0").format(value2) + suffix[base]
                    }
                } else {
                    DecimalFormat("#0").format(value2) + suffix[base]
                }
            } else {
                DecimalFormat("#,##0").format(numValue)
            }
        } catch (e: Exception) {
            number.toString()
        }
    }


    fun listOfIntegerToString(list: List<Int>): String? {
        return TextUtils.join(",", list)
    }

    fun convertStringToLis(s: String): List<Int> {


        if (s.isEmpty()) {
            return listOf()
        }
        val stringList = s.split(",")

        // Convert each string in the list to an Int.
        return stringList.map { it.toInt() }


    }

    fun getLockedList(): MutableList<AllData.WallpapersItem> {

        val wallpapers: MutableList<AllData.WallpapersItem> = ArrayList()




        for (j in allWallpapers!!.indices) {


            if (allWallpapers!![j].accessType == 1) {

                wallpapers.add(allWallpapers!![j])
            }

        }

        return wallpapers


    }

    fun getPremiumList(): MutableList<AllData.WallpapersItem> {

        val wallpapers: MutableList<AllData.WallpapersItem> = ArrayList()




        for (j in allWallpapers!!.indices) {


            if (allWallpapers!![j].accessType == 0) {

                wallpapers.add(allWallpapers!![j])
            }

        }

        return wallpapers


    }


}