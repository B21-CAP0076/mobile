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
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.adapter.CommitmentAdapter
import bangkit.capstone.data.Book
import bangkit.capstone.data.Commitment
import bangkit.capstone.data.ReadingCommitment
import bangkit.capstone.databinding.FragmentCommitmentlistBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.ui.home.ui.home.HomeFragmentDirections

class CommitmentListFragment : Fragment() {

    private lateinit var commitmentListViewModel: CommitmentListViewModel
    private var _binding: FragmentCommitmentlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commitmentListViewModel =
            ViewModelProvider(this).get(CommitmentListViewModel::class.java)

        _binding = FragmentCommitmentlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        commitmentListViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        binding.fragmentcommitmentlistRv.layoutManager =
            LinearLayoutManager(requireContext())
        binding.fragmentcommitmentlistRv.adapter = CommitmentAdapter().apply {
            setData(ProvideDummy.commitmentList)
            setBehaviour(object : CommitmentAdapter.CommitmentAdapterBehaviour {
                override fun onCommitmentClicked(commitment: ReadingCommitment) {
                    // todo todo
                }
            })
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}