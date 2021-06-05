package bangkit.capstone.adapter
//
//import android.text.Html
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import bangkit.capstone.R
//import bangkit.capstone.core.data.model.ReadingCommitment
//import bangkit.capstone.databinding.CommitmentRvItemBinding
//import bangkit.capstone.util.Formatter
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.CenterCrop
//import com.bumptech.glide.request.RequestOptions
//import com.google.android.material.card.MaterialCardView
//import java.util.*
//
//class CommitmentAdapter : RecyclerView.Adapter<CommitmentAdapter.CommitmentViewHolder>() {
//
//    private var data: List<Commitment> = mutableListOf()
//    private lateinit var behaviour: CommitmentAdapterBehaviour
//
//    inner class CommitmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val card = itemView.findViewById<MaterialCardView>(R.id.commitmentrvitem_book1_card)
//        val hobby = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_hobby)
//        val title = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_title)
//        val book1Img = itemView.findViewById<ImageView>(R.id.commitmentrvitem_book1_img)
//        val book2Img = itemView.findViewById<ImageView>(R.id.commitmentrvitem_book2_img)
//        val description = itemView.findViewById<TextView>(R.id.commitmentrvitem_description)
//        val deadline = itemView.findViewById<TextView>(R.id.commitmentrvitem_book1_deadline)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitmentViewHolder {
//        val layout =
//            CommitmentRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CommitmentViewHolder(layout.root)
//    }
//
//    override fun onBindViewHolder(holder: CommitmentViewHolder, position: Int) {
//        val commitment = data[position]
//        holder.card.setOnClickListener {
//            behaviour.onCommitmentClicked(commitment)
//        }
//        holder.hobby.text = commitment.hobby.toUpperCase(Locale.ROOT)
//        holder.title.text = commitment.user1.title
//        Glide.with(holder.itemView.context)
//            .load(commitment.user1.bookImg)
//            .apply(
//                RequestOptions().override(43, 70)
//                    .transform(CenterCrop())
//            )
//            .into(holder.book1Img)
//        Glide.with(holder.itemView.context)
//            .load(commitment.user2.bookImg)
//            .apply(
//                RequestOptions().override(43, 70)
//                    .transform(CenterCrop())
//            )
//            .into(holder.book2Img)
//        holder.description.text = Html.fromHtml(setDescription(commitment.user1, commitment.user2))
//        holder.deadline.text = "Deadline on ${Formatter.getDateTime(commitment.deadline)}"
//    }
//
//    override fun getItemCount(): Int = data.size
//
//    fun setData(newData: List<Commitment>) {
//        data = newData
//        notifyDataSetChanged()
//    }
//
//    private fun setDescription(user1: ReadingCommitment, user2: ReadingCommitment): String {
//        return "<font color=#000000>Continue reading ${user1.title}</font><font color=#465DCB> with ${user2.user.name} reading ${user2.title}" + "</font>"
//    }
//
//    fun setBehaviour(b: CommitmentAdapterBehaviour) {
//        behaviour = b
//    }
//
//    interface CommitmentAdapterBehaviour {
//        fun onCommitmentClicked(commitment: Commitment)
//    }
//}