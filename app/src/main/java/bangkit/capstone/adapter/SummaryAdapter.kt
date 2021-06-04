package bangkit.capstone.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.data.BookSummary
import bangkit.capstone.data.BookSummaryItem
import bangkit.capstone.data.Match
import bangkit.capstone.databinding.SummaryRvItemBinding
import bangkit.capstone.util.Formatter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class SummaryAdapter : RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    private var data: List<BookSummaryItem> = listOf()

    inner class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img : ImageView = itemView.findViewById(R.id.summaryrvitem_bookimg)
        val creator : TextView = itemView.findViewById(R.id.summaryrvitem_creator)
        val summary : TextView = itemView.findViewById(R.id.summaryrvitem_summary)
        val dateCreated: TextView = itemView.findViewById(R.id.summaryrvitem_datecreated)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val layout = SummaryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SummaryViewHolder(layout.root)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        var summary = data[position]
        Glide.with(holder.itemView.context)
            .load(summary.readingCommitment?.book?.img)
            .apply(
                RequestOptions().override(140, 210)
                    .transform(CenterCrop())
            )
            .into(holder.img)
        holder.creator.text = Html.fromHtml("<font color=#465DCB>${summary.readingCommitment?.owner?.username}</font> shared summary on <font color=#465DCB>${summary.readingCommitment?.book?.title}</font>")
        holder.summary.text = summary.summary
        holder.dateCreated.text = "On ${Formatter.getDateTime(summary.creationDate)}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<BookSummaryItem>) {
        data = newData
        notifyDataSetChanged()
    }
}