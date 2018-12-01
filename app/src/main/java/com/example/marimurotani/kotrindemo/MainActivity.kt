package com.example.marimurotani.kotrindemo

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import com.anychart.data.Set;
import com.anychart.enums.Anchor

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //  Pie Chart Example
        val pie = AnyChart.pie()
        val data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("John", 10000))
        data.add(ValueDataEntry("Jake", 12000))
        data.add(ValueDataEntry("Peter", 18000))
        pie.data(data)

        val anyChartView1 = findViewById<View>(R.id.pie_chart_view) as AnyChartView
        anyChartView1.setChart(pie)

        //  Line Chart Example
        val anyChartView2 = findViewById<AnyChartView>(R.id.line_chart_view)
        anyChartView2.setProgressBar(findViewById(R.id.progress_bar))

        val cartesian = AnyChart.line()
        cartesian.animation(true)
        cartesian.padding(10.0, 20.0, 5.0, 20.0)
        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true)
            // TODO ystroke
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.")
        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData = ArrayList<DataEntry>()
        seriesData.add(CustomDataEntry("1986", 3.6, 2.3, 2.8,0 ))
        seriesData.add(CustomDataEntry("1987", 7.1, 4.0, 4.1,0 ))
        seriesData.add(CustomDataEntry("1988", 8.5, 6.2, 5.1,0 ))
        seriesData.add(CustomDataEntry("1989", 9.2, 11.8, 6.5,0 ))
        seriesData.add(CustomDataEntry("1990", 10.1, 13.0, 12.5,0 ))
        seriesData.add(CustomDataEntry("1991", 11.6, 13.9, 18.0,0 ))
        seriesData.add(CustomDataEntry("1992", 16.4, 18.0, 21.0,0 ))
        seriesData.add(CustomDataEntry("1993", 18.0, 23.3, 20.3,0 ))
        seriesData.add(CustomDataEntry("1994", 13.2, 24.7, 19.2,0 ))
        seriesData.add(CustomDataEntry("1995", 12.0, 18.0, 14.4,0 ))
        seriesData.add(CustomDataEntry("1996", 3.2, 15.1, 9.2,0 ))
        seriesData.add(CustomDataEntry("1997", 4.1, 11.3, 5.9,0 ))
        seriesData.add(CustomDataEntry("1998", 6.3, 14.2, 5.2,0 ))
        seriesData.add(CustomDataEntry("1999", 9.4, 13.7, 4.7,0 ))
        seriesData.add(CustomDataEntry("2000", 11.5, 9.9, 4.2,0 ))
        seriesData.add(CustomDataEntry("2001", 13.5, 12.1, 1.2,0 ))
        seriesData.add(CustomDataEntry("2002", 14.8, 13.5, 5.4,0 ))
        seriesData.add(CustomDataEntry("2003", 16.6, 15.1, 6.3,0 ))
        seriesData.add(CustomDataEntry("2004", 18.1, 17.9, 8.9,0 ))
        seriesData.add(CustomDataEntry("2005", 17.0, 18.9, 10.1,0 ))
        seriesData.add(CustomDataEntry("2006", 16.6, 20.3, 11.5,0 ))
        seriesData.add(CustomDataEntry("2007", 14.1, 20.7, 12.2,0 ))
        seriesData.add(CustomDataEntry("2008", 15.7, 21.6, 10,0 ))
        seriesData.add(CustomDataEntry("2009", 12.0, 22.5, 8.9,0))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        val series1 = cartesian.line(series1Mapping)
        series1.name("Brandy")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series2 = cartesian.line(series2Mapping)
        series2.name("Whiskey")
        series2.hovered().markers().enabled(true)
        series2.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series2.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series3 = cartesian.line(series3Mapping)
        series3.name("Tequila")
        series3.hovered().markers().enabled(true)
        series3.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series3.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)

        anyChartView2.setChart(cartesian)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private inner class CustomDataEntry internal constructor(
        x: String,
        value: Number,
        value2: Number,
        value3: Number,
        value4: Number
    ) : ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
            setValue("value4", value4)
        }
    }
}
