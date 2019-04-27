package maurya.devansh.bookidentification.model

/**
 * Created by Devansh on 27/4/19.
 */

data class BooksListItem(
    var id: String = "",
    var title: String = "",
    var subtitle: String = "",
    var authors: String = "",
    var smallThumbnailUrl: String = ""
)