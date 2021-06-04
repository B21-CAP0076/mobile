package bangkit.capstone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.data.Match
import bangkit.capstone.databinding.MatchRvItemBinding
import bangkit.capstone.util.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private  var data: List<Match> = mutableListOf()
    private lateinit var behaviour: MatchAdapterBehaviour

    interface MatchAdapterBehaviour {
        fun onClickListener(match: Match)
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

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        var match = data[position]
//        Glide.with(holder.itemView.context)
//            .load(match.readingCommitment.book?.img)
//            .apply(
//                RequestOptions().override(93, 140)
//                    .transform(CenterCrop())
//            )
//            .into(holder.bookImage)
        holder.username.text = match.readingCommitment.owner?.username
        holder.bookTitle.text = match.readingCommitment.book?.title
        holder.deadline.text = "Deadline on ${match.deadline}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setBehaviour(b: MatchAdapterBehaviour) {
        behaviour = b
    }

    fun setData(newData: List<Match>) {
        data = newData
        notifyDataSetChanged()
    }
}