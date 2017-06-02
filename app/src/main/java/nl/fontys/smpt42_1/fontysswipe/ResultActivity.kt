package nl.fontys.smpt42_1.fontysswipe

import android.os.Bundle
import android.support.v4.content.ContextCompat.getColor
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import nl.fontys.smpt42_1.fontysswipe.R.color.*
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeController
import org.eazegraph.lib.models.PieModel


/**
 * @author SMPT42-1
 */
class ResultActivity : AppCompatActivity() {

    val controller = SwipeController.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colors = arrayOf(colorFontysPrimary, colorFontysSecondary, colorFontysBlend)
        var currentColor = 0
        controller.topRoutes.forEach { route ->
            if (currentColor == colors.size - 1) currentColor = 0 else currentColor++ // Select a random color index.
            pie_chart.addPieSlice(PieModel(String.format("ICT & %s", route.name), route.score.toFloat(), getColor(this, colors[currentColor])))
        }
        pie_chart.startAnimation()
    }

}
