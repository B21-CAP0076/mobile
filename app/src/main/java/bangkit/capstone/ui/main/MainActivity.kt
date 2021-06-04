package bangkit.capstone.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import bangkit.capstone.R
import bangkit.capstone.databinding.ActivityMainBinding
import bangkit.capstone.ui.home.HomeActivity
import bangkit.capstone.ui.screening.PersonalDataEntryActivity
import bangkit.capstone.util.SharedPreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        const val SIGN_IN_RC = 1
        private const val TAG = "MainActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // TEMPORARY
        goToPersonalDataActivity()

//        if (SharedPreferenceHelper.isStringExist(this, getString(R.string.SHARED_PREFERENCE_KEY_NAME))) {
//            val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            startActivity(intent)
//        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        // TODO tinggal handle ini aja
//        GoogleSignIn.silentSignIn()
//            .addOnCompleteListener(
//                this,
//                OnCompleteListener<GoogleSignInAccount?> { task -> handleSignInResult(task) })

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
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account!!.idToken
            val name = account.displayName
            if (name != null) {
                SharedPreferenceHelper.setString(this, getString(R.string.SHARED_PREFERENCE_KEY_NAME), name)
            }
            goToPersonalDataActivity()
        } catch (e: ApiException) {
            Log.w(TAG, "handleSignInResult:error", e)
//            updateUI(null)
        }
    }

    private fun goToPersonalDataActivity() {
        val intent = Intent(this@MainActivity, PersonalDataEntryActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}


