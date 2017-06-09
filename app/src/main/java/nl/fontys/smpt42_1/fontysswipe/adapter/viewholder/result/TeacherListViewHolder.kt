package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.list_item_teacher.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher

/**
 * @author Robin Laugs
 */
class TeacherListViewHolder(val itemView: View) {

    fun set(teacher: Teacher) = with(itemView) {
        text_view_teacher_name.text = teacher.name
        // TODO show teacher image and location
    }

}
