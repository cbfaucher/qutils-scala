package com.quartz.qutilities.swing.table;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ColumnModelDecl
{

    int columnIndex();
    String columnTitle();
    Class<? extends ICellComparator> cellComparator() default EqualsCellComparator.class;
    Class<? extends Comparator> cellSorter() default ComparableComparator.class;
    boolean writable() default false;

    static public class EqualsCellComparator implements ICellComparator<Object>
    {
        public boolean compare(Object pValue, Object pTo)
        {
            if (pValue== null && pTo == null) return true;
            if (pValue== null || pTo == null) return false;
            return pValue.equals(pTo);
        }
    }

    static public class RegexCellComparator implements ICellComparator<String>
    {
        private final Pattern pattern;

        public RegexCellComparator(String pFilter)
        {
            pattern = Pattern.compile(".*" + pFilter + ".*");
        }

        public boolean compare(String pValue, String pTo)
        {
            final Matcher matcher = pattern.matcher(pValue);
            return matcher.matches();
        }
    }
}
