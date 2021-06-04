package bangkit.capstone.ui.home.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.adapter.CommitmentAdapter
import bangkit.capstone.data.Book
import bangkit.capstone.data.ReadingCommitment
import bangkit.capstone.databinding.FragmentHomeBinding
import bangkit.capstone.dummy.ProvideDummy
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val cal = Calendar.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                // TODO send data
                navigateToMatchMaking()
            }
        binding.fragmenthomeCommitmentrv.adapter = CommitmentAdapter().apply {
            setData(ProvideDummy.commitmentList)
            setBehaviour(object : CommitmentAdapter.CommitmentAdapterBehaviour {
                override fun onCommitmentClicked(commitment: ReadingCommitment) {
                    findNavController().navigate(R.id.action_navigation_home_to_commitmentRoomFragment)
                }
            })
        }
        binding.fragmenthomeCommitmentrv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmenthomeBookrv.adapter = BookAdapter().apply {
            setData(ProvideDummy.bookList)
            setBehaviour(object : BookAdapter.BookAdapterBehaviour {
                override fun setOnClickListener(book: Book) {
                    DatePickerDialog(
                        requireContext(),
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }

            })
        }
        binding.fragmenthomeBookrv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.fragmenthomeBookrv.isNestedScrollingEnabled = false
    }

    private fun navigateToMatchMaking() {
        findNavController().navigate(R.id.action_navigation_home_to_matchMakingFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}