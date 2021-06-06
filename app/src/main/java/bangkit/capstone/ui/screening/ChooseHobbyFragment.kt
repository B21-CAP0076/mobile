package bangkit.capstone.ui.screening

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import bangkit.capstone.R
import bangkit.capstone.data.Hobby
import bangkit.capstone.adapter.CardAdapter
import bangkit.capstone.databinding.FragmentChooseHobbyBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.util.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChooseHobbyFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentChooseHobbyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChooseHobbyViewModel by viewModels()
    private lateinit var adapter: CardAdapter<Hobby>

    companion object {
        private const val TAG = "ChooseHobbyFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseHobbyBinding.inflate(layoutInflater)
        adapter = CardAdapter<Hobby>().apply {
            setAdapterBehaviour(object : CardAdapter.AdapterBehaviour<Hobby> {
                override fun onItemBind(data: Hobby, holder: CardAdapter<Hobby>.CardViewHolder) {
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
                        viewModel.setHobby(data, holder.card.isChecked)
                    }
                }
            })
        }
        binding.hobbyRv.cardRv.adapter = adapter
        binding.hobbyRv.cardRv.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.hobbyList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        viewModel.getHobbyList()
        binding.fragmentchoosehobbyButton.setOnClickListener {
            viewModel.submit()
            parentFragmentManager.commit {
                replace(R.id.container, ChooseGenreFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}