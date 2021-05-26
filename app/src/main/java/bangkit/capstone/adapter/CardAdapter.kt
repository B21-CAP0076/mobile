package bangkit.capstone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.capstone.R
import bangkit.capstone.databinding.CardRvItemBinding
import com.google.android.material.card.MaterialCardView

class CardAdapter<T> : RecyclerView.Adapter<CardAdapter<T>.CardViewHolder>() {

    private lateinit var adapterBehaviour: AdapterBehaviour<T>
    private var data: List<T> = mutableListOf<T>()

    fun setAdapterBehaviour(behaviour: AdapterBehaviour<T>) {
        adapterBehaviour = behaviour
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.card_image)
        val title = itemView.findViewById<TextView>(R.id.card_title)
        val card = itemView.findViewById<MaterialCardView>(R.id.card_rv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layout =
            CardRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(layout.root)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        adapterBehaviour.onItemBind(data[position], holder)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<T>) {
        data = newData
        notifyDataSetChanged()
    }

    interface AdapterBehaviour<T> {
        fun onItemBind(data: T, holder: CardAdapter<T>.CardViewHolder)
    }
}