package bangkit.capstone.core.data.choice

import com.google.gson.annotations.SerializedName


enum class MatchStatus {
    @SerializedName("match") MATCH,
    @SerializedName("pending") PENDING,
    @SerializedName("rejected") REJECTED
}