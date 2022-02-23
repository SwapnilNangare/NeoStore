package com.example.neostore.activities.home_screen_ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.app.NavUtils
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.activities.table.ProductAdapter
import com.example.neostore.activities.table.ProductViewModel
import com.example.neostore.activities.table.TableResponse
import com.example.neostore.activities.table.TableData
import com.example.neostore.base.BaseClassActivity
import com.example.neostore.R
import kotlinx.android.synthetic.main.table_activity.*


class Tables : BaseClassActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: ProductAdapter
     var tablelist : MutableList<TableData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_activity)
        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setScreenTitle("Tables")

        recyclerView = findViewById(R.id.recyleview)

        val model = ViewModelProvider(this)[ProductViewModel::class.java]

        model.tables?.observe(this,object :Observer<TableResponse>{
            override fun onChanged(t: TableResponse?) {

                recyclerAdapter = ProductAdapter(applicationContext, tablelist)
                recyleview.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.addItemDecoration(
                    DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL
                    )
                )
                recyclerAdapter.setMovieListItems(t?.data as MutableList<TableData>)

                recyleview.adapter = recyclerAdapter
            }

        })

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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val search: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = MenuItemCompat.getActionView(search) as SearchView
        val searchEditText =
            searchView.findViewById<View>(R.id.search_src_text) as EditText
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))
        val searchplate = searchView.findViewById(R.id.search_plate) as View
        searchplate.background.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        val searchClose: ImageView =
            searchView.findViewById(R.id.search_close_btn)
        searchClose.setImageResource(R.drawable.remove_button)
      //  searchClose.setPadding(0,0,0,-40)
        search(searchView)
        return true
    }
  override fun onBackPressed() {
      super.onBackPressed()
  }
    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                recyclerAdapter.filter.filter(newText)
                return true
            }
        })
    }
}