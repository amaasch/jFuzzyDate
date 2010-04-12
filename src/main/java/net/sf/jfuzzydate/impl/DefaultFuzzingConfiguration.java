package net.sf.jfuzzydate.impl;

import net.sf.jfuzzydate.FuzzingConfiguration;
import net.sf.jfuzzydate.FuzzingStrength;
import net.sf.jfuzzydate.i18n.FuzzyStrings;

import java.util.Locale;


/**
 * 
 *
 * @author ma�
 */
public final class DefaultFuzzingConfiguration implements FuzzingConfiguration {
    //~ Static fields/initializers ---------------------------------------------

    /**
     * 
     */
    private static DefaultFuzzingConfiguration instance;

    /**
     * 
     */
    private static final String FUZZY_STRING_BUNDLE = "net.sf.jfuzzydate.i18n.jFuzzyDate";

    //~ Instance fields --------------------------------------------------------

    /**
     * 
     */
    private final Range[] dist_normal;

    /**
     * 
     */
    private final Range[] dur_normal;

    /**
     * 
     */
    private FuzzyStrings fuzzyStrings = new FuzzyStrings(FUZZY_STRING_BUNDLE);

    //~ Constructors -----------------------------------------------------------

/**
     * Creates a new DefaultFuzzingConfiguration object.
     */
    private DefaultFuzzingConfiguration() {
        final int min = 60;
        final int hour = 60 * min;
        final int day = 24 * hour;
        final int week = 7 * day;
        final int month = 30 * day;

        dur_normal = new Range[] {
                         new Range(80, "duration.minute.1"),
                         new Range(140, "duration.minute.2"),
                         new Range(40 * min, "duration.minute.x", min),
                         new Range(90 * min, "duration.hour.1"),
                         new Range(150 * min, "duration.hour.2"),
                         new Range(day, "duration.hour.x", day),
                         new Range(36 * hour, "duration.day.1"),
                         new Range(60 * hour, "duration.day.2"),
                         new Range(week, "duration.day.x", day),
                         new Range(11 * day, "duration.week.1"),
                         new Range(18 * day, "duration.week.2"),
                         new Range(4 * week, "duration.week.x", week),
                         new Range(45 * day, "duration.month.1"),
                         new Range(75 * day, "duration.month.2"),
                         new Range(300 * day, "duration.month.x", month),
                         new Range(Long.MAX_VALUE, "duration.eternal")
                     };

        dist_normal = new Range[] {
                          new Range(80, "distance.minute.1"),
                          new Range(140, "distance.minute.2"),
                          new Range(40 * min, "distance.minute.x", min),
                          new Range(90 * min, "distance.hour.1"),
                          new Range(150 * min, "distance.hour.2"),
                          new Range(day, "distance.hour.x", day),
                          new Range(36 * hour, "distance.day.1"),
                          new Range(60 * hour, "distance.day.2"),
                          new Range(week, "distance.day.x", day),
                          new Range(11 * day, "distance.week.1"),
                          new Range(18 * day, "distance.week.2"),
                          new Range(4 * week, "distance.week.x", week),
                          new Range(45 * day, "distance.month.1"),
                          new Range(75 * day, "distance.month.2"),
                          new Range(300 * day, "distance.month.x", month),
                          new Range(Long.MAX_VALUE, "duration.eternal")
                      };
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * 
     *
     * @return 
     */
    public static DefaultFuzzingConfiguration getInstance() {
        if (instance == null) {
            instance = new DefaultFuzzingConfiguration();
        }

        return instance;
    }

    /* (non-Javadoc)
     * @see net.sf.jfuzzydate.FuzzingConfiguration#getDistanceIntervals(net.sf.jfuzzydate.FuzzingStrength)
     */
    public Range[] getDistanceIntervals(FuzzingStrength strenght) {
        final Range[] ranges;

        switch (strenght) {
            case NORMAL :
                ranges = dist_normal;

                break;

            case STRONG :
                //TODO
                ranges = dist_normal;

                break;

            case EXTREME :
                //TODO
                ranges = dist_normal;

                break;

            default :
                ranges = dist_normal;
        }

        return ranges;
    }

    /* (non-Javadoc)
     * @see net.sf.jfuzzydate.FuzzingConfiguration#getDurationIntervals(net.sf.jfuzzydate.FuzzingStrength)
     */
    public Range[] getDurationIntervals(FuzzingStrength strenght) {
        final Range[] ranges;

        switch (strenght) {
            case NORMAL :
                ranges = dur_normal;

                break;

            case STRONG :
                //TODO
                ranges = dur_normal;

                break;

            case EXTREME :
                //TODO
                ranges = dur_normal;

                break;

            default :
                ranges = dur_normal;
        }

        return ranges;
    }

    /* (non-Javadoc)
     * @see net.sf.jfuzzydate.FuzzingConfiguration#getFuzzyString(net.sf.jfuzzydate.impl.Range, java.util.Locale, java.lang.Object[])
     */
    public String getFuzzyString(Range range, Locale locale, Object... params) {
        return fuzzyStrings.getString(range.getBundleKey(), locale, params);
    }

    /* (non-Javadoc)
     * @see net.sf.jfuzzydate.FuzzingConfiguration#getFuzzyString(java.lang.String, java.util.Locale, java.lang.Object[])
     */
    public String getFuzzyString(String pattern, Locale locale, Object... params) {
        return fuzzyStrings.getString(pattern, locale, params);
    }
}
