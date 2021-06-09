package bangkit.capstone.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.databinding.CommitmentRvItemBinding
import bangkit.capstone.util.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import javax.inject.Inject


class CommitmentAdapter(val preferenceHelper: SharedPreferenceHelper) : RecyclerView.Adapter<CommitmentAdapter.CommitmentViewHolder>() {

    private var data: List<Match> = mutableListOf()
    private lateinit var behaviour: CommitmentAdapterBehaviour

    inner class CommitmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<MaterialCardView>(R.id.commitmentrvitem_book_card)
        val hobby = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_hobby)
        val title = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_title)
        val book1Img = itemView.findViewById<ImageView>(R.id.commitmentrvitem_book1_img)
        val user1img = itemView.findViewById<CircleImageView>(R.id.commitmentrvitem_user1_profpic)
        val book2Img = itemView.findViewById<ImageView>(R.id.commitmentrvitem_book2_img)
        val user2img = itemView.findViewById<CircleImageView>(R.id.commitmentrvitem_user2_profpic)
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
        val user = Gson().fromJson<User>(preferenceHelper.getString("user"), User::class.java)
        var owner : ReadingCommitment ? = null
        var partner : ReadingCommitment ? = null
        if (user.id == commitment.commitment_1.owner.id) {
            owner = commitment.commitment_1
            partner = commitment.commitment_2
        } else {
            owner = commitment.commitment_2
            partner = commitment.commitment_1
        }
        holder.title.text = owner.book.title
        Glide.with(holder.itemView.context)
            .load(owner.book.img)
            .apply(
                RequestOptions().override(90, 143)
                    .transform(CenterCrop())
            )
            .into(holder.book1Img)
        Glide.with(holder.itemView.context)
            .load(partner.book.img)
            .apply(
                RequestOptions().override(90, 143)
                    .transform(CenterCrop())
            )
            .into(holder.book2Img)
        Glide.with(holder.itemView.context)
            .load(owner.owner.picture)
            .apply(
                RequestOptions().override(30, 30)
                    .transform(CenterCrop())
            )
            .into(holder.book1Img)
        Glide.with(holder.itemView.context)
            .load(partner.owner.picture)
            .apply(
                RequestOptions().override(30, 30)
                    .transform(CenterCrop())
            )
            .into(holder.book1Img)
        holder.description.text = Html.fromHtml(setDescription(owner, partner))
        holder.deadline.text = "Deadline on ${Formatter.dateToString(owner.end_date)}"
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<Match>) {
        data = newData
        notifyDataSetChanged()
    }

    private fun setDescription(owner: ReadingCommitment, partner: ReadingCommitment): String {
        return "<font color=#000000>Continue reading ${owner.book.title} by ${owner.book.authors.map { it -> it.name }.joinToString(",")} with</font><font color=#465DCB>${partner.owner.name} reading ${partner.book.title} by ${partner.book.authors.map { it -> it.name }.joinToString(",")}</font>"
    }

    fun setBehaviour(b: CommitmentAdapterBehaviour) {
        behaviour = b
    }

    interface CommitmentAdapterBehaviour {
        fun onCommitmentClicked(commitment: Match)
    }
}