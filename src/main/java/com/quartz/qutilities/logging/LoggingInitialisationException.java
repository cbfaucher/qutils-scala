package com.quartz.qutilities.logging;

/**
 * Exception thrown by {@link LogManager} when it fails to initialise Java-Excel logging properly.
 *
 * @author Christian Faucher
 */
public class LoggingInitialisationException extends RuntimeException
{
    /**
     * Create a new Exception 
     *
     * @param message The message
     */
    public LoggingInitialisationException(final String message)
    {
        super(message);
    }
}
