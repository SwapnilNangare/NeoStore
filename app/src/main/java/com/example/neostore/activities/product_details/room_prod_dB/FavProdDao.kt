package com.example.neostore.activities.product_details.room_prod_dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.neostore.activities.table.TableData

@Dao

interface FavProdDao {
    @Insert
    fun addData(favoriteList: TableData?)

    @get:Query("select * from favoritelist")
    val favoriteData: List<TableData>?

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun deleteFavoritesById(favoriteList: TableData?)

    @Query("DELETE from favoriteList")
    fun delete()
}