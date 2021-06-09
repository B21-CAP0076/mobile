package bangkit.capstone.core.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import bangkit.capstone.core.data.api.*
import bangkit.capstone.core.util.EnumConverterFactory
import bangkit.capstone.core.util.HeaderInterceptor
import bangkit.capstone.core.util.SharedPreferenceHelper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideUserApi(headerInterceptor: HeaderInterceptor): UserApi {
        return retrofitBuilder(UserApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideHobbyApi(headerInterceptor: HeaderInterceptor): HobbyApi {
        return retrofitBuilder(HobbyApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideBookApi(headerInterceptor: HeaderInterceptor): BookApi {
        return retrofitBuilder(BookApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideGenreApi(headerInterceptor: HeaderInterceptor): GenreApi {
        return retrofitBuilder(GenreApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideReadingCommitmentApi(headerInterceptor: HeaderInterceptor): ReadingCommitmentApi {
        return retrofitBuilder(ReadingCommitmentApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideBookSummaryApi(headerInterceptor: HeaderInterceptor): BookSummaryApi {
        return retrofitBuilder(BookSummaryApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideSwipeApi(headerInterceptor: HeaderInterceptor): MatchApi {
        return retrofitBuilder(MatchApi::class.java, headerInterceptor)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context) : SharedPreferenceHelper {
        return SharedPreferenceHelper(context.getSharedPreferences("bangkit-sp", MODE_PRIVATE))
    }


    private fun <T> retrofitBuilder(apiClass: Class<T>, headerInterceptor: HeaderInterceptor): T {
        val baseUrl = "https://api.habitbangkit.tech/"

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder().apply {
            addNetworkInterceptor(headerInterceptor)
            addInterceptor(logging)
                readTimeout(15, TimeUnit.SECONDS)
                connectTimeout(15, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .client(okHttpClient.build())
            .build()
            .create(apiClass)
    }


}