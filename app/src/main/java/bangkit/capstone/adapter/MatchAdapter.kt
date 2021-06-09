package bangkit.capstone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.databinding.MatchRvItemBinding
import bangkit.capstone.util.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction


// TODO ini pake commitment
class MatchAdapter : PagingDataAdapter<ReadingCommitment, MatchAdapter.MatchViewHolder>(diffCallback) {

    lateinit var behaviour: MatchAdapterBehaviour

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ReadingCommitment>() {
            override fun areItemsTheSame(oldItem: ReadingCommitment, newItem: ReadingCommitment): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ReadingCommitment, newItem: ReadingCommitment): Boolean =
                oldItem == newItem
        }
    }

    interface MatchAdapterBehaviour {
        fun onClickListener(match: ReadingCommitment)
    }


    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.matchitemrv_name)
        val bookTitle = itemView.findViewById<TextView>(R.id.matchitemrv_booktitle)
        val bookImage = itemView.findViewById<ImageView>(R.id.matchrvitem_img)
        val deadline = itemView.findViewById<TextView>(R.id.matchitemrv_deadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val layout = MatchRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(layout.root)
    }

    fun getMatchData(position: Int) : ReadingCommitment {
        return getItem(position)!!
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        var match = getItem(position)
        if (match != null) {
            Glide.with(holder.itemView.context)
                .load(match.book.img)
                .apply(
                    RequestOptions().override(93, 140)
                        .transform(CenterCrop())
                )
                .into(holder.bookImage)
            holder.username.text = match.owner.name
            holder.bookTitle.text = match.book.title
            holder.deadline.text = "Deadline on ${Formatter.dateToString(match.end_date)}"
        }
    }
}