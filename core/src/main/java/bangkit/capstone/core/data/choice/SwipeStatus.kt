package bangkit.capstone.core.data.choice

import com.google.gson.annotations.SerializedName


enum class SwipeStatus {
    @SerializedName("right_swiped") RIGHT_SWIPED,
    @SerializedName("left_swiped") LEFT_SWIPED
}