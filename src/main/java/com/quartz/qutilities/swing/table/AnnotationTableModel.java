package com.quartz.qutilities.swing.table;

import com.quartz.qutilities.lang.ClassUtilities;
import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class AnnotationTableModel<T> extends AbstractTableModel
{
    static private final ILog LOG = LogManager.getLogger(AnnotationTableModel.class);

    final private Map<Integer, AnnotatedTableModelColumn<T>> byColumns = new HashMap<Integer, AnnotatedTableModelColumn<T>>();
    final private Class<T> annotedClass;
    final private Class itemsClass;

    private List<T> allItems = new ArrayList<T>();

    private List<T> displayedItems = new ArrayList<T>();

    private Class<? extends JPopupMenu> popupMenuClass;
    final private Comparator<T> defaultComparator;
    private JPanel owningPanel;
    private Comparator<T> comparator;

    public AnnotationTableModel(JPanel pOwningPanel, Class pAnnotedClass, List pItems) throws TableModelException
    {
        this(pOwningPanel, pAnnotedClass, pAnnotedClass, pItems);
    }

    public AnnotationTableModel(JPanel pOwningPanel, Class pAnnotedClass, Class pItemsClass, List pItems) throws TableModelException
    {
        owningPanel = pOwningPanel;
        annotedClass = pAnnotedClass;
        itemsClass = pItemsClass;

        setContent(pItems);

        defaultComparator = buildTableModel();
        buildColumnNames();
    }

    public AnnotationTableModel(Class<T> pAnnotedClass) throws TableModelException
    {
        this(null, pAnnotedClass, new ArrayList());
    }

    public AnnotationTableModel(Class<T> pAnnotedClass, Class pItemsClass) throws TableModelException
    {
        this(null, pAnnotedClass, pItemsClass, new ArrayList());
    }

    public void setOwningPanel(JPanel pOwningPanel)
    {
        if (this.owningPanel != null) throw new IllegalStateException("Owning Panel already set");
        owningPanel = pOwningPanel;
    }

    private Comparator<T> buildTableModel() throws TableModelException
    {
        try
        {
            final TableModelDecl anno = annotedClass.getAnnotation(TableModelDecl.class);
            if (null == anno) throw new IllegalArgumentException("Class " + annotedClass.getName() + " has no @TableModelDecl annotation");

            comparator = anno.defaultComparatorClass().newInstance();

            popupMenuClass = (anno.popupMenuClass() != TableModelDecl.NullPopupMenu.class ? anno.popupMenuClass() : null);

            return comparator;
        }
        catch (InstantiationException e)
        {
            throw new TableModelException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TableModelException(e);
        }
    }

    private void buildColumnNames() throws TableModelException
    {
        for (final Field f : annotedClass.getDeclaredFields())
        {
            final ColumnModelDecl annotation = f.getAnnotation(ColumnModelDecl.class);
            if (annotation != null)
            {
                processColumnModelDecl(annotation, createGetter(f), f.getName());
            }
        }

        for (final Method m :  ClassUtilities.findGetters(annotedClass))
        {
            final ColumnModelDecl annotation = m.getAnnotation(ColumnModelDecl.class);
            if (annotation != null)
            {
                processColumnModelDecl(annotation, createGetter(m), m.getName().substring(3));
            }
        }
    }

    private GetMethodColumnGetter createGetter(Method m) throws TableModelException
    {
        try
        {
            if (annotedClass == itemsClass) return new GetMethodColumnGetter(m);

            return new GetMethodColumnGetter(itemsClass.getMethod(m.getName()));
        }
        catch (NoSuchMethodException e)
        {
            throw new TableModelException(e);
        }
    }

    private FieldColumnGetter createGetter(Field f) throws TableModelException
    {

        try
        {
            if (annotedClass == itemsClass)
            {
                return new FieldColumnGetter(f);
            }
            else
            {
                return new FieldColumnGetter(itemsClass.getField(f.getName()));
            }
        }
        catch (NoSuchFieldException e)
        {
            throw new TableModelException(e);
        }
    }

    private void processColumnModelDecl(ColumnModelDecl pAnnotation, final IColumnGetter pColumnGetter, final String pDefaultName)
    {
        final int columnIndex = pAnnotation.columnIndex();
        final String columnName = StringUtils.isNotBlank(pAnnotation.columnTitle()) ? pAnnotation.columnTitle() : pDefaultName;
        byColumns.put(columnIndex, new AnnotatedTableModelColumn<T>(columnName, pColumnGetter, pAnnotation));
    }

    public String getColumnName(int column)
    {
        final AnnotatedTableModelColumn cell = byColumns.get(column);

        if (cell != null) return cell.columnName;

        return super.getColumnName(column);    
    }

    public int getRowCount()
    {
        return displayedItems.size();
    }

    public int getColumnCount()
    {
        return Collections.max(byColumns.keySet()) + 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        final T item = displayedItems.get(rowIndex);
        final AnnotatedTableModelColumn<T> cell = byColumns.get(columnIndex);
        if (cell == null) return "N/A";
        return cell.get(item);
    }

    public void setContent(List<T> pContent)
    {
        //  clear old rows.
        fireTableRowsDeleted(0, displayedItems.size());

        allItems = pContent;

        displayedItems.clear();
        displayedItems.addAll(allItems);

        sort();

        fireTableRowsInserted(0, displayedItems.size());
    }

    public void sort(Comparator<T> pComparator)
    {
        comparator = (pComparator != null ? pComparator : defaultComparator);
        if (pComparator != null)
        {
            sort();
        }
    }

    private void sort()
    {
        Collections.sort(displayedItems, comparator);
    }

    public JPopupMenu getPopupMenuFor(int pIdx, int pColumnIdx) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
//        final AnnotatedTableModelColumn<T> cell = byColumns.get(pColumnIdx);
//        if (cell == null) return null;
        if (this.popupMenuClass == null) return null;
        final Constructor<? extends JPopupMenu> ctor = popupMenuClass.getConstructor(
                owningPanel.getClass(), AnnotationTableModel.class, int.class, int.class);
        return ctor.newInstance(owningPanel, this, pIdx, pColumnIdx);
    }

    public void filterBy(final Object pFilter, int pColumnIndex) throws TableModelException
    {
        final AnnotatedTableModelColumn<T> column = byColumns.get(pColumnIndex);
        if (column == null)
        {
            throw new IllegalArgumentException("Unknown column: " + pColumnIndex);
        }

        fireTableRowsDeleted(0, displayedItems.size());

        CollectionUtils.filter(displayedItems,
                           new CellComparatorPredicate<T>(column, (T) pFilter));

        sort();

        fireTableRowsInserted(0, displayedItems.size());
    }

    public void clearFilter()
    {
        setContent(allItems);
    }

    public void sortByColumn(int pColumn, boolean pAscending) throws TableModelException
    {
        final AnnotatedTableModelColumn column = this.byColumns.get(pColumn);
        comparator = (pAscending ? column.createSorter() : new NotComparator(column.createSorter()));

        fireTableRowsDeleted(0, displayedItems.size());
        sort();
        fireTableRowsInserted(0, displayedItems.size());
    }

    public String getValue(int pRow, String pVersionAttrName)
    {
        try
        {
            final Object item = this.displayedItems.get(pRow);
            return BeanUtils.getProperty(item, pVersionAttrName);
        }
        catch (IllegalAccessException e)
        {
//            LOG.error(e);
            e.printStackTrace(System.err);
        }
        catch (InvocationTargetException e)
        {
//            LOG.error(e);
            e.printStackTrace(System.err);
        }
        catch (NoSuchMethodException e)
        {
//            LOG.error(e);
            e.printStackTrace(System.err);
        }

        return null;
    }

    public Integer findRowFor(String pValue, String pPropName)
    {
        for (int i = 0; i < getRowCount(); i++)
        {
            final String value = getValue(i, pPropName);
            if (pValue.equalsIgnoreCase(value))
            {
                return i;
            }
        }

        return null;
    }

    private class CellComparatorPredicate<T> implements Predicate
    {
        private final AnnotatedTableModelColumn<T> column;
        final private ICellComparator comparator;
        final private T filter;

        public CellComparatorPredicate(AnnotatedTableModelColumn<T> pColumn, T pFilter) throws TableModelException
        {
            column = pColumn;
            filter = pFilter;

            comparator = pColumn.createCellComparator(filter);
        }

        public boolean evaluate(Object o)
        {
            final Object v = column.get((T) o);
            return comparator.compare(v, filter);
        }
    }
}
