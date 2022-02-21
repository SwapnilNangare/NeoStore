package com.example.neostore.activities.product_details

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.neostore.R
import com.example.neostore.activities.product_details.room_prod_dB.FavProdDB
import com.example.neostore.activities.table.TableData

class FavouriteProduct : AppCompatActivity() {
    private var rv: RecyclerView? = null
    private var adapter: FavouriteProdAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_activity)
        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar()?.setDisplayShowHomeEnabled(true);
            getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);

            getSupportActionBar()?.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + getString(R.string.MyFavourites) + "</font>")));
        }
        rv = findViewById<View>(R.id.recyleview) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        ProductDetails.favoriteDatabase = Room.databaseBuilder(
            applicationContext,
            FavProdDB::class.java, "myfavdb"
        ).allowMainThreadQueries().build()
        favData
    }

    override fun onResume() {
        super.onResume()
        favData
    }

    private val favData: Unit
        private get() {
            val favoriteLists: List<TableData>? =
                ProductDetails.favoriteDatabase?.favoriteDao()?.favoriteData
            adapter =
                FavouriteProdAdapter(favoriteLists as MutableList<TableData>, applicationContext)

            rv!!.adapter = adapter
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {

        super.onBackPressed()
    }

}