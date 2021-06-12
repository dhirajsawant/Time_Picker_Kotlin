package com.example.timepickerinkotlin



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create textView from XML file
        val txtView = TextView(this)
        // create TimePicker programmatically
        val timePicker = TimePicker(this)

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        timePicker.layoutParams = layoutParams
        timePicker.setOnTimeChangedListener { _, hour, minute ->
            var hour = hour
            var am_pm = ""

            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            if (txtView != null) {
                val hour = if (hour < 10) "0$hour" else hour
                val min = if (minute < 10) "0$minute" else minute
                // display format of time
                val msg = "Time is: $hour : $min $am_pm"
                txtView.text = msg
                txtView.visibility = ViewGroup.VISIBLE
            }
        }
        val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
        linearLayout?.addView(timePicker)
        linearLayout?.addView(txtView)
    }
}
