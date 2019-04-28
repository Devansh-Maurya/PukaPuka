package maurya.devansh.bookidentification.screens.bookinfo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import maurya.devansh.bookidentification.extensions.*
import maurya.devansh.bookidentification.model.BookVolume
import org.json.JSONObject

/**
 * Created by Devansh on 28/4/19.
 */
class BookInfoRepository(private val context: Context) {

    fun getBookVolumeObject(bookVolumeId: String): LiveData<BookVolume> {
        val bookVolume = MutableLiveData<BookVolume>()

        Log.d("bookvolumeid", bookVolumeId)

        val queue = Volley.newRequestQueue(context)
        val url = "https://www.googleapis.com/books/v1/volumes/$bookVolumeId"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                Log.d("Response", it.toString())
                val bookVolumeData = makeBookVolumeObject(it)
                bookVolume.value = bookVolumeData
                return@Listener
            },
            Response.ErrorListener {
                Log.d("Error", "Error")
            })

        queue.add(jsonObjectRequest)

        return bookVolume
    }

    private fun makeBookVolumeObject(item: JSONObject): BookVolume {
        val bookVolume = BookVolume()

        bookVolume.apply {
            title = item.getTitle()
            subtitle = item.getSubtitle()
            authors = item.getAuthorsListAsString()
            isbn13 = item.getISBN13()
            category = item.getCategory()
            rating = item.getRating()
            ratingsCount = item.getRatingsCount()
            pageCount = item.getPageCount()
            description = item.getDescription()
            selfLink = item.getSelfLink()
            infoLink = item.getInfoLink()
            //previewLink = item.getP
            imageUrl = item.getThumbnailImageUrl()
        }

        return bookVolume

    }
}