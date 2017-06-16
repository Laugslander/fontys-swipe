package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.support.v4.content.ContextCompat.getColor
import android.view.View
import kotlinx.android.synthetic.main.card_result_statistic.view.*
import nl.fontys.smpt42_1.fontysswipe.R.color.*
import nl.fontys.smpt42_1.fontysswipe.adapter.RouteLegendItemAdapter
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticResult
import org.eazegraph.lib.models.BarModel

/**
 * @author SMPT42-1
 */
class StatisticResultViewHolder(itemView: View) : ResultViewHolder(itemView) {

    fun set(result: StatisticResult) = with(itemView) {
        text_view_statistic_title.text = result.title

        bar_chart.clearChart()

        val colors = arrayOf(colorFontysPrimary, colorFontysPrimaryDark, colorFontysSecondary, colorFontysBlend)
        var index = 0

        val mainRoutes = result.routes
        mainRoutes.sort()

        var totalPercentage = 0.0F
        mainRoutes.forEach { route -> totalPercentage += route.percentage }

        mainRoutes.forEach { route ->
            if (index == colors.size - 1) index = 0 else index++ // Select a random colors index.
            bar_chart.addBar(BarModel(route.abbreviation, route.percentage / totalPercentage * 100.0F, getColor(context, colors[index])))
        }

        list_view_route_legend.adapter = RouteLegendItemAdapter(context, mainRoutes)
        println()
    }


}
