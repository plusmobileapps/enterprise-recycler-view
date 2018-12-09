package com.plusmobileapps.helloworldrecylcerview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plusmobileapps.helloworldrecylcerview.R

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: T)

}

/**
 * view holder for big cards that have an image
 * @see R.layout.card_view_holder
 *
 * @property glide image loading
 * @property clickListener view holder click listener for entire view holder ever being clicked
 */
class CardViewHolder(itemView: View,
                     private val glide: RequestManager,
                     private val clickListener: (DataWrapper.CardData) -> Unit,
                     private val deleteListener: (DataWrapper.CardData) -> Unit) : BaseViewHolder<DataWrapper.CardData>(itemView) {

    private val header = itemView.findViewById<TextView>(R.id.header)
    private val body = itemView.findViewById<TextView>(R.id.body)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val deleteButton = itemView.findViewById<ImageView>(R.id.delete_button)


    override fun bind(data: DataWrapper.CardData) {
        header.text = data.header
        body.text = data.body
        glide.load(data.imageUrl).into(image)
        itemView.setOnClickListener { clickListener(data) }
        deleteButton.setOnClickListener { deleteListener(data) }
    }

}

/**
 * View holder that houses the nested recyclerview
 *
 * @param clickListener the click listener for the individual items in its recyclerview
 */
class CarouselViewHolder(itemView: View, clickListener: (CarouselItem) -> Unit) : BaseViewHolder<List<CarouselItem>>(itemView) {

    private val recyclerView = itemView.findViewById<RecyclerView>(R.id.carousel_recyclerview)
    private val adapter = CarouselListAdapter(clickListener)

    init {
        recyclerView.adapter = adapter
    }

    override fun bind(data: List<CarouselItem>) = adapter.submitList(data)

}

/**
 * Individual view holder for the nested recyclerview
 *
 * @property clickListener view holder click listener
 */
class CarouselItemViewHolder(itemView: View, private val clickListener: (CarouselItem) -> Unit) : BaseViewHolder<CarouselItem>(itemView) {

    private val header = itemView.findViewById<TextView>(R.id.header)
    private val body = itemView.findViewById<TextView>(R.id.body)

    override fun bind(data: CarouselItem) {
        header.text = data.header
        body.text = data.body
        itemView.setOnClickListener { clickListener(data) }
    }

}

/**
 * Will calculate the difference in list being submitted to [CarouselListAdapter] and the list
 * currently in the adapter's memory
 */
class CarouselDiffUtil : DiffUtil.ItemCallback<CarouselItem>() {

    override fun areItemsTheSame(oldItem: CarouselItem, newItem: CarouselItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarouselItem, newItem: CarouselItem): Boolean {
        return oldItem == newItem
    }

}

/**
 * Recyclerview adapter for the nested recyclerview within [RecyclerViewListAdapter]
 *
 * @property clickListener listener for [CarouselItemViewHolder] being clicked
 */
class CarouselListAdapter(private val clickListener: (CarouselItem) -> Unit) : ListAdapter<CarouselItem, CarouselItemViewHolder>(
    CarouselDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item_view_holder, parent, false)
        return CarouselItemViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holderItem: CarouselItemViewHolder, position: Int) = holderItem.bind(getItem(position))

}