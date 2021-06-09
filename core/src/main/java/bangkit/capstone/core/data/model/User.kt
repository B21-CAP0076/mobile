package bangkit.capstone.core.data.model

import bangkit.capstone.core.data.choice.Education


// GET
data class User(
    val id: String,
    val gid: String,
    val picture: String,
    val name: String,
    val email: String,
    var age: Int? = null,
    var education: Education? = null,
    var hobbies: List<Hobby>? = null,
    var previous_books: List<Book>? = null,
    var genre_preferences: List<Genre>? = null,
    var reading_cluster: Int? = null
)

// PATCH
data class UserUpdate(
    var age: Int? = null,
    var education: Education? = null,
    var hobbies: List<Hobby>? = null,
    var previous_books: List<Book>? = null,
    var genre_preferences: List<Genre>? = null,
    var reading_cluster: Int? = null
)
