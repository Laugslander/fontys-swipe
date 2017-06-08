package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.card_result_teacher.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.result.TeacherResult

/**
 * @author Robin Laugs
 */
class TeacherResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: TeacherResult) = with(itemView) {
        text_view_teacher_title.text = result.title
    }

}
