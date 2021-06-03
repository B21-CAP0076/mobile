package bangkit.capstone.ui.home.ui.detail_book

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bangkit.capstone.R
import bangkit.capstone.databinding.FragmentDetailBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import java.util.*


class DetailBookFragment : Fragment() {
    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!
    private val cal = Calendar.getInstance()
    private val viewModel: DetailBookViewModel by activityViewModels()
    val args: DetailBookFragmentArgs by navArgs()

    companion object {
        const val BOOK_ID: String = "book_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailBook(args.bookId)
        viewModel.book.observe(viewLifecycleOwner, Observer {
            binding.fragmentdetailbookGenre.text = it.genres?.map { it -> it?.name }?.toList()?.joinToString(",")
            Glide.with(requireContext())
                .load(it.img)
                .apply(
                    RequestOptions().override(93, 140)
                        .transform(CenterCrop())
                )
                .into(binding.fragmentdetailbookImg)
            binding.fragmentdetailbookOverview.text = it.authors?.map { it -> it?.name }?.toList()?.joinToString(",")
            binding.fragmentdetailbookTitle.text = it.title
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    // TODO send data
                    navigateToMatchMaking()
                }
            binding.fragmentdetailbookButton.setOnClickListener {
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

    private fun navigateToMatchMaking() {
        findNavController().navigate(R.id.action_detailBookFragment_to_matchMakingFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}