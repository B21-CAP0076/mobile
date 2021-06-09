package bangkit.capstone.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import bangkit.capstone.R
import bangkit.capstone.databinding.ActivityMainBinding
import bangkit.capstone.ui.home.HomeActivity
import bangkit.capstone.ui.screening.PersonalDataEntryActivity
import bangkit.capstone.core.util.SharedPreferenceHelper
import bangkit.capstone.util.State
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var preferenceHelper: SharedPreferenceHelper

    companion object {
        const val SIGN_IN_RC = 1
        private const val TAG = "MainActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestProfile()
            .requestEmail()
            .build()


        if (preferenceHelper.getBool("screening")) {
            val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }


        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            viewModel.alreadySignedInBefore()
        }

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val loginResultHandler = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult? ->
            if (result != null) {
                Log.d(TAG, result.toString())
                if (result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleSignInResult(task)
                }
            }
        }


        binding.loginbtn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            loginResultHandler.launch(signInIntent)
        }

        viewModel.status.observe(this, Observer {
            when (it) {
                is State.Loading -> {
                    Toast.makeText(this, "Loading..", Toast.LENGTH_LONG).show()
                }
                is  State.Success -> {
                    Toast.makeText(this, it.data, Toast.LENGTH_LONG).show()
                    goToPersonalDataActivity()
                }
                is State.Failed -> {
                    Toast.makeText(this, it.throwable.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account!!.idToken!!
            //Log.d(TAG, "token is ${idToken}")
            viewModel.signIn(idToken)
        } catch (e: ApiException) {
            Log.w(TAG, "handleSignInResult:error", e)
        }
    }

    private fun goToPersonalDataActivity() {
        val intent = Intent(this@MainActivity, PersonalDataEntryActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}


