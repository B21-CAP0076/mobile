package bangkit.capstone.dummy

import bangkit.capstone.core.data.*
import bangkit.capstone.core.data.model.Book
import bangkit.capstone.core.data.model.Genre
import bangkit.capstone.core.data.model.Hobby
import bangkit.capstone.core.data.model.User
import com.google.gson.Gson

object ProvideDummy {

    val hobbyList: List<Hobby> = listOf(
        Hobby(
            id = "123",
            name = "Reading Books",
        ),
        Hobby(
            id = "234",
            name = "Berenang",
        )
    )

    val genreList: List<Genre> = listOf(
        Genre(
            id = "asjfi",
            name = "Fiction",
        ),
        Genre(
            id = "anslkf",
            name = "Science",

        )
    )

    val bookList: List<Book> = listOf(
        Gson().fromJson(
            """{
        "img": "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg",
        "title": "string",
        "authors": [
        {
            "name": "string",
            "id": "au5f85f36d6dfecacc68428a46"
        }
        ],
        "genres": [
        {
            "name": "string",
            "id": "ge5f85f36d6dfecacc68428a46"
        }
        ],
        "id": "id5f85f36d6dfecacc6842a46"
    }""", Book::class.java
        ),
        Gson().fromJson(
            """{
        "img": "https://media.wired.com/photos/5955c355ad90646d424bb62b/master/w_2560%2Cc_limit/books.jpg",
        "title": "string",
        "authors": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "genres": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "id": "5f85f36d6dfecacc68428a46"
    }""", Book::class.java
        )
    )


    val user1 = Gson().fromJson<User>(
        """{"g_id": "string",
        "img": "string",
        "username": "Lee Jinki",
        "email": "user@example.com",
        "birthdate": "2021-06-02T14:36:11.333741",
        "education": "SD",
        "hobbies": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "previous_books": [
        {
            "img": "string",
            "title": "string",
            "authors": [
            {
                "name": "string",
                "id": "5f85f36d6dfecacc68428a46"
            }
            ],
            "genres": [
            {
                "name": "string",
                "id": "5f85f36d6dfecacc68428a46"
            }
            ],
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "genre_preferences": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "reading_cluster": 0,
        "id": "5f85f36d6dfecacc68428a46"}""", User::class.java
    )

    val user2 = Gson().fromJson<User>(
        """{"g_id": "string",
        "img": "string",
        "username": "Kim Kibum",
        "email": "user@example.com",
        "birthdate": "2021-06-02T14:36:11.333741",
        "education": "SD",
        "hobbies": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "previous_books": [
        {
            "img": "string",
            "title": "string",
            "authors": [
            {
                "name": "string",
                "id": "5f85f36d6dfecacc68428a46"
            }
            ],
            "genres": [
            {
                "name": "string",
                "id": "5f85f36d6dfecacc68428a46"
            }
            ],
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "genre_preferences": [
        {
            "name": "string",
            "id": "5f85f36d6dfecacc68428a46"
        }
        ],
        "reading_cluster": 0,
        "id": "5f85f36d6dfecacc68428a46"}""", User::class.java
    )

    val readingCommitment1 = ReadingCommitment(
        owner = user1,
        partner = user2,
        book = bookList[0],
        creationDate = "2021-06-02T14:36:11.334741",
        endDate = "2021-06-02T14:36:11.334741",
        id = "asjfasfoiwe"
    )

    val readingCommitment2 = ReadingCommitment(
        owner = user1,
        partner = user2,
        book = bookList[1],
        creationDate = "2021-06-02T14:36:11.334741",
        endDate = "2021-06-02T14:36:11.334741",
        id = "asjfasfoiwe"
    )

    val commitmentList = listOf<ReadingCommitment>(
        readingCommitment1, readingCommitment2
    )

    val matchList = listOf<Match>(
        Match(readingCommitment = readingCommitment1, deadline = 1622103006),
        Match(readingCommitment = readingCommitment2, deadline = 1622103006)
    )

    val bookSummaries: BookSummary = BookSummary(
        bookSummary = listOf(
            BookSummaryItem(
                summary = "Jinkijinki",
                readingCommitment = readingCommitment2,
                creationDate = "2021-06-02T14:36:11.334741",
                id = "yawyaw"
            ),
            BookSummaryItem(
                summary = "bumbumpaw",
                readingCommitment = readingCommitment2,
                creationDate = "2021-06-02T14:36:11.334741",
                id = "yawyaw"
            )
        )
    )

//    {
//        "reading_commitment": {
//        "partner": {
//        "g_id": "string",
//        "img": "string",
//        "username": "string",
//        "email": "user@example.com",
//        "birthdate": "2021-06-02T14:36:11.333741",
//        "education": "SD",
//        "hobbies": [
//        {
//            "name": "string",
//            "id": "5f85f36d6dfecacc68428a46"
//        }
//        ],
//        "previous_books": [
//        {
//            "img": "string",
//            "title": "string",
//            "authors": [
//            {
//                "name": "string",
//                "id": "5f85f36d6dfecacc68428a46"
//            }
//            ],
//            "genres": [
//            {
//                "name": "string",
//                "id": "5f85f36d6dfecacc68428a46"
//            }
//            ],
//            "id": "5f85f36d6dfecacc68428a46"
//        }
//        ],
//        "genre_preferences": [
//        {
//            "name": "string",
//            "id": "5f85f36d6dfecacc68428a46"
//        }
//        ],
//        "reading_cluster": 0,
//        "id": "5f85f36d6dfecacc68428a46"
//    },
//        "creation_date": "2021-06-02T14:36:11.333741",
//        "end_date": "2021-06-02T14:36:11.334741",
//        "book":,
//        "id": "5f85f36d6dfecacc68428a46"
//    },
//        "creation_date": "2021-06-02T14:36:11.334741",
//        "summary": "string",
//        "id": "5f85f36d6dfecacc68428a46"
//    }
}