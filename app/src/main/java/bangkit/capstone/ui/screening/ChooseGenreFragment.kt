package bangkit.capstone.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.CardAdapter
import bangkit.capstone.core.data.model.Genre
import bangkit.capstone.databinding.FragmentChooseGenreBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.Constants
import bangkit.capstone.util.State
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class ChooseGenreFragment : Fragment() {

    private var _binding : FragmentChooseGenreBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChooseGenreViewModel by viewModels()
    private lateinit var adapter: CardAdapter<Genre>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseGenreBinding.inflate(layoutInflater)
        return binding.root
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CardAdapter<Genre>().apply {
            setAdapterBehaviour(object : CardAdapter.AdapterBehaviour<Genre> {
                override fun onItemBind(data: Genre, holder: CardAdapter<Genre>.CardViewHolder) {
                    Glide.with(holder.itemView.context)
                        .load(AppCompatResources.getDrawable(requireContext(), R.drawable.books))
                        .apply(
                            RequestOptions().override(200, 200)
                                .transform(CenterCrop())
                        )
                        .into(holder.image)
                    holder.title.text = data.name
                    holder.card.setOnClickListener {
                        holder.card.isChecked = !holder.card.isChecked
                        viewModel.setGenre(data, holder.card.isChecked)
                    }
                }
            })
        }
        binding.genreRv.cardRv.adapter = adapter
            binding.genreRv.cardRv.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.genreList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    adapter.setData(it.data)
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getGenre()
        binding.fragmentchoosegenreButton.setOnClickListener {
            viewModel.submit()
        }
        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    parentFragmentManager.commit {
                        replace(R.id.container, ChooseBookFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}