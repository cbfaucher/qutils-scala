package com.quartz.qutilities.swing.table;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * INSERT YOUR COMMENT HERE....
*
* @author Christian
* @since Quartz...
*/
public class AnnotatedTableModelColumn<T>
{
    final public int columnIndex;
    final public String columnName;
    final public IColumnGetter columnGetter;
    final public Class<? extends ICellComparator<T>> cellComparatorClass;
    final public Class<? extends Comparator<T>> cellSorterClass;

    AnnotatedTableModelColumn(String pColumnName, IColumnGetter pGetter, ColumnModelDecl pAnnotation)
    {
        columnIndex = pAnnotation.columnIndex();
        cellComparatorClass = (Class<? extends ICellComparator<T>>) pAnnotation.cellComparator();
        cellSorterClass = (Class<? extends Comparator<T>>) pAnnotation.cellSorter();
        columnName = pColumnName;
        columnGetter = pGetter;
    }

    public Object get(T pItem)
    {
        return columnGetter.get(pItem);
    }

    public boolean isEquals(T pValue, Object pFilter)
    {
        if (pValue == null && pFilter == null) return true;
        if (pValue == null || pFilter == null) return false;

        return pValue.equals(pFilter);
    }

    public Comparator<T> createSorter() throws TableModelException
    {
        try
        {
            final Constructor ctr = cellSorterClass.getConstructor(AnnotatedTableModelColumn.class);
            return (Comparator<T>) ctr.newInstance(this);
        }
        catch (InstantiationException e)
        {
            throw new TableModelException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TableModelException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new TableModelException(e.getTargetException());
        }
        catch (NoSuchMethodException e)
        {
            throw new TableModelException(e);
        }
    }

    public ICellComparator<T> createCellComparator(T pFilter) throws TableModelException
    {
        try
        {
            final Constructor ctor = cellComparatorClass.getConstructor(pFilter.getClass());
            return (ICellComparator<T>) ctor.newInstance(pFilter);
        }
        catch (NoSuchMethodException e)
        {
            try
            {
                return cellComparatorClass.newInstance();
            }
            catch (InstantiationException e1)
            {
                throw new TableModelException(e1);
            }
            catch (IllegalAccessException e1)
            {
                throw new TableModelException(e1);
            }
        }
        catch (InvocationTargetException e)
        {
            throw new TableModelException(e.getTargetException());
        }
        catch (IllegalAccessException e)
        {
            throw new TableModelException(e);
        }
        catch (InstantiationException e)
        {
            throw new TableModelException(e);
        }
    }
}
