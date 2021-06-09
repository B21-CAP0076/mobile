package bangkit.capstone.ui.home.ui.match_making

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import bangkit.capstone.adapter.MatchAdapter
import bangkit.capstone.adapter.MatchAdapter.MatchAdapterBehaviour
import bangkit.capstone.core.data.choice.MatchAction
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.databinding.MatchMakingFragmentBinding
import bangkit.capstone.ui.home.HomeActivity
import bangkit.capstone.util.State
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchMakingFragment : Fragment() {

    private val viewModel: MatchMakingViewModel by viewModels()
    private var _binding: MatchMakingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MatchAdapter
    val args: MatchMakingFragmentArgs by navArgs()
    private var currentIndex : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchMakingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MatchAdapter().apply {
            behaviour = object : MatchAdapterBehaviour {
                override fun onClickListener(match: ReadingCommitment) {

                }
            }
        }

        binding.matchmakingfragmentRv.adapter = adapter
        val layoutManager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {
                val id1 = args.commitmentId
                val id2 = adapter.getMatchData(currentIndex).id
                if (direction == Direction.Left) {
                    viewModel.match(id1,id2, MatchAction.SWIPE_LEFT)
                } else if (direction == Direction.Right) {
                    viewModel.match(id1,id2, MatchAction.SWIPE_RIGHT)
                }
            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {
                currentIndex = position
            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }
        })

        binding.matchmakingfragmentRv.layoutManager = layoutManager
        viewModel.matchList.observe(viewLifecycleOwner, Observer {
           lifecycleScope.launch {
               adapter.submitData(it)
           }
        })

        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                if (adapter.snapshot().isEmpty()) {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }

            }
            val error = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                else -> null
            }
            error?.let {
                if (adapter.snapshot().isEmpty()) {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }

            }
        }
        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
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
        currentIndex = 0
    }

}