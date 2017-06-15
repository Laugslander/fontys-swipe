package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.list_item_timetable.view.*

/**
 * @author SMPT42-1
 */
class TimetableItemListViewHolder(val itemView: View) {

    fun set(timetableItem: String) = with(itemView) {
        text_view_timetable_item.text = timetableItem
    }

}
