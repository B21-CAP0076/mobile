package bangkit.capstone.dummy

import bangkit.capstone.data.*

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

    val bookList: List<Book> = listOf(
        Book(
            id = 1,
            title = "Test Book",
            overview = "Louisa Clark is an ordinary girl living an exceedingly ordinary life—steady boyfriend, close family—who has barely been farther afield than their tiny village. She takes a badly needed job working for ex–Master of the Universe Will Traynor, who is wheelchair bound after an accident. Will has always lived a huge life—big deals, extreme sports, worldwide travel—and now he’s pretty sure he cannot live the way he is.",
            image = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg",
            genre = "Fiction",
            rating = 4.5F
        ),
        Book(
            id = 1,
            title = "Test Book",
            overview = "Louisa Clark is an ordinary girl living an exceedingly ordinary life—steady boyfriend, close family—who has barely been farther afield than their tiny village. She takes a badly needed job working for ex–Master of the Universe Will Traynor, who is wheelchair bound after an accident. Will has always lived a huge life—big deals, extreme sports, worldwide travel—and now he’s pretty sure he cannot live the way he is.",
            image = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg",
            genre = "Fiction",
            rating = 4.5F
        )
    )

    private val user1 = ReadingCommitment(
        user = "Jinki",
        title = "Agaya",
        bookImg = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg"
    )

    private val user2 = ReadingCommitment(
        user = "Taemin",
        title = "Heater",
        bookImg = "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg"
    )

    val commitmentList = listOf<Commitment>(
        Commitment(
            id = 1,
            deadline = 1622103006,
            hobby = "Reading Books",
            user1 = user1,
            user2 = user2
        ),
        Commitment(
            id = 2,
            deadline = 1622103006,
            hobby = "Reading Books",
            user1 = user1,
            user2 = user2
        )
    )
}