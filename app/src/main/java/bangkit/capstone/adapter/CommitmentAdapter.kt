package bangkit.capstone.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.core.data.Book
import bangkit.capstone.core.data.Commitment
import bangkit.capstone.core.data.ReadingCommitment
import bangkit.capstone.core.data.User
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.databinding.CommitmentRvItemBinding
import bangkit.capstone.util.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

class CommitmentAdapter : RecyclerView.Adapter<CommitmentAdapter.CommitmentViewHolder>() {

    private var data: List<ReadingCommitment> = mutableListOf()
    private lateinit var behaviour: CommitmentAdapterBehaviour

    inner class CommitmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<MaterialCardView>(R.id.commitmentrvitem_book1_card)
        val hobby = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_hobby)
        val title = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_title)
        val book1Img = itemView.findViewById<ImageView>(R.id.commitmentrvitem_book1_img)
        val description = itemView.findViewById<TextView>(R.id.commitmentrvitem_description)
        val deadline = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_deadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitmentViewHolder {
        val layout =
            CommitmentRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommitmentViewHolder(layout.root)
    }

    override fun onBindViewHolder(holder: CommitmentViewHolder, position: Int) {
        val commitment = data[position]
        holder.card.setOnClickListener {
            behaviour.onCommitmentClicked(commitment)
        }
        holder.hobby.text = "READING BOOKS"
        holder.title.text = commitment.book?.title
        Glide.with(holder.itemView.context)
            .load(commitment.book?.img)
            .apply(
                RequestOptions().override(90, 143)
                    .transform(CenterCrop())
            )
            .into(holder.book1Img)
        holder.description.text = Html.fromHtml(setDescription(commitment.book, commitment.owner, commitment.partner))
        holder.deadline.text = "Deadline on ${Formatter.getDateTime(commitment.creationDate)}"
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<ReadingCommitment>) {
        data = newData
        notifyDataSetChanged()
    }

    private fun setDescription(book: Book?, user1: User?, user2: User?): String {
        return "<font color=#000000>Continue reading ${book?.title}</font><font color=#465DCB> with ${user2?.username}" + "</font>"
    }

    fun setBehaviour(b: CommitmentAdapterBehaviour) {
        behaviour = b
    }

    interface CommitmentAdapterBehaviour {
        fun onCommitmentClicked(commitment: ReadingCommitment)
    }
}