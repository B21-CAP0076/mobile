package bangkit.capstone.core.data.choice

import com.google.gson.annotations.SerializedName

enum class Education {
    @SerializedName("SD") SD,
    @SerializedName("SMP") SMP,
    @SerializedName("SMA") SMA,
    @SerializedName("Diploma") DIPLOMA,
    @SerializedName("S1") S1,
    @SerializedName("S2") S2,
    @SerializedName("S3") S3,
    @SerializedName("Profesi") PROFESI
}