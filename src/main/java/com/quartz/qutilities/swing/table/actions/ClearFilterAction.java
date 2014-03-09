package com.quartz.qutilities.swing.table.actions;

import com.quartz.qutilities.swing.table.AnnotationTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class ClearFilterAction extends AbstractAction
{
    final private AnnotationTableModel model;

    public ClearFilterAction(AnnotationTableModel pModel)
    {
        super("Clear Filter...");
        
        model = pModel;
    }

    public void actionPerformed(ActionEvent e)
    {
        model.clearFilter();
    }
}
