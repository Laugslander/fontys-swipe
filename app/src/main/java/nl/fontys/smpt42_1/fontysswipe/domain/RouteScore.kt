package nl.fontys.smpt42_1.fontysswipe.domain

/**
 * @author SMPT42-1
 */
data class RouteScore(val name: String, val score: Int) : Comparable<RouteScore> {

    override fun compareTo(other: RouteScore) = -score.compareTo(other.score)

}
