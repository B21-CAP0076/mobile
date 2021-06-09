package bangkit.capstone.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Formatter {
    fun dateToString(s: Date): String? {
        return try {
            val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
            formatter.format(s)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun stringToDateTime(s: String) : Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        return sdf.parse(s) ?: Date()
    }
}