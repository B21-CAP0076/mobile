package bangkit.capstone.ui.home.ui.detail_book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import bangkit.capstone.R
import bangkit.capstone.databinding.FragmentDetailBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions


class DetailBookFragment : Fragment() {
    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!
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
            binding.fragmentdetailbookGenre.text = it.genre
            Glide.with(requireContext())
                .load(it.image)
                .apply(
                    RequestOptions().override(93, 140)
                        .transform(CenterCrop())
                )
                .into(binding.fragmentdetailbookImg)
            binding.fragmentdetailbookOverview.text = it.overview
            binding.fragmentdetailbookRb.rating = it.rating
            binding.fragmentdetailbookTitle.text = it.title
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}