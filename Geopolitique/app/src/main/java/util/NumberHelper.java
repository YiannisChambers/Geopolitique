/*
 * Copyright (C) 2016 Yiannis Chambers
 * Geopolitique
 */
package util;

/**
 * Number helper class to give the worded version of big
 * numbers like GDP, population and income taxes.
 */
public class NumberHelper {
    public static String getWordedVersion(double number){
        double trillion = 1000000000000f;
        double billion = 1000000000f;
        double million = 1000000;
        double thousand = 1000f;

        int result = 0;
        result = (int)(number / trillion);
        if(result > 0) {
            return Math.round(number / trillion) + " trillion";
        }

        result = (int)(number / billion);
        if(result > 0) {
            return Math.round(number / billion) + " billion";
        }

        result = (int)(number / million);
        if(result > 0) {
            return Math.round(number / million) + " million";
        }

        result = (int)(number / thousand);
        if(result > 0) {
            return Math.round(number / thousand) + " thousand";
        }

        return Math.round(number) + "";
    }
}
