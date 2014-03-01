package com.quartz.qutilities.swing.table.actions;

import com.quartz.qutilities.logging.ILog;
import com.quartz.qutilities.logging.LogManager;
import com.quartz.qutilities.swing.table.AnnotationTableModel;
import com.quartz.qutilities.swing.table.TableModelException;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class SortByColumnAction<T> extends AbstractAction
{
    static private final ILog LOG = LogManager.getLogger(SortByColumnAction.class);
    
    final private AnnotationTableModel<T> model;
    final private int column;
    final private boolean ascending;

    public SortByColumnAction(AnnotationTableModel<T> pModel, int pColumn, boolean pAscending)
    {
        super(pAscending ? "Sort by column (ascending)" : "Sort by column (descending)");

        model = pModel;
        column = pColumn;
        ascending = pAscending;
    }

    public void actionPerformed(ActionEvent e)
    {

        try
        {
            model.sortByColumn(column, ascending);
        }
        catch (TableModelException e1)
        {
            LOG.error("Could not sort the list.");
        }
    }
}
