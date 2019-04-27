package maurya.devansh.bookidentification.screens.bookslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import maurya.devansh.bookidentification.R
import maurya.devansh.bookidentification.model.BooksListItem

/**
 * Created by Devansh on 27/4/19.
 */
class BooksListAdapter(private val booksList: List<BooksListItem>) : RecyclerView.Adapter<BookItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        return BookItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(booksList[position])
    }
}