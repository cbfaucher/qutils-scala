package com.quartz.qutilities.swing.table.actions;

import com.quartz.qutilities.swing.table.AnnotationTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class CopyValueAction extends AbstractAction
{
    private AnnotationTableModel model;
    private int row;
    private String rowPropertyName;

    public CopyValueAction(AnnotationTableModel pModel, int pRow, final String pLabel, final String pRowPropertyName)
    {
        super(pLabel);
        model = pModel;
        row = pRow;
        rowPropertyName = pRowPropertyName;        
    }

    public void actionPerformed(ActionEvent e)
    {
        final String version = model.getValue(row, rowPropertyName);
        final StringSelection contents = new StringSelection(version);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(contents, contents);
    }
}
