package bangkit.capstone.ui.screening

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.R
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.databinding.FragmentChooseBookBinding
import bangkit.capstone.dummy.ProvideDummy
import bangkit.capstone.ui.home.HomeActivity
import bangkit.capstone.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch


@AndroidEntryPoint
@FlowPreview
class ChooseBookFragment : Fragment() {

    private var _binding: FragmentChooseBookBinding?=null
    private val binding get() = _binding!!
    private val viewModel: ChooseBookViewModel by viewModels()
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter().apply {
            setBehaviour(object : BookAdapter.BookAdapterBehaviour {
                override fun setOnClickListener(book: Book) {
                    viewModel.setBook(book)
                }
            })
        }
        binding.fragmentchoosebookRv.adapter = adapter

            viewModel.books.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        binding.fragmentchoosebookRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.fragmentchoosebookSv.doAfterTextChanged {
            lifecycleScope.launch {
                viewModel.query.value = it.toString()
            }
        }
        binding.fragmentchoosebookButton.setOnClickListener {
            viewModel.submit()
        }
        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    val intent = Intent(requireActivity(), HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
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
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}