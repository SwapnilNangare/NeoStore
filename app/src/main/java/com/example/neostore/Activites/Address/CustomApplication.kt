package com.example.neostore.Activites.Address

import android.app.Application
import androidx.room.Room
import com.example.neostore.Activites.Address.Room.AddressDao
import com.example.neostore.Activites.Address.Room.Database

class CustomApplication : Application() {
    lateinit var database: Database
        private set
    lateinit var addressDao: AddressDao
        private set

    override fun onCreate() {
        super.onCreate()

        this.database = Room.databaseBuilder<Database>(
            applicationContext,
            Database::class.java, "database"
        ).allowMainThreadQueries()
            .build()

        addressDao = database.AddressDao()

    }
}
