package com.quartz.qutilities.swing.table;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public interface ICellSorter<T>
{
    int compare(T pThis, T pOther);
}
