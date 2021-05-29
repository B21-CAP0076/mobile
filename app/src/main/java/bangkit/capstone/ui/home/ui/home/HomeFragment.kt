package bangkit.capstone.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.adapter.CommitmentAdapter
import bangkit.capstone.data.Book
import bangkit.capstone.data.Commitment
import bangkit.capstone.databinding.FragmentHomeBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.ui.home.ui.detail_book.DetailBookFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

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
        binding.fragmenthomeCommitmentrv.adapter = CommitmentAdapter().apply {
            setData(ProvideDummy.commitmentList)
            setBehaviour(object : CommitmentAdapter.CommitmentAdapterBehaviour {
                override fun onCommitmentClicked(commitment: Commitment) {
                    // todo todo
                }
            })
        }
        binding.fragmenthomeCommitmentrv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmenthomeBookrv.adapter = BookAdapter().apply {
            setData(ProvideDummy.bookList)
            setBehaviour(object : BookAdapter.BookAdapterBehaviour {
                override fun setOnClickListener(book: Book) {
                    val action = HomeFragmentDirections.actionNavigationHomeToDetailBookFragment(book.id)
                    findNavController().navigate(action)
                }

            })
        }
        binding.fragmenthomeBookrv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}