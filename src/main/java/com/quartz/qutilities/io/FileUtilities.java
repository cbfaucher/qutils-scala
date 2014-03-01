/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.io;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;

/**
 * Utility methods for Files.
 *
 * @author Christian
 * @since Quartz...
 */
public class FileUtilities
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    ///////////////////////////////////////
    ////    STATIC METHODS

    /**
     * Return the extension portion of the file's name, without the '.', e.g. in "test.txt", the return value will be "txt"
     *
     * @param f the file
     * @return the extension, null if none.
     */
    static public String getExtension(File f)
    {
        if (f != null)
        {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1)
            {
                return filename.substring(i + 1).toLowerCase();
            }
            ;
        }
        return null;
    }

    public static List<String> load(URL pUrl) throws IOException
    {
        final InputStream is = pUrl.openStream();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final List<String> lines = new ArrayList<String>();

        try
        {
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }

            return lines;
        }
        finally
        {
            reader.close();
        }
    }
    
    public static List<String> loadFile(File pFile) throws IOException
    {
        return load(pFile.toURL());
    }
}
