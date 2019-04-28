package maurya.devansh.bookidentification.screens.bookinfo


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_book_info.view.*
import maurya.devansh.bookidentification.R
import maurya.devansh.bookidentification.extensions.*
import maurya.devansh.bookidentification.model.BookVolume
import org.json.JSONObject

class BookInfoFragment : Fragment() {

    private val args: BookInfoFragmentArgs by navArgs()

    private lateinit var viewModel: BookInfoViewModel
    private lateinit var viewModelFactory: BookInfoViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_info, container, false)

        viewModelFactory = BookInfoViewModelFactory(args.bookVolumeId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BookInfoViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v("querystring", args.bookVolumeId)

        val queue = Volley.newRequestQueue(context)
        val url = "https://www.googleapis.com/books/v1/volumes/" + args.bookVolumeId

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                Log.d("Response", it.toString())
                val bookVolume = makeBookVolumeObject(it)
                setBookImage(view, bookVolume.imageUrl)
            },
            Response.ErrorListener {
                Log.d("Error", "Error")
            })

        queue.add(jsonObjectRequest)
    }

    @SuppressLint("CheckResult")
    private fun setBookImage(view: View, imageUrl: String) {

        var bitmap: Bitmap

        val requestOptions = RequestOptions()
        requestOptions.transform(BlurTransformation(24))

        Glide.with(context!!)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Glide.with(context!!).load(resource)
                                .apply(requestOptions).into(view.backgroundImage)
                        Glide.with(context!!).load(resource).into(view.coverImageView)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        view.backgroundImage.invalidate()
                        view.coverImageView.invalidate()
                    }
                })
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
            publishedDate = item.getPublishedDate()
            imageUrl = item.getCoverImageUrl()
            if (imageUrl.isEmpty())
                imageUrl = item.getSmallThumbnailImageUrl()
        }

        return bookVolume

    }
}
