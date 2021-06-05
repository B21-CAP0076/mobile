package bangkit.capstone.ui.screening

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import bangkit.capstone.R
import bangkit.capstone.databinding.ActivityPersonalDataEntryBinding
import bangkit.capstone.util.SharedPreferenceHelper

class PersonalDataEntryActivity : AppCompatActivity() {
    private val TAG = "PersonalDataEntryActivi"
    private lateinit var binding: ActivityPersonalDataEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val name = SharedPreferenceHelper.getString(this, getString(R.string.SHARED_PREFERENCE_KEY_NAME))
        binding.textView2.text = getString(R.string.hello, name)
        supportFragmentManager.commit {
            replace(R.id.container, BasicQuestionFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}