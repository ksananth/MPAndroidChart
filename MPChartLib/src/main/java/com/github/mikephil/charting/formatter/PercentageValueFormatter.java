package com.github.mikephil.charting.formatter;

public class PercentageValueFormatter extends ValueFormatter {

    protected String mFormatter;

    public PercentageValueFormatter(String formatter) {
        mFormatter = formatter;
    }


    @Override
    public String getFormattedValue(float value) {
        int round = Math.round(value);
        if (isDivisibleBy50(round)) {
            return round + mFormatter;
        }
        return "";
    }

    public boolean isDivisibleBy50(int value) {
        if (value % 50 == 0) {
            return true;
        }
        return false;
    }
}
