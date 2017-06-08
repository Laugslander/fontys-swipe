package nl.fontys.smpt42_1.fontysswipe.controller

import nl.fontys.smpt42_1.fontysswipe.domain.Route

/**
 * Created by Merik on 08/06/2017.
 */

public fun findRoute(name : String, routes : ArrayList<Route>) = routes.filter{it.name == name}[0]

