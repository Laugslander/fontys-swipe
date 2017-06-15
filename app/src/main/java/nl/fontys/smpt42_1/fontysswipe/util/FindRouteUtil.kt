package nl.fontys.smpt42_1.fontysswipe.util

import nl.fontys.smpt42_1.fontysswipe.domain.Route

/**
 * @author SMPT42-1
 */
fun findRoute(abbreviation: String, routes: List<Route>) = routes.filter { it.abbreviation == abbreviation }[0]
