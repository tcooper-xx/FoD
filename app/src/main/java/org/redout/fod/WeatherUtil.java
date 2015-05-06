package org.redout.fod;

import org.redout.fod.openWeatherMap.Wind;

/**
 * Created by tom.cooper on 5/5/2015.
 */
public class WeatherUtil {
    private static final double MPS_TO_MPH = 2.23694;

    public static Long convertKtoF(Double temp) {
        Double convertedTemp = (temp -273.15)*1.8 +32;
        return Math.round(convertedTemp);
    }

    public static String formatWind(Wind w) {
        String fWind = new String ();
        fWind = convertDegreesToDirection(w.getDeg()) + "(" + w.getDeg() + ")" + " @ " +convertMpsToMph(w.getSpeed()) + "mph ";
        return fWind;
    }

    private static long convertMpsToMph(int s) {
        double speed = s *  MPS_TO_MPH;
        return Math.round(speed);
    }

    private static String convertDegreesToDirection(int deg) {
        double val = Math.floor((deg / 22.5) + 0.5);
        String[] dir = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        return dir[(int)Math.round(val % 16)];
    }
}
