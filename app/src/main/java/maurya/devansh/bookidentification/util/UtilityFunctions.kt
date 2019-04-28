package maurya.devansh.bookidentification.util

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * Created by Devansh on 28/4/19.
 */

fun fromHtml(source: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }
}