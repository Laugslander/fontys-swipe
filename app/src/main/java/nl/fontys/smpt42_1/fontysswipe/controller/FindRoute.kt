package nl.fontys.smpt42_1.fontysswipe.controller

import nl.fontys.smpt42_1.fontysswipe.domain.Route

/**
 * @author SMPT42-1
 */
fun findRoute(name: String, routes: ArrayList<Route>) = routes.filter { it.name == name }[0]

