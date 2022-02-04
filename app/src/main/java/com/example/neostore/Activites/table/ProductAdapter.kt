package com.example.neostore.Activites.table

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.Activites.ProductDetails.Productdetails
import com.example.neostore.R

class ProductAdapter(val context: Context, var tablelist: List<Tabledata>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>(), Filterable {

    var filtered: MutableList<Tabledata> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.table_recycle_item, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return tablelist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tableItem = tablelist.get(position)

        holder.productname.text = tableItem.name
        holder.producername.text = tableItem.producer
        holder.productprice.text = tableItem.cost.toString()
        Glide.with(holder.itemView!!.context).load(tableItem.product_images).into(holder.image)
        holder.rate.setRating(tableItem.rating.toFloat());


        holder.itemView!!.setOnClickListener {
            val context: Context = holder.itemView.context
            val i = Intent(context, Productdetails::class.java
            )
            i.putExtra("id", tableItem.id)
            i.putExtra("product_category_id", tableItem.product_category_id)
            i.putExtra("name", tablelist.get(position).name)
            i.putExtra("producer", tablelist.get(position).producer)
            i.putExtra("description", tablelist.get(position).description)

            i.putExtra("cost", tablelist.get(position).cost)
            i.putExtra("rating", tablelist.get(position).rating)
            i.putExtra("view_count", tablelist.get(position).view_count)
            i.putExtra("created", tablelist.get(position).created)
            i.putExtra("modified", tablelist.get(position).modified)
            i.putExtra("image", tableItem.product_images)
            i.putExtra("current_img", position)
            context.startActivity(i)
        }


    }

    fun setMovieListItems(movieList: MutableList<Tabledata>) {
        filtered = movieList.toMutableList() // makes a copy
        tablelist = movieList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val productname: TextView = itemView!!.findViewById(R.id.title)
        val producername: TextView = itemView!!.findViewById(R.id.title1)
        val productprice: TextView = itemView!!.findViewById(R.id.title2)
        val rate: RatingBar = itemView!!.findViewById(R.id.ratingbar)
        val image: ImageView = itemView!!.findViewById(R.id.image)
        //   val  fav_btn:ImageView=itemView!!.findViewById(R.id.fav_btn);

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val queryString = charSequence.toString()

                val filterResults = FilterResults()
                filterResults.values =
                    if (queryString.isEmpty()) {
                        filtered
                    } else {
                        filtered.filter {
                            it.name.contains(queryString, ignoreCase = true) || it.name.contains(
                                charSequence)
                        }
                    }
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                tablelist = filterResults.values as List<Tabledata>
                notifyDataSetChanged()
            }
        }
    }
}