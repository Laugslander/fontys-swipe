package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.support.v4.content.ContextCompat.getColor
import android.view.View
import kotlinx.android.synthetic.main.card_result_statistic.view.*
import nl.fontys.smpt42_1.fontysswipe.R.color.*
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticResult
import org.eazegraph.lib.models.PieModel

/**
 * @author SMPT42-1
 */
class StatisticResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: StatisticResult) = with(itemView) {
        text_view_statistic_title.text = result.title

        val colors = arrayOf(colorFontysPrimary, colorFontysPrimaryDark, colorFontysSecondary, colorFontysBlend)
        var index = 0

        pie_chart.clearChart()

        result.routes.forEach { route ->
            if (index == colors.size - 1) index = 0 else index++ // Select a random colors index.
            pie_chart.addPieSlice(PieModel(String.format("ICT & %s", route.name), route.userPoints.toFloat(), getColor(context, colors[index])))
        }

        pie_chart.startAnimation()
    }

}
