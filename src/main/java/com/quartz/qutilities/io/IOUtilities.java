package com.quartz.qutilities.io;

import java.io.*;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
final public class IOUtilities
{
    static public final BufferedReader STDIN = new BufferedReader(new InputStreamReader(System.in));

    static public void copy(InputStream pIn, OutputStream pOut) throws IOException
    {
        final byte[] buffer = new byte[8192];

        int size;

        while ((size = pIn.read(buffer)) >= 0)
        {
            pOut.write(buffer, 0, size);
        }
    }

    static public String askQuestion(String pQuestion, String pDefaultValue) throws IOException
    {
        System.out.print(pQuestion + " " + (pDefaultValue != null ? "[" + pDefaultValue + "]" : "") + ": ");
        final String dir = STDIN.readLine();
        return (dir.length() > 0 ? dir : pDefaultValue);
    }

    static public String askQuestion(String pQuestion, String[] pValues) throws IOException
    {
        String choices = "";
        for (int i = 0; i < pValues.length; i++)
        {
            choices = choices + "\t" + (i+1) + ": " + pValues[i] + "\n";
        }

        while (true)
        {
            System.out.println(pQuestion + ":");
            System.out.print(choices);
            System.out.print("> ");

            try
            {
                final int idx = Integer.parseInt(STDIN.readLine());
                if (idx >= 1 && idx <= pValues.length)
                {
                    return pValues[idx - 1];
                }
            }
            catch (NumberFormatException e)
            {
                //  just continue to read...
            }
        }
    }
}
