package bangkit.capstone.ui.home.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.adapter.CommitmentAdapter
import bangkit.capstone.data.Book
import bangkit.capstone.data.Commitment
import bangkit.capstone.data.ReadingCommitment
import bangkit.capstone.databinding.FragmentCommitmentlistBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.ui.home.ui.home.HomeFragmentDirections

class CommitmentListFragment : Fragment() {

    private val commitmentListViewModel: CommitmentListViewModel by navGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentCommitmentlistBinding? = null
    private lateinit var adapter: CommitmentAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommitmentlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fragmentcommitmentlistRv.layoutManager =
            LinearLayoutManager(requireContext())
       adapter = CommitmentAdapter().apply {
            setData(ProvideDummy.commitmentList)
            setBehaviour(object : CommitmentAdapter.CommitmentAdapterBehaviour {
                override fun onCommitmentClicked(commitment: ReadingCommitment) {
                    findNavController().navigate(
                        CommitmentListFragmentDirections.actionNavigationNotificationsToCommitmentRoomFragment(
                            commitmentId = commitment.id!!
                        )
                    )
                }
            })
        }
        binding.fragmentcommitmentlistRv.adapter = adapter
        commitmentListViewModel.commitmentList.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
        commitmentListViewModel.getCommitment()
            return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}