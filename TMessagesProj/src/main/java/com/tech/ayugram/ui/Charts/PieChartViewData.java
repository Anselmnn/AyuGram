package com.tech.ayugram.ui.Charts;

import android.animation.Animator;

import com.tech.ayugram.ui.Charts.data.ChartData;
import com.tech.ayugram.ui.Charts.view_data.StackLinearViewData;

public class PieChartViewData extends StackLinearViewData {

    float selectionA;
    float drawingPart;
    Animator animator;

    public PieChartViewData(ChartData.Line line) {
        super(line);
    }
}
