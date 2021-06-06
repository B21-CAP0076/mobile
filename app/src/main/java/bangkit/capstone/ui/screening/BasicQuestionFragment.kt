package bangkit.capstone.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import bangkit.capstone.R
import bangkit.capstone.databinding.FragmentBasicQuestionBinding
import bangkit.capstone.util.Constants
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

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
    private  val viewModel: BasicQuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicQuestionBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect {
                binding.fragmentbasicquestionButton.isEnabled = it
            }
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_job_item, Constants.JOB_LIST)
        binding.personaldataentryJobautocomplete.setAdapter(adapter)
        binding.personaldataentryJobautocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.setJob(selectedItem)
            }
        binding.personaldataentryAge.doAfterTextChanged {
            viewModel.setAge(it.toString().toInt())
        }
        val educationAdapter = ArrayAdapter(requireContext(), R.layout.list_job_item, Constants.EDUCATION_LIST)
        binding.personaldataentryEducationautocomplete.setAdapter(educationAdapter)
        binding.personaldataentryEducationautocomplete.setOnItemClickListener { adapterView, view, i, l ->
            val selectedItem = adapterView.getItemAtPosition(i).toString()
            viewModel.setEducation(selectedItem)

        }
        binding.fragmentbasicquestionButton.setOnClickListener {
            viewModel.submit()
            parentFragmentManager.commit {
                replace(R.id.container, ChooseHobbyFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}