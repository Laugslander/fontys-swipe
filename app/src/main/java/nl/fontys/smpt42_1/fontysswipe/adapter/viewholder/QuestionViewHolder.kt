package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.swipe_card.view.*
import nl.fontys.smpt42_1.fontysswipe.R
import nl.fontys.smpt42_1.fontysswipe.domain.Question

/**
 * @author SMPT42-1
 */
class QuestionViewHolder(private val itemView: View) {

    fun set(question: Question) = with(itemView) {
        Picasso.with(context).load(R.drawable.placeholder_image).fit().centerCrop().into(card_image) // TODO replace placeholder image
        card_text.text = question.text
    }

}
