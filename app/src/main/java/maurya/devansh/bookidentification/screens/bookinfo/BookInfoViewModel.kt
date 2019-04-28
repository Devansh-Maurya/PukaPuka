package maurya.devansh.bookidentification.screens.bookinfo

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import maurya.devansh.bookidentification.model.BookVolume
import maurya.devansh.bookidentification.util.fromHtml
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
    val rating = MutableLiveData<String>()
    val ratingsCount = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val pageCount = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val buyLink = MutableLiveData<String>()
    val shareText = MutableLiveData<String>()

    init {
        mediatorLiveData.addSource(bookInfoRepository.getBookVolumeObject(bookVolumeId)) {
            if (it != null) {
                title.value = it.title
                subtitle.value = it.subtitle
                authors.value = "by ${it.authors}"
                isbn13.value = "ISBN13 ${it.isbn13}"
                category.value = it.category
                rating.value = it.rating.toString() + " / 5"
                ratingsCount.value = " ⚫ ${it.ratingsCount} ${if (it.ratingsCount > 1) "ratings" else "rating"}"
                imageUrl.value = it.imageUrl
                pageCount.value = it.pageCount.toString() + " pages ⚫ "
                description.value = if (it.description.isNotEmpty()) fromHtml(it.description).toString()
                                    else "Not available"
                buyLink.value = it.buyLink

                shareText.value = "Check out ${it.title}" +
                        if (it.subtitle.isNotEmpty()) ": ${it.subtitle}" else "" + " book" +
                                "\n${it.bookVolumeRequestUrl}\n\n-By *Pakupaku app"
            }
        }

    }


}