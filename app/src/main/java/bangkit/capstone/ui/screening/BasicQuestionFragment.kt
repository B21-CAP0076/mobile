package bangkit.capstone.ui.screening

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.commit
import bangkit.capstone.R
import bangkit.capstone.databinding.FragmentBasicQuestionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BasicQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasicQuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val TAG = "BasicQuestionFragment"
    private var _binding: FragmentBasicQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicQuestionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_job_item, items)
        binding.personaldataentryJobautocomplete.setAdapter(adapter)
        binding.personaldataentryJobautocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d(TAG, "job selected $selectedItem")
            }

        binding.fragmentbasicquestionButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.container, ChooseHobbyFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}