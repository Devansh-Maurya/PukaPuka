package maurya.devansh.bookidentification.extensions

import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.AUTHORS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.IMAGE_LINKS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.SMALL_THUMBNAIL_URL
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.VOLUME_INFO
import org.json.JSONObject

/**
 * Created by Devansh on 27/4/19.
 */

fun JSONObject.getAuthorsListAsString(): String {
    val authors = getAuthorsList()
    val authorsString = StringBuilder()

    for (author in authors) {
        authorsString.append(author)
    }

    return authorsString.toString()
}

fun JSONObject.getAuthorsList(): List<String> {
    val volumeInfo = get(VOLUME_INFO) as JSONObject
    return volumeInfo.get(AUTHORS) as List<String>
}

fun JSONObject.getSmallThumbnailImageUrl(): String {
    val imageLinks = get(IMAGE_LINKS) as JSONObject
    return imageLinks.get(SMALL_THUMBNAIL_URL) as String
}