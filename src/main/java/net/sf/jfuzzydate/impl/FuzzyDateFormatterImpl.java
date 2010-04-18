package net.sf.jfuzzydate.impl;

import java.util.Date;
import java.util.Locale;

import net.sf.jfuzzydate.FuzzingConfiguration;
import net.sf.jfuzzydate.FuzzingStrength;
import net.sf.jfuzzydate.FuzzyDateFormatter;

/**
 * Basic implementation for fuzzy date formatting.
 * 
 * @author amaasch
 * 
 * @see FuzzyDateFormatter
 */
public class FuzzyDateFormatterImpl implements FuzzyDateFormatter {

	/**
	 * The fuzzing configuration.
	 */
	private final FuzzingConfiguration config;

	/**
	 * Creates a new FuzzyDateFormatterImpl object with the specified
	 * configuration.
	 * 
	 * @param config
	 *            the given configuration.
	 */
	public FuzzyDateFormatterImpl(final FuzzingConfiguration config) {
		this.config = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#format(java.util.Date)
	 */
	public String format(final Date date) {
		return format(date, Locale.getDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#format(java.util.Date,
	 * java.util.Locale)
	 */
	public String format(final Date date, final Locale locale) {
		return formatDate(date.getTime() - new Date().getTime(),
				FuzzingStrength.NORMAL, locale);
	}

	/**
	 * Formats a point in time. Not yet implemented.
	 * 
	 * @param distance
	 *            todo
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * @param locale
	 *            the locale for formatting.
	 * 
	 * @return a string representing a point in time in relation to the current
	 *         time.
	 */
	private String formatDate(final long distance,
			final FuzzingStrength strength, final Locale locale) {
		return "not implemented";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDistance(java.util.Date)
	 */
	public String formatDistance(final Date date) {
		return format(date, Locale.getDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDistance(java.util.Date,
	 * java.util.Locale)
	 */
	public String formatDistance(final Date date, final Locale locale) {
		return formatDistance(date.getTime() - new Date().getTime(),
				FuzzingStrength.NORMAL, locale);
	}

	/**
	 * Formats a distance.
	 * 
	 * @param distanceSec
	 *            a relative distance (number of milliseconds). Negative
	 *            distances are assumed to be past values.
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * @param locale
	 *            the locale for formatting.
	 * 
	 * @return a string representing a readable distance.
	 */
	private String formatDistance(final long distanceSec,
			final FuzzingStrength strength, final Locale locale) {
		String pattern;

		if (distanceSec <= 0) {
			pattern = "distance.past.pattern";
		} else {
			pattern = "distance.future.pattern";
		}

		final long absDistance = Math.abs(distanceSec);

		for (final Range range : config.getDistanceRanges(strength)) {
			if (absDistance < range.getUpperBound()) {
				if (range.hasDivisor()) {
					final int parameter = Math.round(absDistance
							/ range.getParameterDivisor());

					return config.getFuzzyString(pattern, locale, config
							.getFuzzyString(range, locale, Integer
									.valueOf(parameter)));
				}

				return config.getFuzzyString(pattern, locale, config
						.getFuzzyString(range, locale));
			}
		}

		return config.getFuzzyString(pattern, locale, config.getFuzzyString(
				Range.ETERNAL, locale));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(java.util.Date)
	 */
	public String formatDuration(final Date relative) {
		return formatDuration(relative, Locale.getDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(java.util.Date,
	 * java.util.Date, java.util.Locale)
	 */
	public String formatDuration(final Date from, final Date to,
			final Locale locale) {
		return formatDuration(to.getTime() - from.getTime(), locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(java.util.Date,
	 * java.util.Locale)
	 */
	public String formatDuration(final Date relative, final Locale locale) {
		return formatDuration(new Date().getTime() - relative.getTime(), locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(long)
	 */
	public String formatDuration(final long milliSeconds) {
		return formatDuration(milliSeconds, Locale.getDefault());
	}

	/**
	 * Formats a duration.
	 * 
	 * @param milliSeconds
	 *            the duration defined by a number of milliseconds. Negative
	 *            distances are assumed to be past values.
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * @param locale
	 *            the locale for formatting.
	 * 
	 * @return a string representing a readable duration.
	 */
	private String formatDuration(final long milliSeconds,
			final FuzzingStrength strength, final Locale locale) {
		for (final Range range : config.getDurationRanges(strength)) {
			if (milliSeconds < range.getUpperBound()) {
				if (range.hasDivisor()) {
					final int parameter = Math.round(milliSeconds
							/ range.getParameterDivisor());

					return config.getFuzzyString(range, locale, Integer
							.valueOf(parameter));
				}

				return config.getFuzzyString(range, locale);
			}
		}

		return config.getFuzzyString(Range.ETERNAL, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(long,
	 * java.util.Locale)
	 */
	public String formatDuration(final long milliSeconds, final Locale locale) {
		return formatDuration(milliSeconds, FuzzingStrength.NORMAL, locale);
	}
}
