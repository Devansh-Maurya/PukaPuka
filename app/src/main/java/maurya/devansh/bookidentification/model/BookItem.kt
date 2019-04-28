package maurya.devansh.bookidentification.model

/**
 * Created by Devansh on 27/4/19.
 */

data class BookItem(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val authors: String = "",
    val publisher: String = "",
    val isbn13: String = "",
    val category: String = "",
    val rating: Double = 0.0,
    val thumbnailUrl: String = "",
    val description: String = ""
)