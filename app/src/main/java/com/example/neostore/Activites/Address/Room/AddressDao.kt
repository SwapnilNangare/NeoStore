package com.example.neostore.Activites.Address.Room

import  androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressDao {
    @Insert
    suspend fun addData(address: Address)

    @Query("select * from address")
    fun getAddressesWithChanges(): LiveData<MutableList<Address>>

    @Query("SELECT EXISTS (SELECT 1 FROM address WHERE id=:id)")
    suspend fun isAddressAdded(id: Int): Int

    @Delete
    fun delete(address: Address)

    @Query("DELETE from address")
    fun delete()

}


