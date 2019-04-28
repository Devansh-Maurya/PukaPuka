package maurya.devansh.bookidentification.screens.bookinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Devansh on 28/4/19.
 */

class BookInfoViewModelFactory(private val bookVolumeId: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookInfoViewModel::class.java)) {
            return BookInfoViewModel(bookVolumeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}