package com.quartz.qutilities.io;

import com.quartz.qutilities.util.Output;

import java.io.OutputStream;
import java.io.IOException;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class OutputOutputStream extends OutputStream
{
    final private Output output;

    public OutputOutputStream(Output pOutput)
    {
        output = pOutput;
    }

    public void write(int b) throws IOException
    {
        output.write("" + (char)b);
    }
}
