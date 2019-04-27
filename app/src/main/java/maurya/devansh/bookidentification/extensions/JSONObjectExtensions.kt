package maurya.devansh.bookidentification.extensions

import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.AUTHORS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.IMAGE_LINKS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.SMALL_THUMBNAIL_URL
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.TITLE
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesListConsts.VOLUME_INFO
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Devansh on 27/4/19.
 */

fun JSONObject.getAuthorsListAsString(): String {
    val authors = getAuthorsList()
    return if (authors.isNotEmpty()) {
        val authorsString = StringBuilder()

        for (author in authors) {
            authorsString.append(author)
        }

        authorsString.toString()
    } else ""
}

fun JSONObject.getAuthorsList(): List<String> {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(AUTHORS)) {
        val jsonArray = volumeInfo.get(AUTHORS) as JSONArray
        val authorsList = arrayListOf<String>()

        for (i in 0 until jsonArray.length() - 1) {
            authorsList.add(jsonArray[i] as String + ", ")
        }
        authorsList.add(jsonArray[jsonArray.length() - 1] as String)
        authorsList
    } else
        listOf()
}

fun JSONObject.getSmallThumbnailImageUrl(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(IMAGE_LINKS)) {
        val imageLinks = getVolumeInfo().get(IMAGE_LINKS) as JSONObject
        imageLinks.get(SMALL_THUMBNAIL_URL) as String
    } else ""
}

fun JSONObject.getTitle() = getVolumeInfo().get(TITLE) as String

private fun JSONObject.getVolumeInfo(): JSONObject = get(VOLUME_INFO) as JSONObject