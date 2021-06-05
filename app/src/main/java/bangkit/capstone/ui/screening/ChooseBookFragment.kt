package bangkit.capstone.ui.screening

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.capstone.adapter.BookAdapter
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.databinding.FragmentChooseBookBinding
import bangkit.capstone.core.data.dummy.ProvideDummy
import bangkit.capstone.ui.home.HomeActivity


class ChooseBookFragment : Fragment() {

    private var _binding: FragmentChooseBookBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentchoosebookRv.adapter = BookAdapter().apply {
            setData(ProvideDummy.bookList)
            setBehaviour(object : BookAdapter.BookAdapterBehaviour {
                override fun setOnClickListener(book: Book) {
                    // todo
                }

            })
        }
        binding.fragmentchoosebookRv.layoutManager = LinearLayoutManager(requireContext())
        binding.fragmentchoosebookSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                if (ProvideDummy.bookList.contains(query)) {
//                    adapter.filter.filter(query)
//                } else {
//                    Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
//                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
//                adapter.filter.filter(newText)
                return false
            }
        })
        binding.fragmentchoosebookButton.setOnClickListener {
            val intent = Intent(requireActivity(), HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}