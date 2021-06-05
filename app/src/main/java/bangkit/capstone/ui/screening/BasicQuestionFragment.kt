package bangkit.capstone.ui.screening

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import bangkit.capstone.R
import bangkit.capstone.databinding.FragmentBasicQuestionBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

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
        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_job_item, items)
        binding.personaldataentryJobautocomplete.setAdapter(adapter)
        binding.personaldataentryJobautocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.setJob(selectedItem)
            }
        binding.personaldataentryAge.doAfterTextChanged {
            Log.d(TAG, "char sequence $it")
            viewModel.setAge(it.toString().toInt())
        }
//        binding.personaldataentryAge.addTextChangedListener {
//            object : TextWatcher {
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    Log.d(TAG, "beforeTextChange $p0")
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    Log.d(TAG, "char sequence $p0")
//                    viewModel.setAge(p0.toString().toInt())
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                    Log.d(TAG, "afterTextChanged $p0")
//                    TODO("Not yet implemented")
//                }
//
//            }
//        }
        val educationList = listOf("SD", "SMP", "SMA", "D1", "D3", "S1/D4", "S2", "S3")
        val educationAdapter = ArrayAdapter(requireContext(), R.layout.list_job_item, educationList)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}