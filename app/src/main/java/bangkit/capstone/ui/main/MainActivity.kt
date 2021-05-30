package bangkit.capstone.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import bangkit.capstone.R
import bangkit.capstone.databinding.ActivityMainBinding
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

//                    try {
//
//                        Log.d(TAG, "Token : ${result.data?.extras}")
//                    } catch (e: ApiException) {
//                        Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
//                        Log.d(TAG, e.toString())
//                        // The ApiException status code indicates the detailed failure reason.
//                    }

                }
            }
        }


        binding.loginbtn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            loginResultHandler.launch(signInIntent)
        }
//            val request: GetSignInIntentRequest = GetSignInIntentRequest.builder()
//                .setServerClientId(getString(R.string.server_client_id))
//                .build()
//
//            Identity.getSignInClient(this)
//                .getSignInIntent(request)
//                .addOnSuccessListener {
//                    loginResultHandler(it)
//                }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account!!.idToken
            Log.d(TAG, "Token : ${idToken}")

            // TODO(developer): send ID Token to server and validate
//            updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "handleSignInResult:error", e)
//            updateUI(null)
        }
    }
}


