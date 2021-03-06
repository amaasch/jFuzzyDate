package net.sf.jfuzzydate.i18n;

import net.sf.jfuzzydate.FuzzyStringProvider;

import java.util.Locale;


/**
 * A internationalized string based selector for locale specific plural rules. Looks up a key in a
 * internationalization repository and uses its value to get a {@link Locale} specific rule by looking up a
 * {@link PluralRule} by name.
 *
 * @author amaasch
 *
 * @see PluralRule#valueOf(String)
 */
public class I18nStringLookupRuleSelector implements PluralRuleSelector {
    //~ Instance fields --------------------------------------------------------------------------------------

    /**
     * The string builder used to look up locale specific resource bundle entries.
     */
    private FuzzyStringProvider fuzzyStringProvider;

    /**
     * The key to the rule values.
     */
    private String ruleKey;

    //~ Constructors -----------------------------------------------------------------------------------------

/**
     * Creates a new internationalization repository based plural rule selector.
     * 
     * @param stringBuilder the fuzzy string builder instance to look up the 
     *        locale specific plural rule.
     * @param key the key which references the plural rule.
     */
    public I18nStringLookupRuleSelector(final FuzzyStringProvider stringBuilder, final String key) {
        this.fuzzyStringProvider = stringBuilder;
        this.ruleKey = key;
    }

    //~ Methods ----------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     *
     * @see
     * net.sf.jfuzzydate.i18n.PluralRuleSelector#selectRuleFor(java.util.Locale)
     */
    public PluralRule selectRuleFor(final Locale locale) {
        final String rule = fuzzyStringProvider.getString(ruleKey, locale);

        return PluralRule.valueOf(rule);
    }
}
