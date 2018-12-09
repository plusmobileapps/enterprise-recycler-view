package com.plusmobileapps.helloworldrecylcerview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.plusmobileapps.helloworldrecylcerview.R
import java.lang.IllegalStateException
import java.util.*


/**
 * Data model for the base recyclerview, using a sealed class will encapsulate the different
 * possible data sets for each type of view holder in the set
 *
 * @property id important to make sure this is unique per view holder so that
 */
sealed class DataWrapper {
    abstract val id: Int

    /**
     * model for view holder that will have a nested recyclerview for a carousel of items
     *
     * @property items list of carousel items that will be submitted to [CarouselListAdapter]
     */
    data class CarouselData(override val id: Int = UUID.randomUUID().hashCode(), val items: List<CarouselItem>) : DataWrapper()

    /**
     * model for a single card of data
     *
     * @property header the text for the title of the view holder
     * @property imageUrl url to be used with glide to load images
     * @property body smaller text on the card for details
     */
    data class CardData(override val id: Int, val header: String, val imageUrl: String, val body: String) : DataWrapper()
}


/**
 * model for the carousel view holder item that is a nested recyclerview
 */
data class CarouselItem(val id: Int, val header: String, val body: String)


/**
 * This will calculate the difference in the lists being submitted to the [RecyclerViewListAdapter]
 *  and the list currently in the adapter's memory
 */
class RecyclerViewDiffUtil : DiffUtil.ItemCallback<DataWrapper>() {

    /**
     * this function is why it is important to have [DataWrapper.id] be a unique id, which will then
     * help in letting the adapter to know which items were added/deleted/modified
     */
    override fun areItemsTheSame(oldItem: DataWrapper, newItem: DataWrapper): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * this function is then called if the [DataWrapper.id] was the same in order to determine if
     * this item in the list should rebind in the recyclerview
     */
    override fun areContentsTheSame(oldItem: DataWrapper, newItem: DataWrapper): Boolean {
        return oldItem == newItem
    }
}

/**
 * adapter for the base recyclerview which will support various view holder types of
 * cards and nested recycler views
 *
 * @property carouselItemClickListener click listener for the items in the nested recycler view
 * @property cardClickListener click listener for the big cards in the recyclerview
 * @property glide image loading request manager
 */
class RecyclerViewListAdapter(private val carouselItemClickListener: (CarouselItem) -> Unit,
                              private val cardClickListener: (DataWrapper.CardData) -> Unit,
                              private val cardDeleteListener: (DataWrapper.CardData) -> Unit,
                              private val glide: RequestManager)
    : ListAdapter<DataWrapper, BaseViewHolder<*>>(
    RecyclerViewDiffUtil()
) {

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataWrapper.CarouselData -> R.layout.carousel_view_holder
            is DataWrapper.CardData -> R.layout.card_view_holder
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.carousel_view_holder -> CarouselViewHolder(
                view,
                carouselItemClickListener
            )
            R.layout.card_view_holder -> CardViewHolder(
                view,
                glide,
                cardClickListener,
                cardDeleteListener
            )
            else -> throw IllegalStateException("There is no layout file for $viewType in this recycler view")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (item) {
            is DataWrapper.CardData -> (holder as CardViewHolder).bind(item)
            is DataWrapper.CarouselData -> (holder as CarouselViewHolder).bind(item.items)
        }
    }
}

