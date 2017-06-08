package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.card_result_prize.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.result.PrizeResult

/**
 * @author SMPT42-1
 */
class PrizeResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: PrizeResult) = with(itemView) {
        text_view_prize_title.text = result.title
    }

}
