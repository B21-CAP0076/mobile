package bangkit.capstone.util

import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    fun getDateTime(s: Int): String? {
        try {
            val sdf = SimpleDateFormat("dd MMMM")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}