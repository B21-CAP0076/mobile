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

    @FlowPreview
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private  val viewModel: BasicQuestionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicQuestionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_job_item, items)
        binding.personaldataentryJobautocomplete.setAdapter(adapter)
        binding.personaldataentryJobautocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                lifecycleScope.launch {
                    viewModel.jobChannel.send(selectedItem)
                }
            }

        binding.personaldataentryBio.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lifecycleScope.launch {
                    viewModel.bioChannel.send(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        } }
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