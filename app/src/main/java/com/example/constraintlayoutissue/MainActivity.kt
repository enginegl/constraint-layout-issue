package com.example.constraintlayoutissue

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        renderItems(
            listOf(
                longText to longText,
                longText to longText,
                "key1" to "value1",
                "key2" to "value2",
            )
        )
    }

    private fun renderItems(items: List<Pair<String, String>>) {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val flow = findViewById<Flow>(R.id.flow)
        val views = items.map {
            LayoutInflater.from(this).inflate(R.layout.item_view, null, false).apply {
                findViewById<TextView>(R.id.propertyKey).text = it.first
                findViewById<TextView>(R.id.propertyValue).text = it.second
                id = ViewCompat.generateViewId()
            }
        }

        val referenceIds = IntArray(views.size)
        views.forEachIndexed { index, view ->
            referenceIds[index] = view.id
            constraintLayout.addView(
                view,
                ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    constrainedWidth = true
                    constrainedHeight = true
                }
            )
        }

        flow.referencedIds = referenceIds

        constraintLayout.post { constraintLayout.requestLayout() }
    }

    private val longText =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sapien velit, facilisis vel imperdiet vestibulum, viverra sed leo. Pellentesque consectetur, arcu pulvinar pellentesque luctus, odio neque aliquet leo, et vestibulum orci leo sit amet est. Vivamus efficitur nunc est, aliquet scelerisque velit sagittis eget. Vivamus cursus turpis eget magna faucibus, at eleifend nunc dictum. In sed orci sem. Donec nisi leo, luctus vitae blandit vitae, ornare id dolor. Nulla facilisi. Proin dapibus tellus ac commodo iaculis. Nulla ultricies gravida justo non pharetra. Nulla ut tellus venenatis, tristique neque at, tempus nisl.\n" +
                "\n" +
                "Vivamus viverra sapien vel neque vehicula rhoncus pulvinar sit amet sapien. Mauris in urna id sapien sodales commodo. Nam interdum eget felis eu egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nullam id tellus in arcu porttitor vehicula sit amet a nulla. Fusce lacus metus, condimentum nec rhoncus in, sollicitudin quis mauris. Proin malesuada risus lacus, sed ultricies tellus venenatis interdum. Nunc iaculis venenatis dolor vel maximus. Proin mauris risus, hendrerit vel urna et, rhoncus mattis ex. Ut in tempus quam, eu dignissim dolor.\n" +
                "\n" +
                "Integer fringilla, nulla interdum finibus pulvinar, ligula ante ornare ante, vitae porttitor nisi mi sit amet enim. Fusce venenatis ante at velit lacinia, vitae cursus tellus eleifend. Maecenas molestie, augue id consectetur eleifend, augue risus finibus metus, a iaculis felis metus eu sapien. Curabitur vulputate lectus id nunc sagittis, ut consequat massa semper. Sed rutrum dapibus ipsum iaculis porta. Suspendisse gravida ante id dictum tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla mattis vestibulum nulla. Donec tellus neque, placerat eu quam eget, lobortis dapibus massa. Donec pharetra sapien at mi iaculis mattis. Sed rhoncus pharetra lorem, dapibus maximus dolor pharetra ornare. Aenean vitae malesuada velit. Aliquam vulputate nisl dui, pretium scelerisque mi."
}
