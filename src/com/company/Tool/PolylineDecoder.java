package com.company.Tool;

import com.company.model.CoordGeo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Port to Java of Mark McClures Javascript PolylineEncoder :
 * http://facstaff.unca.edu/mcmcclur/GoogleMaps/EncodePolyline/decode.js
 */
public class PolylineDecoder {
    private static final double DEFAULT_PRECISION = 1E6;

    public static List<CoordGeo> decode(String encoded) {
        return decode(encoded, DEFAULT_PRECISION);
    }

    /**
     * Precision should be something like 1E5 or 1E6. For OSRM routes found precision was 1E6, not the original default
     * 1E5.
     *
     * @param encoded
     * @param precision
     * @return
     */
    public static List<CoordGeo> decode(String encoded, double precision) {
        List<CoordGeo> track = new ArrayList<CoordGeo>();
        try {
            int index = 0;
            int lat = 0, lng = 0;
            int len = encoded.length();
            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                CoordGeo p = new CoordGeo((float) (lat / precision), (float) (lng / precision));
                track.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return track;
    }

}
