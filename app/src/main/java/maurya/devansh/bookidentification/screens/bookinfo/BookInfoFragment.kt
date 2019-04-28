package maurya.devansh.bookidentification.screens.bookinfo


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_book_info.view.*
import maurya.devansh.bookidentification.R

class BookInfoFragment : Fragment() {

    private val args: BookInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_info, container, false)
//        Blurry.with(context).radius(25).sampling(2).onto(view.frame.backgroundImage)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v("querystring", args.bookVolumeId)

        val queue = Volley.newRequestQueue(context)
        val url = "https://www.googleapis.com/books/v1/volumes/" + args.bookVolumeId

        val requestOptions = RequestOptions()
        requestOptions.transform(BlurTransformation(30))

        Glide.with(context!!).load(R.drawable.sample)
                .apply(requestOptions)
                .into(view.backgroundImage)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                Log.d("Response", it.toString())
            },
            Response.ErrorListener {
                Log.d("Error", "Error")
            })

        queue.add(jsonObjectRequest)
    }
}
