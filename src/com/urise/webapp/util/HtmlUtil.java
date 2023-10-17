package com.urise.webapp.util;

import com.urise.webapp.model.Period;

public class HtmlUtil {
    public static String formatDates(Period period) {
        return DateUtil.format(period.getStartDate()) + " - " + DateUtil.format(period.getEndDate());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
