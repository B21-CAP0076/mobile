package bangkit.capstone.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Formatter {

    fun getDateTime(s: String?): String? {
        try {
            val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.parse(s)
            } else {
                return s
            }
            val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH)
            return formatter.format(sdf)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}