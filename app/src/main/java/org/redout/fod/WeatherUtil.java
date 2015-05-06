package org.redout.fod;

/**
 * Created by tom.cooper on 5/5/2015.
 */
public class WeatherUtil {
    public static Long convertKtoF(Double temp) {
        Double convertedTemp = (temp -273.15)*1.8 +32;
        return Math.round(convertedTemp);
    }
}
