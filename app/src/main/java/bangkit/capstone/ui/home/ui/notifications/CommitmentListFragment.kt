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
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.databinding.FragmentCommitmentlistBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.ui.home.ui.home.HomeFragmentDirections
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommitmentListFragment : Fragment() {

    private val commitmentListViewModel: CommitmentListViewModel by navGraphViewModels(R.id.mobile_navigation)
    private var _binding: FragmentCommitmentlistBinding? = null
    private lateinit var adapter: CommitmentAdapter

    private val binding get() = _binding!!

    @Inject
    lateinit var preferenceHelper: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommitmentlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fragmentcommitmentlistRv.layoutManager =
            LinearLayoutManager(requireContext())
       adapter = CommitmentAdapter(preferenceHelper).apply {
            setBehaviour(object : CommitmentAdapter.CommitmentAdapterBehaviour {
                override fun onCommitmentClicked(commitment: Match) {
                    val user = Gson().fromJson<User>(preferenceHelper.getString("user"), User::class.java)
                    var owner : ReadingCommitment ? = null
                    var partner : ReadingCommitment ? = null
                    if (user.id == commitment.commitment_1.owner.id) {
                        owner = commitment.commitment_1
                        partner = commitment.commitment_2
                    } else {
                        owner = commitment.commitment_2
                        partner = commitment.commitment_1
                    }
                    findNavController().navigate(
                        CommitmentListFragmentDirections.actionNavigationNotificationsToCommitmentRoomFragment(
                            commitmentId1 = owner.id,
                            commitmentId2 = partner.id
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