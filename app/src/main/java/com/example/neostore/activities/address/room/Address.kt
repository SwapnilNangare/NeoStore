package com.example.neostore.activities.address.room

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity(tableName = "address")
class Address {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "address")
    var address: String? = null
}