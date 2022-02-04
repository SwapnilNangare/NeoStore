package com.example.neostore.Activites.ProductDetails.RoomProdDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.neostore.Activites.table.Tabledata

@Database(entities = [Tabledata::class], version = 1)
abstract class FavProdDB : RoomDatabase() {
    abstract fun favoriteDao(): FavProdDao
}