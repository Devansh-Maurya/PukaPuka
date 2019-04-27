package maurya.devansh.bookidentification.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Devansh on 27/4/19.
 */
abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: Any)
}