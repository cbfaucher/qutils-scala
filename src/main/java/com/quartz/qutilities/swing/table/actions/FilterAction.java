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
public class FilterAction extends AbstractAction
{
    static private final ILog LOG = LogManager.getLogger(FilterAction.class);
    
    final private Object defaultValue;
    final private AnnotationTableModel model;
    private int columnIndex;

    public FilterAction(final AnnotationTableModel pModel, final int pColumnIndex, final Object pDefaultValue)
    {
        super("Filter...");

        model = pModel;
        columnIndex = pColumnIndex;
        defaultValue = pDefaultValue;
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            final String filter = (String) JOptionPane.showInputDialog(null, "Enter filter:", "Filter by...", JOptionPane.QUESTION_MESSAGE, null, null, defaultValue);
            if (filter == null) return;
            model.filterBy(filter, columnIndex);
        }
        catch (TableModelException e1)
        {
            LOG.error(e1);
        }
    }
}
