package bangkit.capstone.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.RatingBar
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import bangkit.capstone.R
//import bangkit.capstone.core.data.model.Book
//import bangkit.capstone.databinding.BookRvItemBinding
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.CenterCrop
//import com.bumptech.glide.request.RequestOptions
//import com.google.android.material.card.MaterialCardView
//
//class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
//
//    private var data: List<Book> = mutableListOf()
//    private lateinit var behaviour: BookAdapterBehaviour
//
//    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image = itemView.findViewById<ImageView>(R.id.book_image)
//        val rating = itemView.findViewById<RatingBar>(R.id.book_rating)
//        val title = itemView.findViewById<TextView>(R.id.book_title)
//        val overview = itemView.findViewById<TextView>(R.id.book_overview)
//        val genre = itemView.findViewById<TextView>(R.id.book_genre)
//        val card = itemView.findViewById<MaterialCardView>(R.id.book_rv_item_card)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
//        val layout = BookRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return BookViewHolder(layout.root)
//    }
//
//    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
//        var book = data[position]
//        Glide.with(holder.itemView.context)
//            .load(book.image)
//            .apply(
//                RequestOptions().override(93, 140)
//                    .transform(CenterCrop())
//            )
//            .into(holder.image)
//        holder.title.text = book.title
//        holder.card.setOnClickListener {
//            behaviour.setOnClickListener(data[position])
//        }
//        holder.genre.text = book.genre
//        holder.overview.text = book.overview
//        holder.rating.rating = book.rating
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    fun setData(newData: List<Book>) {
//        data = newData
//        notifyDataSetChanged()
//    }
//
//    fun setBehaviour(b: BookAdapterBehaviour) {
//        behaviour = b
//    }
//
//    interface BookAdapterBehaviour {
//        fun setOnClickListener(book: Book)
//    }
//}