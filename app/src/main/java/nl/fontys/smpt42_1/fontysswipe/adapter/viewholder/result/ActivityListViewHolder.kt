package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.list_item_activity.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.Activity

/**
 * @author Robin Laugs
 */
class ActivityListViewHolder(val itemView: View) {

    fun set(activity: Activity) = with(itemView) {
        text_view_activity_name.text = activity.title
    }

}
