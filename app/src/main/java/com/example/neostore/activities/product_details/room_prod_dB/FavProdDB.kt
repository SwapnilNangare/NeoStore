package com.example.neostore.activities.product_details.room_prod_dB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.neostore.activities.table.TableData

@Database(entities = [TableData::class], version = 1)
abstract class FavProdDB : RoomDatabase() {
    abstract fun favoriteDao(): FavProdDao
}