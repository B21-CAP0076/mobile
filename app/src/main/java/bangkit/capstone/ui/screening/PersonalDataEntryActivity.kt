package bangkit.capstone.ui.screening

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import bangkit.capstone.R
import bangkit.capstone.core.data.model.User
import bangkit.capstone.databinding.ActivityPersonalDataEntryBinding
import bangkit.capstone.core.util.SharedPreferenceHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonalDataEntryActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private val TAG = "PersonalDataEntryActivi"
    private lateinit var binding: ActivityPersonalDataEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val name =
            Gson().fromJson<User>(sharedPreferenceHelper.getString("user"), User::class.java).name
        binding.textView2.text = getString(R.string.hello, name)
        supportFragmentManager.commit {
            replace(R.id.container, BasicQuestionFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}