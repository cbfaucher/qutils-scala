/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.swing;

import com.quartz.qutilities.io.FileUtilities;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * This class implements a {@link FileFilter} for extension filtering, to be used with {@link javax.swing.JFileChooser#addChoosableFileFilter(javax.swing.filechooser.FileFilter)} .
 *
 * @author Christian
 * @since Quartz...
 */
public class ExtensionFileFilter extends FileFilter
{
    private String extension = null;

    public ExtensionFileFilter()
    {
        this(null);
    }

    public ExtensionFileFilter(String pExtension)
    {
        this.extension = pExtension;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String pExtension)
    {
        extension = pExtension;
    }

    public boolean accept(File f)
    {
        if (f.isDirectory() == true) return true;

        final String fileExtension = FileUtilities.getExtension(f);

        if (fileExtension == null && extension == null) return true;    //  no extension accepted

        if (fileExtension != null && extension != null) return extension.equalsIgnoreCase(fileExtension);

        return false;
    }

    public String getDescription()
    {
        return (extension != null ? "*." + extension : "<Files without extension>");
    }

    public String toString()
    {
        return this.getDescription();
    }
}
