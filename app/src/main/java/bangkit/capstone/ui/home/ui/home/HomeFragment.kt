package bangkit.capstone.ui.home.ui.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.adapter.CommitmentAdapter
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.ReadingCommitment
import bangkit.capstone.databinding.FragmentHomeBinding
import bangkit.capstone.dummy.ProvideDummy
import java.util.*
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import bangkit.capstone.core.data.model.Match
import bangkit.capstone.core.data.model.User
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.ui.home.HomeActivity
import bangkit.capstone.ui.home.ui.match_making.MatchMakingFragmentDirections
import bangkit.capstone.util.State
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var commitmentAdapter: CommitmentAdapter

    @Inject
    lateinit var preferenceHelper: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Gson().fromJson(preferenceHelper.getString("user"), User::class.java).name
        binding.fragmenthomeCommitmentSubtitle.text =
            getString(R.string.home_commitment_subtitle, user)

        commitmentAdapter = CommitmentAdapter(preferenceHelper).apply {
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
                        HomeFragmentDirections.actionNavigationHomeToCommitmentRoomFragment(
                            commitmentId1 = owner.id,
                            commitmentId2 = partner.id
                        )
                    )
                }
            })
        }
        binding.fragmenthomeCommitmentrv.adapter = commitmentAdapter
        homeViewModel.commitmentList.observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    commitmentAdapter.setData(it.data)
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
        homeViewModel.getCommitment()
        binding.fragmenthomeCommitmentrv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.fragmenthomeButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToBookCommitmentFragment())
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}