package maurya.devansh.bookidentification.extensions

import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.AUTHORS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.AVERAGE_RATING
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.BUY_LINK
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.DESCRIPTION
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.IDENTIFIER
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.IMAGE_LINKS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.INDUSTRY_IDENTIFIERS
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.INFO_LINK
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.ISBN_13
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.ISBN_TYPE
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.MAIN_CATEGORY
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.MEDIUM_IMAGE_URL
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.PAGE_COUNT
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.PUBLISHED_DATE
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.RATINGS_COUNT
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.SALE_INFO
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.SELF_LINK
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.SMALL_THUMBNAIL_URL
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.SUBTITLE
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.TITLE
import maurya.devansh.bookidentification.consts.GoogleBooksVolumesConsts.VOLUME_INFO
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

private fun JSONObject.getVolumeInfo(): JSONObject = get(VOLUME_INFO) as JSONObject

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

fun JSONObject.getThumbnailImageUrl(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(IMAGE_LINKS)) {
        val imageLinks = getVolumeInfo().get(IMAGE_LINKS) as JSONObject
        imageLinks.get(THUMBNAIL_URL) as String
    } else ""
}

fun JSONObject.getTitle() = getVolumeInfo().get(TITLE) as String

fun JSONObject.getSubtitle(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(SUBTITLE)) {
        volumeInfo.get(SUBTITLE) as String
    } else ""
}

fun JSONObject.getPublishedDate(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(PUBLISHED_DATE))
        volumeInfo.get(PUBLISHED_DATE) as String
    else ""
}

fun JSONObject.getISBN13(): String {
    val volumeInfo = getVolumeInfo()
    var isbn = ""
    if (volumeInfo.has(INDUSTRY_IDENTIFIERS)) {
        val industryIdentifiers = volumeInfo.get(INDUSTRY_IDENTIFIERS) as JSONArray
        for (i in 0 until industryIdentifiers.length()) {
            val isbnType = (industryIdentifiers[i] as JSONObject).getString(ISBN_TYPE)
            if(isbnType == ISBN_13) {
                isbn = (industryIdentifiers[i] as JSONObject).getString(IDENTIFIER)
            }
        }
    }

    return isbn
}

fun JSONObject.getCategory(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(MAIN_CATEGORY))
        volumeInfo.getString(MAIN_CATEGORY)
    else ""
}

fun JSONObject.getRating(): Double {
    val volumeInfo = getVolumeInfo()
    return if(volumeInfo.has(AVERAGE_RATING))
        volumeInfo.getDouble(AVERAGE_RATING)
    else 0.0
}

fun JSONObject.getRatingsCount(): Int {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(RATINGS_COUNT))
        volumeInfo.getInt(RATINGS_COUNT)
    else 0
}

fun JSONObject.getCoverImageUrl(): String {
    val volumeInfo = getVolumeInfo()
    var url = ""
    if (volumeInfo.has(IMAGE_LINKS)) {
        val imageLinks = volumeInfo.getJSONObject(IMAGE_LINKS)
        if (imageLinks.has(MEDIUM_IMAGE_URL))
            url = imageLinks.getString(MEDIUM_IMAGE_URL)
    }

    return url
}

fun JSONObject.getPageCount(): Int {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(PAGE_COUNT))
        volumeInfo.getInt(PAGE_COUNT)
    else
        0
}

fun JSONObject.getDescription(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(DESCRIPTION))
        volumeInfo.getString(DESCRIPTION)
    else ""
}

fun JSONObject.getBuyLink(): String {
    val saleInfo = getJSONObject(SALE_INFO)
    return if (saleInfo.has(BUY_LINK))
        saleInfo.getString(BUY_LINK)
    else ""
}

fun JSONObject.getSelfLink() = getString(SELF_LINK)

fun JSONObject.getInfoLink(): String {
    val volumeInfo = getVolumeInfo()
    return if (volumeInfo.has(INFO_LINK))
        volumeInfo.getString(INFO_LINK)
    else ""
}