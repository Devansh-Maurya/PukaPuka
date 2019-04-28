package maurya.devansh.bookidentification.screens.bookinfo

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import maurya.devansh.bookidentification.model.BookVolume
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Created by Devansh on 28/4/19.
 */

class BookInfoViewModel(bookVolumeId: String) : ViewModel(), KoinComponent {

    private val bookInfoRepository: BookInfoRepository by inject()
    val mediatorLiveData = MediatorLiveData<BookVolume>()

    val title = MutableLiveData<String>()
    val subtitle = MutableLiveData<String>()
    val authors = MutableLiveData<String>()
    val isbn13 = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val rating = MutableLiveData<Double>()
    val ratingsCount = MutableLiveData<Int>()
    val imageUrl = MutableLiveData<String>()
    val pageCount = MutableLiveData<Int>()
    val description = MutableLiveData<String>()
    val publishedDate = MutableLiveData<String>()

    init {
        Log.d("bookvolumeid", bookVolumeId)

        mediatorLiveData.addSource(bookInfoRepository.getBookVolumeObject(bookVolumeId)) {
            if (it != null) {
                title.value = it.title
                subtitle.value = it.subtitle
                authors.value = it.authors
                isbn13.value = it.isbn13
                category.value = it.category
                rating.value = it.rating
                ratingsCount.value = it.ratingsCount
                imageUrl.value = it.imageUrl
                pageCount.value = it.pageCount
                description.value = it.description
                publishedDate.value = it.publishedDate
            }
        }

    }


}