package bangkit.capstone.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.CardAdapter
import bangkit.capstone.core.data.model.Genre
import bangkit.capstone.databinding.FragmentChooseGenreBinding
import bangkit.capstone.core.data.dummy.ProvideDummy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class ChooseGenreFragment : Fragment() {

    private var _binding : FragmentChooseGenreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseGenreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genreRv.cardRv.adapter = CardAdapter<Genre>().apply {
            setAdapterBehaviour(object : CardAdapter.AdapterBehaviour<Genre> {
                override fun onItemBind(data: Genre, holder: CardAdapter<Genre>.CardViewHolder) {
                    Glide.with(holder.itemView.context)
                        .load(data.image)
                        .apply(
                            RequestOptions().override(200, 200)
                                .transform(CenterCrop())
                        )
                        .into(holder.image)
                    holder.title.text = data.title
                    holder.card.setOnCheckedChangeListener { card, isChecked ->

                    }
                }
            })
            setData(ProvideDummy.genreList)
        }
        binding.genreRv.cardRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.fragmentchoosegenreButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.container, ChooseBookFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}