package dlei.forkme.helpers;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class providing custom date utils.
 */
public class DateHelper {

    /**
     * Formats date along with calculations to conform to what GitHub's API is expecting.
     * @param timeframe a time frame from <string-array name="timeframe_array">
     * @return formatted date appceted by GitHub's Search API.
     */
    public static String getFormattedQueryDate(String timeframe) {
        Calendar cal = new GregorianCalendar();
        int minusDays = 0;

        if (timeframe.equals("1 Week")) {
            minusDays = 7;
        } else if (timeframe.equals("1 Month")) {
            minusDays = 30;
        } else if (timeframe.equals("2 Months")) {
            minusDays = 30 * 2;
        } else if (timeframe.equals("3 Months")) {
            minusDays = 30 * 3;
        } else if (timeframe.equals("6 Months")) {
            minusDays = 30 * 3 * 2;
        } else if (timeframe.equals("2 Months")) {
            minusDays = 365;
        } else if (timeframe.equals("2 Months")) {
            minusDays = 365 * 3;  // Defaults to 3 years backwards.
        }
        cal.add(Calendar.DAY_OF_MONTH, -minusDays);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedQueryDate = sdf.format(date);
        Log.d("DateHelper: ", "getFormattedQueryDate(): " + formattedQueryDate);
        return formattedQueryDate;
    }
}
