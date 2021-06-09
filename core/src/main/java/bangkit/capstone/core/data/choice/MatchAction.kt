package bangkit.capstone.core.data.choice

import com.google.gson.annotations.SerializedName

enum class MatchAction {
    @SerializedName("swipe_right") SWIPE_RIGHT,
    @SerializedName("swipe_left") SWIPE_LEFT
}