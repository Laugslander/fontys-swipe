package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.list_item_activity.view.*
import nl.fontys.smpt42_1.fontysswipe.adapter.TimetableItemAdapter
import nl.fontys.smpt42_1.fontysswipe.domain.Activity
import java.lang.String.format

/**
 * @author Robin Laugs
 */
class ActivityListViewHolder(val itemView: View) {

    fun set(activity: Activity) = with(itemView) {
        text_view_activity_name.text = activity.title

        val timetable = ArrayList<String>()
        for ((key) in activity.timetable) {
            val text = format("%s: %s", key, activity.timetable.get(key))
            timetable.add(text)
        }

        list_view_timetable.adapter = TimetableItemAdapter(itemView.context, timetable)
    }

}
