package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result

import android.view.View
import kotlinx.android.synthetic.main.list_item_route_legend.view.*
import nl.fontys.smpt42_1.fontysswipe.domain.Route

/**
 * @author SMPT42-1
 */
class RouteLegendItemListViewHolder(val itemView: View) {

    fun set(route: Route) = with(itemView) {
        text_view_route_legend_item.text = String.format("%s = %s", route.abbreviation, route.name)
    }

}
