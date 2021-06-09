package bangkit.capstone.core.util

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor() : Interceptor {
    private var sessionToken = ""

    fun setSessionToken(sessionToken: String) {
        this.sessionToken = sessionToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (sessionToken.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", sessionToken)
        }
        return chain.proceed(requestBuilder.build())
    }
}