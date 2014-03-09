package com.quartz.qutilities.swing.table;

import java.util.Comparator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class NotComparator<T> implements Comparator<T>
{
    private Comparator<T> comparator;

    public NotComparator(Comparator<T> pSorter)
    {
        comparator = pSorter;
    }

    public int compare(T o1, T o2)
    {
        return -1 * comparator.compare(o1, o2);
    }
}
