package com.xxmassdeveloper.mpchartexample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.PercentageValueFormatter
import kotlinx.android.synthetic.main.activity_my_linechart.*

class MyLineChart : AppCompatActivity()/*, OnChartValueSelectedListener */{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_linechart)

        addLineChart(steppedLineChart, LineDataSet.Mode.STEPPED)
        addLineChart(lineChart, LineDataSet.Mode.LINEAR)
    }

    private fun addLineChart(lineChart: LineChart, mode: LineDataSet.Mode) {
        val lineEntries: List<Entry> = getEntries()
        val lineDataSet = LineDataSet(lineEntries, "")
        lineDataSet.mode = mode
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 18f

        // set the filled area
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillFormatter = IFillFormatter { _, _ ->
            lineChart.axisLeft.axisMinimum
        }
        // gradient
        val drawable = ContextCompat.getDrawable(this, R.drawable.chart_gradient)
        lineDataSet.fillDrawable = drawable

        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)
        lineDataSet.setCircleColor(ContextCompat.getColor(applicationContext, R.color.chartLine))
        lineDataSet.lineWidth = 2f
        lineDataSet.color = ContextCompat.getColor(applicationContext, R.color.chartLine)
        lineDataSet.circleRadius = 5f
        lineDataSet.circleHoleRadius = 2f

        val lineData = LineData(lineDataSet)

        // create marker to display box when values are selected
        val mv = CustomMarkerView(applicationContext, R.layout.chart_marker_view)

        // Set the marker to the chart
        mv.chartView = lineChart
        lineChart.marker = mv

        //X-Axis Style
        val xAxis: XAxis = lineChart.xAxis
        xAxis.setDrawLabels(false)
        xAxis.enableGridDashedLine(1f, 1f, 0f)

        //Y-Axis Style
        val yAxis: YAxis = lineChart.axisLeft
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisRight.isEnabled = false
        yAxis.enableGridDashedLine(2f, 10f, 0f)
        yAxis.axisMaximum = 100f
        yAxis.axisMinimum = 0f
        yAxis.labelCount = 10
        yAxis.granularity = 10f
        yAxis.textColor = Color.parseColor("#adb5bd")
        yAxis.textSize = 10f
        yAxis.valueFormatter = PercentageValueFormatter("%")

        // draw limit lines behind data instead of on top
        yAxis.setDrawLimitLinesBehindData(true)
        xAxis.setDrawLimitLinesBehindData(true)

        xAxis.setDrawAxisLine(false)
        yAxis.setDrawAxisLine(false)

        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)

        val description = Description()
        description.text = ""
        lineChart.description = description
        lineChart.legend.isEnabled = false
        lineChart.axisRight.setDrawLabels(false);
        lineChart.xAxis.setDrawLabels(false);

        lineChart.setScaleEnabled(false)

        lineChart.data = lineData
        lineChart.animateXY(300, 300)
        lineChart.setVisibleXRangeMaximum(20.0f)
        lineChart.moveViewToX(10f)
        lineChart.setHardwareAccelerationEnabled(false)


    }

    private fun getEntries(): List<Entry> {
        val lineEntries = mutableListOf<Entry>()
        lineEntries.add(Entry(1f, 70f))
        lineEntries.add(Entry(2f, 50f))
        lineEntries.add(Entry(3f, 40f))
        lineEntries.add(Entry(4f, 30f))
        lineEntries.add(Entry(5f, 20f))
        lineEntries.add(Entry(6f, 10f))
        lineEntries.add(Entry(7f, 0f))
        lineEntries.add(Entry(8f, 100f))
        lineEntries.add(Entry(9f, 90f))
        lineEntries.add(Entry(10f, 80f))
        lineEntries.add(Entry(11f, 70f))
        lineEntries.add(Entry(12f, 60f))
        lineEntries.add(Entry(13f, 50f))
        lineEntries.add(Entry(14f, 40f))
        lineEntries.add(Entry(15f, 30f))
        lineEntries.add(Entry(16f, 20f))
        lineEntries.add(Entry(17f, 10f))
        lineEntries.add(Entry(18f, 0f))
        lineEntries.add(Entry(19f, 100f))
        lineEntries.add(Entry(20f, 90f))
        lineEntries.add(Entry(21f, 80f))
        lineEntries.add(Entry(22f, 80f))
        lineEntries.add(Entry(24f, 40f))
        lineEntries.add(Entry(25f, 60f))
        lineEntries.add(Entry(26f, 60f))
        lineEntries.add(Entry(27f, 60f))
        lineEntries.add(Entry(28f, 50f))
        lineEntries.add(Entry(29f, 60f))
        lineEntries.add(Entry(30f, 30f))
        lineEntries.add(Entry(31f, 60f))
        lineEntries.add(Entry(32f, 77f))
        return lineEntries
    }
/*
    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.i("Entry selected", e.toString())
        Log.i("LOW HIGH", "low: " + steppedLineChart.lowestVisibleX + ", high: " + steppedLineChart.highestVisibleX)
        Log.i("MIN MAX", "xMin: " + steppedLineChart.xChartMin + ", xMax: " + steppedLineChart.xChartMax + ", yMin: " + steppedLineChart.yChartMin + ", yMax: " + steppedLineChart.yChartMax)

        mCurrentToast?.cancel()

        val res: String = java.lang.String.format(Locale.ENGLISH, "Item: %f; Value: %.2f", e!!.x, e.y)
        mCurrentToast = Toast.makeText(this, res, Toast.LENGTH_SHORT)
        mCurrentToast?.show()
    }*/
}