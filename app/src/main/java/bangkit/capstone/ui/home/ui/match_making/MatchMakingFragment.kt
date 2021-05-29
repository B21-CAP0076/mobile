package bangkit.capstone.ui.home.ui.match_making

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.MatchAdapter
import bangkit.capstone.adapter.MatchAdapter.MatchAdapterBehaviour
import bangkit.capstone.data.Match
import bangkit.capstone.databinding.MatchMakingFragmentBinding

class MatchMakingFragment : Fragment() {

    private val viewModel: MatchMakingViewModel by activityViewModels()
    private var _binding: MatchMakingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchMakingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.matchmakingfragmentRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = MatchAdapter().apply {
            setBehaviour(object : MatchAdapterBehaviour {
                override fun onClickListener(match: Match) {
                    // todo todo
                }
            })
        }

        binding.matchmakingfragmentRv.adapter = adapter
        viewModel.matchList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        viewModel.getMatchList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}