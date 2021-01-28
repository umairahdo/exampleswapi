package exampleswapi.bundle

import android.content.Context
import android.content.Intent

import exampleswapi.view.DetailActivity
import exampleswapi.models.SWModel

/**
 * Holding intent extra names and utility methods for intent handling.
 */
object DetailExtras {

    const val MODEL = "MODEL"

    /**
     * Intent builder for Detail Activity
     *
     * @param context
     * @param item SWModel
     * @return intent
     */
    fun getIntent(context: Context, item: SWModel): Intent {

        val intent = Intent(context, DetailActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.putExtra(MODEL, item)

        return intent
    }

    /**
     * Checks if all extras are present in an intent.
     *
     * @param intent The intent to check.
     * @param extras The extras to check for.
     * @return `true` if all extras are present, else `false`.
     */
    fun hasAll(intent: Intent, vararg extras: String): Boolean {
        for (extra in extras) {
            if (!intent.hasExtra(extra)) {
                return false
            }
        }
        return true
    }
}
