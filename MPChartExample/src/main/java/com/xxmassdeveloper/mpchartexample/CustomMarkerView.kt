package com.xxmassdeveloper.mpchartexample

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.custom_marker_view.view.*

class CustomMarkerView(context: Context, layoutResource: Int) :
    MarkerView(context, layoutResource) {

    override fun refreshContent(
        e: Entry,
        highlight: Highlight?
    ) {
        if (e is CandleEntry) {
            tvContent!!.text = Utils.formatNumber(
                e.high,
                0,
                true
            )
        } else {
            tvContent!!.text = e.y.toString() + "%"
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF? {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}