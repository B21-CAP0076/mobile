package bangkit.capstone.ui.screening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import bangkit.capstone.R
import bangkit.capstone.core.data.choice.Education
import bangkit.capstone.databinding.FragmentBasicQuestionBinding
import bangkit.capstone.util.Constants
import bangkit.capstone.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
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
        binding.personaldataentryAge.doAfterTextChanged {
            viewModel.setAge(it.toString().toInt())
        }
        val educationAdapter = ArrayAdapter(requireContext(), R.layout.list_job_item, Constants.EDUCATION_LIST.map { it -> it.name })
        binding.personaldataentryEducationautocomplete.setAdapter(educationAdapter)
        binding.personaldataentryEducationautocomplete.setOnItemClickListener { adapterView, view, i, l ->
            val selectedItem = adapterView.getItemAtPosition(i).toString()
            viewModel.setEducation(selectedItem)

        }
        binding.fragmentbasicquestionButton.setOnClickListener {
            viewModel.submit()
        }
        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    parentFragmentManager.commit {
                        replace(R.id.container, ChooseHobbyFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
                is State.Failed -> {
                    Toast.makeText(requireContext(), it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}