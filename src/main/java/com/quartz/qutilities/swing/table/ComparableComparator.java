package com.quartz.qutilities.swing.table;

import java.util.Comparator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class ComparableComparator<T> implements Comparator<T>
{
    final private AnnotatedTableModelColumn<T> column;

    public ComparableComparator(AnnotatedTableModelColumn<T> pColumn)
    {
        column = pColumn;
    }

    public int compare(T pValue1, T pValue2)
    {
        final Object o1 = column.get(pValue1);
        final Object o2 = column.get(pValue2);
        final Comparable c1 = (Comparable) o1;
        return c1.compareTo(o2);
    }
}
