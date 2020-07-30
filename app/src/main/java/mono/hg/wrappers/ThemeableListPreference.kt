package mono.hg.wrappers

import android.content.Context
import android.content.DialogInterface
import android.util.AttributeSet
import androidx.appcompat.app.AlertDialog
import androidx.preference.ListPreference
import mono.hg.R
import mono.hg.helpers.PreferenceHelper

/**
 * A ListPreference with custom style and dialogue buttons that follows [PreferenceHelper.accent].
 */
class ThemeableListPreference(context: Context?, attrs: AttributeSet?) :
    ListPreference(context, attrs) {
    override fun onClick() {
        with(AlertDialog.Builder(context, R.style.PreferenceList_NoRadio)) {
            setTitle(title)
            setMessage(dialogMessage)
            setSingleChoiceItems(entries, entries.indexOf(entry)) { it, index ->
                if (callChangeListener(entryValues[index].toString())) {
                    setValueIndex(index)
                }
                it.dismiss()
            }
            setNegativeButton(R.string.dialog_cancel) { it, _ ->
                it.dismiss()
            }

            create().apply {
                show()
                with(PreferenceHelper.darkAccent) {
                    getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(this)
                    getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(this)
                    getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(this)
                }
            }
        }
    }
}