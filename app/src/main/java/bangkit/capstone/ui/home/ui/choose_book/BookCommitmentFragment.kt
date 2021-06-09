package bangkit.capstone.ui.home.ui.choose_book

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.databinding.BookCommitmentFragmentBinding
import bangkit.capstone.ui.home.ui.home.HomeFragmentDirections
import bangkit.capstone.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class BookCommitmentFragment : Fragment() {

    private val viewModel: BookCommitmentViewModel by viewModels()
    private var _binding: BookCommitmentFragmentBinding? = null
    private val binding get() = _binding!!
    private val cal = Calendar.getInstance()
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BookCommitmentFragmentBinding.inflate(inflater, container, false)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.makeCommitment(cal.time)
            }
        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    navigateToMatchMaking(it.data)
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
        adapter = BookAdapter().apply {
            setBehaviour(object: BookAdapter.BookAdapterBehaviour {
                override fun setOnClickListener(book: Book) {
                    viewModel.setBook(book)
                    DatePickerDialog(
                        requireContext(),
                        dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            })
        }
        binding.fragmentbookcommitmentRv.adapter = adapter
        binding.fragmentbookcommitmentRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.fragmentbookcommitmentSv.doAfterTextChanged {
            lifecycleScope.launch {
                viewModel.query.value = it.toString()
            }
        }
        viewModel.books.observe(viewLifecycleOwner, {
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
        return binding.root
    }


    private fun navigateToMatchMaking(id: String) {
        findNavController().navigate(
            BookCommitmentFragmentDirections.actionBookCommitmentFragmentToMatchMakingFragment(
                commitmentId = id
            )
        )
    }

}