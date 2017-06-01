package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.swipe_card.view.*
import nl.fontys.smpt42_1.fontysswipe.data.repository.QuestionRepository
import nl.fontys.smpt42_1.fontysswipe.domain.Question

/**
 * @author SMPT42-1
 */
class QuestionViewHolder(private val itemView: View) {

    fun set(question: Question) = with(itemView) {
        QuestionRepository().getQuestionImageUri(question.image, { uri -> Picasso.with(context).load(uri).fit().centerCrop().into(card_image) })
        card_text.text = question.text
    }

}
