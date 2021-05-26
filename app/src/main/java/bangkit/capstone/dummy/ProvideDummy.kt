package bangkit.capstone.dummy

import bangkit.capstone.data.Genre
import bangkit.capstone.data.Hobby

object ProvideDummy {

    val hobbyList: List<Hobby> = listOf(
        Hobby(
            id = 1,
            title = "Reading Books",
            image = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg"
        )
    )

    val genreList: List<Genre> = listOf(
        Genre(
            id = 1,
            title = "Fiction",
            image = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg"
        ),
        Genre(
            id = 2,
            title = "Science",
            image = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg"
        )
    )
}