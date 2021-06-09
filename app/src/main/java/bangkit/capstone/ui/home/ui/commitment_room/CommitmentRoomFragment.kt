package bangkit.capstone.ui.home.ui.commitment_room

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.SummaryAdapter
import bangkit.capstone.databinding.CommitmentRoomFragmentBinding
import bangkit.capstone.ui.screening.ChooseGenreFragment
import bangkit.capstone.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
@AndroidEntryPoint
class CommitmentRoomFragment : Fragment() {

    private  val viewModel: CommitmentRoomViewModel by viewModels()
    private var _binding : CommitmentRoomFragmentBinding? = null
    private lateinit var adapter : SummaryAdapter
    val args: CommitmentRoomFragmentArgs by navArgs()
    val binding get() = _binding!!

    private val TAG = "CommitmentRoomFragment"

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommitmentRoomFragmentBinding.inflate(inflater, container, false)
        adapter = SummaryAdapter()
        binding.commitmentroomfragmentSummaryrv.adapter = adapter
        binding.commitmentroomfragmentSummaryrv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getSummaries(args.commitmentId1, args.commitmentId2)
        viewModel.bookSummary.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
        binding.commitmentroomfragmentSendbutton.setOnClickListener {
            viewModel.sendSummary(args.commitmentId1, binding.commitmentroomgragmentEt.text.toString())
            binding.commitmentroomgragmentEt.text = null
        }
        Log.d(TAG, "${args.commitmentId1} - ${args.commitmentId2}")
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
        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                if (adapter.snapshot().isEmpty()) {
                    Toast.makeText(requireContext(), "Loading Summary...", Toast.LENGTH_LONG).show()
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
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}