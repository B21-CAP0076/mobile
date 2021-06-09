package bangkit.capstone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.databinding.BookRvItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import java.util.*

class BookAdapter : PagingDataAdapter<Book, BookAdapter.BookViewHolder>(diffCallback) {

    private lateinit var behaviour: BookAdapterBehaviour

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.book_image)
        val title = itemView.findViewById<TextView>(R.id.book_title)
        val genre = itemView.findViewById<TextView>(R.id.book_genre)
        val card = itemView.findViewById<MaterialCardView>(R.id.book_rv_item_card)
        val author = itemView.findViewById<TextView>(R.id.book_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layout = BookRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(layout.root)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        var book = getItem(position)
        if (book != null) {
            Glide.with(holder.itemView.context)
                .load(book.img)
                .apply(
                    RequestOptions().override(186, 250)
                        .transform(CenterCrop())
                ).into(holder.image)
            holder.title.text = book.title
            holder.card.setOnClickListener {
                holder.card.isChecked = !holder.card.isChecked
                behaviour.setOnClickListener(book)
            }
            holder.genre.text = book.genres.map { it -> it.name }.toList().joinToString(",")
                .uppercase(Locale.ROOT)
            holder.author.text = book.authors.map { it -> it.name }.toList().joinToString(",")   
        }
    }


    fun setBehaviour(b: BookAdapterBehaviour) {
        behaviour = b
    }

    interface BookAdapterBehaviour {
        fun setOnClickListener(book: Book)
    }
}