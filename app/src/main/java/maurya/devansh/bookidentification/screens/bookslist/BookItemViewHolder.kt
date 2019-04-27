package maurya.devansh.bookidentification.screens.bookslist

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_book.view.*
import maurya.devansh.bookidentification.model.BooksListItem
import maurya.devansh.bookidentification.util.BaseViewHolder

/**
 * Created by Devansh on 27/4/19.
 */
class BookItemViewHolder(view: View) : BaseViewHolder(view) {

    override fun bind(item: Any) {
        item as BooksListItem

        view.apply {
            if (item.smallThumbnailUrl != "") {
                Glide.with(this).load(item.smallThumbnailUrl).into(imageView)
            }
            titleTV.text = item.title

            if (item.subtitle.isEmpty())
                subtitleTV.visibility = View.GONE
            else {
                subtitleTV.visibility = View.VISIBLE
                subtitleTV.text = item.subtitle
            }

            authorsTV.text = item.authors
        }
    }
}