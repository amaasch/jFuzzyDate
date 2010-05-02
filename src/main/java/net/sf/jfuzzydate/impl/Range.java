package net.sf.jfuzzydate.impl;

/**
 * A date/time range mapping time values up to an upper bound to an externalized
 * string.
 * 
 * @author amaasch
 */
public class Range {

	/**
     * 
     */
	public static final Range ETERNAL = new Range(Long.MAX_VALUE,
			"distance.eternal");

	/**
     * 
     */
	private Long parameterDivisor;

	/**
	 * The key for the externalized string.
	 */
	private final String bundleKey;

	/**
     * 
     */
	private final long upperBound;

	/**
	 * Creates a new Range object with an upper binding and a reference to an
	 * externalized string.
	 * 
	 * @param upperBound
	 * @param bundleKey
	 */
	public Range(final long upperBound, final String bundleKey) {
		this.upperBound = upperBound * 1000;
		this.bundleKey = bundleKey;
	}

	/**
	 * Creates a new Range object with an upper binding and a reference to an
	 * externalized string. This constructor method also sets a divisor, used to
	 * define the calculation of number values included in fuzzy strings.
	 * 
	 * @param upperBound
	 * @param bundleKey
	 * @param parameterDivisor
	 */
	public Range(final long upperBound, final String bundleKey,
			final long parameterDivisor) {
		this.upperBound = upperBound * 1000;
		this.bundleKey = bundleKey;
		this.parameterDivisor = parameterDivisor * 1000;
	}

	/**
	 * 
	 * 
	 * @return the bundleKey
	 */
	public String getBundleKey() {
		return bundleKey;
	}

	/**
	 * 
	 * 
	 * @return the parameterDivisor
	 */
	public long getParameterDivisor() {
		return parameterDivisor;
	}

	/**
	 * 
	 * 
	 * @return the upperBound
	 */
	public long getUpperBound() {
		return upperBound;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean hasDivisor() {
		return parameterDivisor != null;
	}
}
