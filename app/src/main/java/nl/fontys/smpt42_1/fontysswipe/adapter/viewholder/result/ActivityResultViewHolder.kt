package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.card_result_activity.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.result.ActivityResult

/**
 * @author SMPT42-1
 */
class ActivityResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: ActivityResult) = with(itemView) {
        text_view_activity_title.text = result.title
    }

}
