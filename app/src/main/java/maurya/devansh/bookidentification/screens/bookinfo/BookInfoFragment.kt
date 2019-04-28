package maurya.devansh.bookidentification.screens.bookinfo


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_book_info.view.*
import maurya.devansh.bookidentification.MainActivity
import maurya.devansh.bookidentification.R

class BookInfoFragment : Fragment() {

    private val args: BookInfoFragmentArgs by navArgs()

    private lateinit var viewModel: BookInfoViewModel
    private lateinit var viewModelFactory: BookInfoViewModelFactory

    private var shareText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_info, container, false)

        viewModelFactory = BookInfoViewModelFactory(args.bookVolumeId)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BookInfoViewModel::class.java)
        //A blank observer for MediatorLiveData
        viewModel.mediatorLiveData.observe(this, Observer {  })
        observeBookVolumeInfoLiveDatas(view)

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share)
            startActivity(getShareIntent())
        return true
    }

    private fun observeBookVolumeInfoLiveDatas(view: View) {
        viewModel.apply {
            title.observe(this@BookInfoFragment, Observer {
                view.titleTV.text = it
                (activity as MainActivity).supportActionBar?.title = it
            })

            subtitle.observe(this@BookInfoFragment, Observer {
                if (it.isEmpty())
                    view.subtitleTV.visibility = View.GONE
                else {
                    view.subtitleTV.visibility = View.VISIBLE
                    view.subtitleTV.text = it
                }
            })

            authors.observe(this@BookInfoFragment, Observer {
                view.authorsTV.text = it
            })

            isbn13.observe(this@BookInfoFragment, Observer {
                view.isbn13TV.text = it
            })

            category.observe(this@BookInfoFragment, Observer {})

            rating.observe(this@BookInfoFragment, Observer {
                view.ratingsTV.text = it
            })

            ratingsCount.observe(this@BookInfoFragment, Observer {
                view.ratingsCountTV.text = it
            })

            imageUrl.observe(this@BookInfoFragment, Observer {
                setBookImage(view, it)
            })

            pageCount.observe(this@BookInfoFragment, Observer {
                view.pageCountTV.text = it
            })

            description.observe(this@BookInfoFragment, Observer {
                view.descriptionTV.text = it
            })

            buyLink.observe(this@BookInfoFragment, Observer {
                if (it.isNotEmpty()) {
                    view.buyButton.visibility = View.VISIBLE
                    view.buyButton.setOnClickListener { }
                }
                else
                    view.buyButton.visibility = View.GONE
            })


            shareText.observe(this@BookInfoFragment, Observer {
                this@BookInfoFragment.shareText = it
            })

        }
    }


    @SuppressLint("CheckResult")
    private fun setBookImage(view: View, imageUrl: String) {

        val blurRequestOptions = RequestOptions()
        blurRequestOptions.transform(BlurTransformation(20))
        blurRequestOptions.placeholder(R.drawable.placeholder_cover)

        Glide.with(context!!)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Glide.with(context!!).load(resource).apply(blurRequestOptions).into(view.backgroundImage)
                                .onLoadFailed(resources.getDrawable(R.drawable.ic_no_cover))

                        Glide.with(context!!).load(resource)
                                .placeholder(R.drawable.placeholder_cover)
                                .into(view.coverImageView)
                                .onLoadFailed(resources.getDrawable(R.drawable.ic_no_cover))
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        view.backgroundImage.invalidate()
                        view.coverImageView.invalidate()
                    }
                })
    }

    private fun getShareIntent(): Intent =
            ShareCompat.IntentBuilder.from(activity)
                    .setText(shareText)
                    .setType("text/plain")
                    .intent
}
