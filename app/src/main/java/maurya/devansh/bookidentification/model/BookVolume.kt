package maurya.devansh.bookidentification.model

/**
 * Created by Devansh on 27/4/19.
 */

data class BookVolume (
    var id: String = "",
    var title: String = "",
    var subtitle: String = "",
    var authors: String = "",
    var publisher: String = "",
    var isbn13: String = "",
    var category: String = "",
    var rating: Double = 0.0,
    var ratingsCount: Int = 0,
    var imageUrl: String = "",
    var pageCount: Int = 0,
    var description: String = "",
    var buyLink: String = "",
    var selfLink: String = "",
    var infoLink: String = ""
)