package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_teacher.view.*
import nl.fontys.smpt42_1.fontysswipe.data.repository.TeacherRepository
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher

/**
 * @author SMPT42-1
 */
class TeacherListViewHolder(val itemView: View) {

    fun set(teacher: Teacher) = with(itemView) {
        TeacherRepository().getTeacherImageUri(teacher.image, { uri -> Picasso.with(context).load(uri).into(image_view_teacher) })
        text_view_teacher_name.text = teacher.name
        text_view_teacher_location.text = teacher.location
    }

}
