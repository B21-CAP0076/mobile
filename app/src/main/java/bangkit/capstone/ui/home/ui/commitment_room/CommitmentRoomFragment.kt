package bangkit.capstone.ui.home.ui.commitment_room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.adapter.SummaryAdapter
import bangkit.capstone.databinding.CommitmentRoomFragmentBinding

class CommitmentRoomFragment : Fragment() {

    private  val viewModel: CommitmentRoomViewModel by viewModels()
    private var _binding : CommitmentRoomFragmentBinding? = null
    private lateinit var adapter : SummaryAdapter
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommitmentRoomFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = SummaryAdapter()
        binding.commitmentroomfragmentSummaryrv.adapter = adapter
        binding.commitmentroomfragmentSummaryrv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.bookSummary.observe(viewLifecycleOwner, Observer {
            activity?.actionBar?.title = it.bookSummary.first().readingCommitment?.partner?.username
            adapter.setData(it.bookSummary)
        })
        viewModel.getCommitment("demo")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}