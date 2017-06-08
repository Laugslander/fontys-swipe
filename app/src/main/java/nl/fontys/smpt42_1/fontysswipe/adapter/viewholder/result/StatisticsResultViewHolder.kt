package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.support.v4.content.ContextCompat.getColor
import android.view.View
import kotlinx.android.synthetic.main.card_statistics.view.*
import nl.fontys.smpt42_1.fontysswipe.R.color.*
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticsResult
import org.eazegraph.lib.models.PieModel

/**
 * @author SMPT42-1
 */
class StatisticsResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: StatisticsResult) = with(itemView) {
        text_view_statistics_title.text = result.title

        val colors = arrayOf(colorFontysPrimary, colorFontysPrimaryDark, colorFontysSecondary, colorFontysBlend)
        var index = 0

        result.routes.forEach { route ->
            if (index == colors.size - 1) index = 0 else index++ // Select a random colors index.
            pie_chart.addPieSlice(PieModel(String.format("ICT & %s", route.name), route.userPoints.toFloat(), getColor(context, colors[index])))
        }

        pie_chart.startAnimation()
    }

}
