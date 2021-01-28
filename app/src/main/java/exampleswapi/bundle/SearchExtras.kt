package exampleswapi.bundle

import android.content.Context
import android.content.Intent

import exampleswapi.view.SearchActivity

/**
 * Holding intent extra names and utility methods for intent handling.
 */
object SearchExtras {

    const val QUERY = "QUERY"
    const val CATEGORY = "CATEGORY"

    /**
     * Intent & bundle builder for Search Activity
     *
     * @param context Context
     * @param query String
     * @param category String
     * @return
     */
    fun getIntent(context: Context, query: String, category: String): Intent {

        val intent = Intent(context, SearchActivity::class.java)
        intent.putExtra(QUERY, query)
        intent.putExtra(CATEGORY, category)

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
