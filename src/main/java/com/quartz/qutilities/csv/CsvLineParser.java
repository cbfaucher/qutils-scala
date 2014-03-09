package com.quartz.qutilities.csv;

import java.util.List;
import java.util.ArrayList;

/**
 * A parser for a single CSV line.  It handles empty ;; element as the value specified ("" or null).
 *
 * @author Christian
 * @since Quartz...
 */
public class CsvLineParser
{
    final private List<String> tokens = new ArrayList<String>();

    final private String defaultValueForEmpty;
    final private char separator;
    final private char quotingCharacter;
    private int nextTokenIdx = 0;

    public CsvLineParser(final char pSeparator, final char pQuotingChar, final String pValueForEmpty)
    {
        defaultValueForEmpty = pValueForEmpty;
        separator = pSeparator;
        quotingCharacter = pQuotingChar;
    }

    public String getDefaultValueForEmpty()
    {
        return defaultValueForEmpty;
    }

    public char getSeparator()
    {
        return separator;
    }

    public char getQuotingCharacter()
    {
        return quotingCharacter;
    }

    public void parse(String pLine)
    {
        nextTokenIdx = 0;
        tokens.clear();

        final int lineLength = pLine.length();
        int idx = 0;
        StringBuffer currentToken = new StringBuffer();
        boolean inQuotes = false;
        
        while (idx < lineLength)
        {
            final char c = pLine.charAt(idx++);

            //  if at a separator, we've read the end of a token
            if (c == separator)
            {
                if (!inQuotes)
                {
                    currentToken = addToken(currentToken);
                }
                else
                {
                    //  if quotes, simply tokenize the current char
                    currentToken.append(c);
                }
            }
            else if (c == quotingCharacter)
            {
                inQuotes = !inQuotes;
//                if (inQuotes)
//                {
//                    currentToken = addToken(currentToken);
//                    inQuotes = false;
//                }
//                else                Q
//                {
//                    inQuotes = true;
//                }
            }
            else
            {
                //  simply a character
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0)
        {
            addToken(currentToken);
        }


        if (inQuotes) throw new IllegalArgumentException("Quote of double-quote not closed: [" + pLine + "].");
    }

    private StringBuffer addToken(StringBuffer pCurrentToken)
    {
        tokens.add(pCurrentToken.length() > 0 ? pCurrentToken.toString() : null);
        pCurrentToken = new StringBuffer();
        return pCurrentToken;
    }

    public String nextToken()
    {
        return nextToken(null);
    }
    
    public String nextToken(String pDefaultValue)
    {
        final String s = tokens.get(nextTokenIdx++);

        if (s == null)
        {
            if (pDefaultValue != null) return pDefaultValue;
            return defaultValueForEmpty;
        }

        return s;
    }
}
