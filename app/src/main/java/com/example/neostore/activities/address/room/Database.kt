package com.example.neostore.activities.address.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Address::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun AddressDao(): AddressDao



}

