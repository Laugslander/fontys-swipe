package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.card_result_teacher.view.*
import nl.fontys.smpt42_1.fontysswipe.adapter.TeacherAdapter
import nl.fontys.smpt42_1.fontysswipe.domain.result.TeacherResult

/**
 * @author SMPT42-1
 */
class TeacherResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: TeacherResult) = with(itemView) {
        text_view_teacher_title.text = result.title
        list_view_teachers.adapter = TeacherAdapter(itemView.context, result.teachers)
    }

}
